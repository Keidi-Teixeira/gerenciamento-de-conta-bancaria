package info.keidi.util;

import info.keidi.domain.CPF;
import info.keidi.domain.Client;
import info.keidi.rest.dto.ClientRequest;
import info.keidi.rest.dto.DepositRequest;
import info.keidi.rest.dto.TransferRequest;

import java.math.BigDecimal;

public class RequestDataCreator {

  public static Client client() {
    var id = new CPF("97231193072");
    return new Client(id, "Tiringa da silva sauro", BigDecimal.TEN);
  }

  public static ClientRequest clientRequest() {
    return new ClientRequest("225.525.640-11", "Tiringa da silva sauro");
  }

  public static DepositRequest deposit() {
    return new DepositRequest("225.525.640-11", BigDecimal.TEN);
  }

  public static TransferRequest transfer() {
    return new TransferRequest("225.525.640-11", "97231193072", BigDecimal.ONE);
  }
}
