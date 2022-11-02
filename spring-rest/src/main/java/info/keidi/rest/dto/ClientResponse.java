package info.keidi.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.keidi.domain.Client;

import java.math.BigDecimal;

public class ClientResponse extends ClientRequest {
  private final BigDecimal balance;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ClientResponse(
      @JsonProperty("cpf") String cpf,
      @JsonProperty("fullname") String fullName,
      @JsonProperty("balance") BigDecimal balance) {
    super(cpf, fullName);
    this.balance = balance;
  }

  public static ClientResponse fromClient(Client client) {
    return new ClientResponse(
        client.getIdentifier().getId(), client.getFullName(), client.getBalance());
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
