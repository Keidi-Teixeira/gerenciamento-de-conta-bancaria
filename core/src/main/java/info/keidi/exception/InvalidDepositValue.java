package info.keidi.exception;

public class InvalidDepositValue extends BaseDomainException {
  public InvalidDepositValue() {
    super("Deposit values must be greater than zero and less/equal then 2000");
  }
}
