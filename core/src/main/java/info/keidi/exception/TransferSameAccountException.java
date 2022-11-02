package info.keidi.exception;

public class TransferSameAccountException extends BaseDomainException {
  public TransferSameAccountException() {
    super("Cannot transfer to the same account");
  }
}
