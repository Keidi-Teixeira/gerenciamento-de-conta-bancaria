package info.keidi.exception;

public class ClientExistsException extends BaseDomainException {
  public ClientExistsException(String id) {
    super(String.format("Client with identifier '%s' already exists.", id));
  }
}
