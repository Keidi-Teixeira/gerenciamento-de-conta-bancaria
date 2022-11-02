package info.keidi.persistence;

import info.keidi.domain.Client;
import info.keidi.ports.ClientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements ClientRepository {

  private final ClientJpaAdapter clientJpaAdapter;

  public ClientRepositoryAdapter(ClientJpaAdapter clientJpaAdapter) {
    this.clientJpaAdapter = clientJpaAdapter;
  }

  @Transactional
  @Override
  public Client createClient(Client client) {
    return clientJpaAdapter.save(ClientEntity.fromClient(client)).toClient();
  }

  @Override
  public boolean existsById(String cpf) {
    return clientJpaAdapter.existsById(cpf);
  }

  @Override
  public Optional<Client> findById(String clientId) {
    var clientEntityOptional = clientJpaAdapter.findById(clientId);
    return clientEntityOptional.map(ClientEntity::toClient);
  }

  @Override
  public Client save(Client client) {
    var clientEntity = ClientEntity.fromClient(client);
    clientJpaAdapter.save(clientEntity);
    return clientEntity.toClient();
  }

  @Override
  public void updateBalance(String clientId, BigDecimal value) {
    if (clientJpaAdapter.existsById(clientId)) clientJpaAdapter.updateBalance(clientId, value);
  }
}
