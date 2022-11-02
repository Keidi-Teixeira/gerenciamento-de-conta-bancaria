package info.keidi.usecases;

import info.keidi.domain.Client;
import info.keidi.domain.Identifier;
import info.keidi.exception.ClientNotFoundException;
import info.keidi.ports.ClientRepository;

public class GetClient {
  private final ClientRepository clientRepository;

  public GetClient(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client get(Identifier id) {

    return clientRepository
        .findById(id.getId())
        .orElseThrow(() -> new ClientNotFoundException(id.getId()));
  }
}
