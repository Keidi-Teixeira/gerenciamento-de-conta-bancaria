package info.keidi.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Client {
  private final Identifier identifier;
  private final String fullName;
  private BigDecimal balance;

  public Client(Identifier identifier, String fullName, BigDecimal balance) {
    this.identifier = identifier;
    this.fullName = fullName;
    this.balance = balance.setScale(2, RoundingMode.HALF_EVEN);
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Identifier getIdentifier() {
    return identifier;
  }

  public String getFullName() {
    return fullName;
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
