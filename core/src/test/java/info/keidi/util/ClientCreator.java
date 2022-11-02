package info.keidi.util;

import info.keidi.domain.CPF;
import info.keidi.domain.Client;

import java.math.BigDecimal;

public class ClientCreator {

  public static Client single() {
    var id = new CPF("55028292000");
    return new Client(id, "Wally test", BigDecimal.TEN);
  }
}
