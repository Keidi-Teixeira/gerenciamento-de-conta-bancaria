package info.keidi.usecases;

import info.keidi.exception.ClientNotFoundException;
import info.keidi.exception.InvalidTransferValue;
import info.keidi.exception.NotEnoughFundsException;
import info.keidi.exception.TransferSameAccountException;
import info.keidi.ports.ClientRepository;

import java.math.BigDecimal;

public class Transfer {
  private final ClientRepository clientRepository;

  public Transfer(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void transfer(TransferData transferData) {
    if (transferData.getClientSender().equals(transferData.getClientReceiver()))
      throw new TransferSameAccountException();

    var senderId = transferData.getClientSender().getId();
    var receiverId = transferData.getClientReceiver().getId();
    var senderOptional = clientRepository.findById(senderId);
    var receiverOptional = clientRepository.findById(receiverId);

    if (senderOptional.isEmpty()) throw new ClientNotFoundException(senderId);
    else if (receiverOptional.isEmpty()) throw new ClientNotFoundException(receiverId);
    else if (!isTransferValueValid(transferData)) throw new InvalidTransferValue();

    var sender = senderOptional.get();
    var transferValue = transferData.getValue();

    if (sender.getBalance().compareTo(transferValue) < 0) throw new NotEnoughFundsException();

    var receiver = receiverOptional.get();

    sender.setBalance(sender.getBalance().subtract(transferValue));
    receiver.setBalance(receiver.getBalance().add(transferValue));

    clientRepository.updateBalance(sender.getIdentifier().getId(), sender.getBalance());
    clientRepository.updateBalance(receiver.getIdentifier().getId(), receiver.getBalance());
  }

  private boolean isTransferValueValid(TransferData transferData) {
    return transferData.getValue().compareTo(new BigDecimal("0")) > 0;
  }
}
