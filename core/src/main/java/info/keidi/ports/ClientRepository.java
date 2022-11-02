package info.keidi.ports;

import info.keidi.domain.Client;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClientRepository {
  Client createClient(Client client);

  boolean existsById(String cpf);

  Optional<Client> findById(String clientId);

  Client save(Client client);

  void updateBalance(String clientId, BigDecimal value);
}
