package info.keidi.domain;

import info.keidi.exception.InvalidIdentifierException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class CPFTests {

  @Test
  @DisplayName("Should throw exception for invalid CPF entries")
  void shouldThrowExceptionForInvalidCPF() {
    assertThrows(InvalidIdentifierException.class, () -> new CPF("00000000000"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("11111111111"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("22222222222"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("33333333333"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("44444444444"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("55555555555"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("66666666666"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("77777777777"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("88888888888"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("99999999999"));

    assertThrows(InvalidIdentifierException.class, () -> new CPF(""), "Empty CPF");
    assertThrows(InvalidIdentifierException.class, () -> new CPF("24tyhm2klpo"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("242"));
    assertThrows(InvalidIdentifierException.class, () -> new CPF("1232323232323"));
  }

  @Test
  @DisplayName("Should create valid CPF")
  void shouldCreateValidCPF() {
    var cpf = new CPF("55028292000");
    assertEquals("55028292000", cpf.getId());
  }

  @Test
  @DisplayName("Should remove non numerical characters from identifier")
  void shouldRemoveNonNumericalCharactersFromId() {
    var cpf1 = new CPF("550.282.920-00");
    var cpf2 = new CPF(".....///550.282.920-00");
    var cpf3 = new CPF("abc550.def282...;920%$#00");

    assertEquals("55028292000", cpf1.getId());
    assertEquals("55028292000", cpf2.getId());
    assertEquals("55028292000", cpf3.getId());
  }
}
