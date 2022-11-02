package info.keidi.persistence;

import info.keidi.domain.CPF;
import info.keidi.domain.Client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "client")
public class ClientEntity {
  @Id private String id;

  @Column(name = "fullname")
  private String fullName;

  @Column(name = "balance")
  private BigDecimal balance;

  public ClientEntity() {}

  public ClientEntity(String id, String fullName, BigDecimal balance) {
    this.id = id;
    this.fullName = fullName;
    this.balance = balance.setScale(2, RoundingMode.HALF_EVEN);
  }

  public Client toClient() {
    return new Client(new CPF(this.id), this.fullName, this.balance);
  }

  public static ClientEntity fromClient(Client client) {
    var id = client.getIdentifier();
    return new ClientEntity(id.getId(), client.getFullName(), client.getBalance());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
