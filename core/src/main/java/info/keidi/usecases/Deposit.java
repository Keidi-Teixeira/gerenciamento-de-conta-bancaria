package info.keidi.usecases;

import info.keidi.exception.ClientNotFoundException;
import info.keidi.exception.InvalidDepositValue;
import info.keidi.ports.ClientRepository;

import java.math.BigDecimal;

public class Deposit {
  private final ClientRepository clientRepository;

  public Deposit(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void deposit(String clientId, BigDecimal value) {
    if (!isDepositValueValid(value)) throw new InvalidDepositValue();

    var client =
        clientRepository
            .findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(clientId));

    var newBalance = client.getBalance().add(value);

    clientRepository.updateBalance(client.getIdentifier().getId(), newBalance);
  }

  private boolean isDepositValueValid(BigDecimal value) {
    return value.compareTo(new BigDecimal("0")) > 0 && value.compareTo(new BigDecimal("2000")) <= 0;
  }
}
