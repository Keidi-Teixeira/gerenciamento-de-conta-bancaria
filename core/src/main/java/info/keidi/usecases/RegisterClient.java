package info.keidi.usecases;

import info.keidi.domain.Client;
import info.keidi.exception.ClientExistsException;
import info.keidi.ports.ClientRepository;

import java.math.BigDecimal;

public class RegisterClient {
  private final ClientRepository clientRepository;

  public RegisterClient(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client create(Client client) {
    var id = client.getIdentifier();
    if (clientRepository.existsById(id.getId())) throw new ClientExistsException(id.getId());

    client.setBalance(BigDecimal.ZERO);
    return clientRepository.createClient(client);
  }
}
