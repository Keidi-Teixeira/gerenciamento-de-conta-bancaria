package info.keidi.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.keidi.domain.CPF;
import info.keidi.domain.Client;

import java.math.BigDecimal;

public class ClientRequest {
  private final String cpf;
  private final String fullName;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ClientRequest(@JsonProperty("cpf") String cpf, @JsonProperty("fullname") String fullName) {
    this.cpf = cpf;
    this.fullName = fullName;
  }

  public Client toClient() {
    var cpf = new CPF(this.cpf);
    return new Client(cpf, this.fullName, BigDecimal.ZERO);
  }

  public String getCpf() {
    return cpf;
  }

  public String getFullName() {
    return fullName;
  }
}
