package info.keidi.usecases;

import info.keidi.exception.ClientExistsException;
import info.keidi.util.ClientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

final class RegisterClientTests extends TestBaseClass {
  private RegisterClient registerClient;

  @BeforeEach
  void setUp() {
    registerClient = new RegisterClient(clientRepository);
  }

  @Test
  @DisplayName("Should throw exception when client already exists")
  void shouldThrowExceptionWhenClientAlreadyExists() {
    var client = ClientCreator.single();
    var clientId = client.getIdentifier().getId();

    when(clientRepository.existsById(clientId)).thenReturn(true);

    assertThrows(ClientExistsException.class, () -> registerClient.create(client));
  }

  @Test
  @DisplayName("Should create a new client with balance of zero")
  void shouldCreateNewClientWithBalanceZero() {
    var client = ClientCreator.single();
    var clientId = client.getIdentifier().getId();

    when(clientRepository.existsById(clientId)).thenReturn(false);
    when(clientRepository.createClient(client)).thenReturn(client);

    var createdClient = registerClient.create(client);

    assertEquals(BigDecimal.ZERO, createdClient.getBalance());
    assertEquals(client.getFullName(), createdClient.getFullName());
    assertEquals(client.getIdentifier().getId(), createdClient.getIdentifier().getId());
  }
}
