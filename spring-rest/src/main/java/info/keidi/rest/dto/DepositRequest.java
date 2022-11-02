package info.keidi.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class DepositRequest {
  @NotBlank private final String clientId;

  @DecimalMin(value = "1")
  private final BigDecimal value;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public DepositRequest(
      @JsonProperty("clientId") String clientId, @JsonProperty("value") BigDecimal value) {
    this.clientId = clientId;
    this.value = value;
  }

  public String getClientId() {
    return clientId;
  }

  public BigDecimal getValue() {
    return value;
  }
}
