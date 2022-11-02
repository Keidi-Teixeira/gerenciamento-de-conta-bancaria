package info.keidi.usecases;

import info.keidi.domain.CPF;
import info.keidi.domain.Client;
import info.keidi.exception.ClientNotFoundException;
import info.keidi.exception.InvalidTransferValue;
import info.keidi.exception.NotEnoughFundsException;
import info.keidi.exception.TransferSameAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class TransferTests extends TestBaseClass {
  private Transfer transfer;

  @BeforeEach
  void setUp() {
    transfer = new Transfer(clientRepository);
  }

  @Test
  @DisplayName("Should fail to transfer to the same account")
  void shouldFailToTransferToSameAccount() {
    var clientId = new CPF("55028292000");
    var transferData = new TransferData(clientId, clientId, BigDecimal.TEN);
    assertThrows(TransferSameAccountException.class, () -> transfer.transfer(transferData));

    var idWithNonDigit = new CPF("550.282.920-00");
    var transferData2 = new TransferData(idWithNonDigit, clientId, BigDecimal.TEN);
    assertThrows(TransferSameAccountException.class, () -> transfer.transfer(transferData2));
  }

  @Test
  @DisplayName("Should throw exception if not enough funds to transfer")
  void shouldThrowExceptionIfNotEnoughFunds() {
    var senderId = new CPF("55028292000");
    var receiverId = new CPF("948.718.220-95");
    var sender = new Client(senderId, "", BigDecimal.TEN);
    var receiver = new Client(receiverId, "", BigDecimal.TEN);

    when(clientRepository.findById(senderId.getId())).thenReturn(Optional.of(sender));
    when(clientRepository.findById(receiverId.getId())).thenReturn(Optional.of(receiver));

    var transferData = new TransferData(senderId, receiverId, new BigDecimal("20"));
    assertThrows(NotEnoughFundsException.class, () -> transfer.transfer(transferData));

    verify(clientRepository, times(1)).findById((senderId.getId()));
    verify(clientRepository, times(1)).findById((receiverId.getId()));
  }

  @Test
  @DisplayName("Should fail to transfer if sender do not exists")
  void shouldFailIfSenderDoNotExists() {
    var senderId = new CPF("55028292000");
    var receiverId = new CPF("948.718.220-95");
    var receiver = new Client(receiverId, "", BigDecimal.TEN);

    when(clientRepository.findById(senderId.getId())).thenReturn(Optional.empty());
    when(clientRepository.findById(receiverId.getId())).thenReturn(Optional.of(receiver));

    var transferData = new TransferData(senderId, receiverId, BigDecimal.ONE);
    assertThrows(ClientNotFoundException.class, () -> transfer.transfer(transferData));

    verify(clientRepository, times(1)).findById((senderId.getId()));
    verify(clientRepository, times(1)).findById((receiverId.getId()));
  }

  @Test
  @DisplayName("Should fail to transfer if receiver do not exists")
  void shouldFailIfReceiverDoNotExists() {
    var senderId = new CPF("55028292000");
    var receiverId = new CPF("948.718.220-95");
    var sender = new Client(senderId, "", BigDecimal.TEN);

    when(clientRepository.findById(senderId.getId())).thenReturn(Optional.of(sender));
    when(clientRepository.findById(receiverId.getId())).thenReturn(Optional.empty());

    var transferData = new TransferData(senderId, receiverId, new BigDecimal("20"));
    assertThrows(ClientNotFoundException.class, () -> transfer.transfer(transferData));

    verify(clientRepository, times(1)).findById((senderId.getId()));
    verify(clientRepository, times(1)).findById((receiverId.getId()));
  }

  @Test
  @DisplayName("Should not transfer values equal or less zero")
  void shouldNotTransferValuesEqualOrLessZero() {
    var senderId = new CPF("55028292000");
    var receiverId = new CPF("948.718.220-95");
    var sender = new Client(senderId, "", BigDecimal.TEN);
    var receiver = new Client(receiverId, "", BigDecimal.TEN);

    when(clientRepository.findById(senderId.getId())).thenReturn(Optional.of(sender));
    when(clientRepository.findById(receiverId.getId())).thenReturn(Optional.of(receiver));

    var transferData = new TransferData(senderId, receiverId, BigDecimal.ZERO);
    var transferDataNegativeBalance = new TransferData(senderId, receiverId, new BigDecimal("-10"));

    assertThrows(InvalidTransferValue.class, () -> transfer.transfer(transferData));
    assertThrows(InvalidTransferValue.class, () -> transfer.transfer(transferDataNegativeBalance));

    verify(clientRepository, times(2)).findById((senderId.getId()));
    verify(clientRepository, times(2)).findById((receiverId.getId()));
  }

  @Test
  @DisplayName("Should transfer funds between clients")
  void shouldTransferFundsBetweenClients() {
    var senderId = new CPF("55028292000");
    var receiverId = new CPF("948.718.220-95");
    var sender = new Client(senderId, "", BigDecimal.TEN);
    var receiver = new Client(receiverId, "", BigDecimal.TEN);

    when(clientRepository.findById(senderId.getId())).thenReturn(Optional.of(sender));
    when(clientRepository.findById(receiverId.getId())).thenReturn(Optional.of(receiver));

    var transferData = new TransferData(senderId, receiverId, new BigDecimal("4"));

    transfer.transfer(transferData);

    verify(clientRepository, times(1)).findById((senderId.getId()));
    verify(clientRepository, times(1)).findById((receiverId.getId()));

    verify(clientRepository, times(1)).updateBalance(senderId.getId(), new BigDecimal("6.00"));
    verify(clientRepository, times(1)).updateBalance(receiverId.getId(), new BigDecimal("14.00"));
  }

  @Test
  @DisplayName("Should transfer all funds from sender")
  void shouldTransferAllFundsFromSender() {
    var senderId = new CPF("55028292000");
    var receiverId = new CPF("948.718.220-95");
    var sender = new Client(senderId, "", BigDecimal.TEN);
    var receiver = new Client(receiverId, "", BigDecimal.TEN);

    when(clientRepository.findById(senderId.getId())).thenReturn(Optional.of(sender));
    when(clientRepository.findById(receiverId.getId())).thenReturn(Optional.of(receiver));

    var transferData = new TransferData(senderId, receiverId, sender.getBalance());

    transfer.transfer(transferData);

    verify(clientRepository, times(1)).findById((senderId.getId()));
    verify(clientRepository, times(1)).findById((receiverId.getId()));

    verify(clientRepository, times(1))
        .updateBalance(senderId.getId(), BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
    verify(clientRepository, times(1)).updateBalance(receiverId.getId(), new BigDecimal("20.00"));
  }
}
