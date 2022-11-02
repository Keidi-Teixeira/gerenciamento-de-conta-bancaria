package info.keidi.exception;

public class ClientNotFoundException extends BaseDomainException {
  public ClientNotFoundException(String clientId) {
    super(String.format("Client with id '%s' do not exists.", clientId));
  }
}
