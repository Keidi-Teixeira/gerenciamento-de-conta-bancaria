package info.keidi.usecases;

import info.keidi.domain.Identifier;

import java.math.BigDecimal;

public class TransferData {
  private final Identifier clientSender;
  private final Identifier clientReceiver;
  private final BigDecimal value;

  public TransferData(Identifier clientSender, Identifier clientReceiver, BigDecimal value) {
    this.clientSender = clientSender;
    this.clientReceiver = clientReceiver;
    this.value = value;
  }

  public Identifier getClientSender() {
    return clientSender;
  }

  public Identifier getClientReceiver() {
    return clientReceiver;
  }

  public BigDecimal getValue() {
    return value;
  }
}
