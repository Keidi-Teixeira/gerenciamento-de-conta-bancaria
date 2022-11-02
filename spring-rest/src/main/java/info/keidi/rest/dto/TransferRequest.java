package info.keidi.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.keidi.domain.CPF;
import info.keidi.usecases.TransferData;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class TransferRequest {
  @NotBlank private final String from;
  @NotBlank private final String to;

  @DecimalMin("1")
  private final BigDecimal value;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public TransferRequest(
      @JsonProperty("from") String from,
      @JsonProperty("to") String to,
      @JsonProperty("value") BigDecimal value) {
    this.from = from;
    this.to = to;
    this.value = value;
  }

  public TransferData toTransferData() {
    var idFrom = new CPF(from);
    var idTo = new CPF(to);
    return new TransferData(idFrom, idTo, value);
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public BigDecimal getValue() {
    return value;
  }
}
