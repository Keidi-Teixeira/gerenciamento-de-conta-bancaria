package info.keidi.rest.config;

import info.keidi.ports.ClientRepository;
import info.keidi.usecases.Deposit;
import info.keidi.usecases.GetClient;
import info.keidi.usecases.RegisterClient;
import info.keidi.usecases.Transfer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
  private final ClientRepository clientRepository;

  public BeanConfig(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Bean
  public RegisterClient registerClientBean() {
    return new RegisterClient(clientRepository);
  }

  @Bean
  public Transfer transferBean() {
    return new Transfer(clientRepository);
  }

  @Bean
  public Deposit depositBean() {
    return new Deposit(clientRepository);
  }

  @Bean
  public GetClient getClientBean() {
    return new GetClient(clientRepository);
  }
}
