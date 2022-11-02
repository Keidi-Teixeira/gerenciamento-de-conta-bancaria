package info.keidi.exception;

public class NotEnoughFundsException extends BaseDomainException {
  public NotEnoughFundsException() {
    super("Not enough funds on the account to complete the transfer.");
  }
}
