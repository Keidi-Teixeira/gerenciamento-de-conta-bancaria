package info.keidi.exception;

public class InvalidTransferValue extends BaseDomainException {
  public InvalidTransferValue() {
    super("Transfer values must be greater than zero or less/equal to 2000");
  }
}
