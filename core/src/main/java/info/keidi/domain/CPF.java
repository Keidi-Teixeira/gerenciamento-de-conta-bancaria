package info.keidi.domain;

public class CPF extends Identifier {
  public CPF(String id) {
    super(id);
  }

  /**
   * Method that validates a CPF with only numbers. This CPF validation was implemented by
   * "devmedia"
   *
   * @param id CPF as string
   * @return true if it is a valid CPF, false otherwise
   */
  @Override
  boolean isIdValid(String id) {

    if (id.equals("00000000000")
        || id.equals("11111111111")
        || id.equals("22222222222")
        || id.equals("33333333333")
        || id.equals("44444444444")
        || id.equals("55555555555")
        || id.equals("66666666666")
        || id.equals("77777777777")
        || id.equals("88888888888")
        || id.equals("99999999999")
        || (id.length() != 11)) return false;

    char dig10, dig11;
    int sm, i, r, num, peso;

    sm = 0;
    peso = 10;
    for (i = 0; i < 9; i++) {
      num = (int) (id.charAt(i) - 48);
      sm = sm + (num * peso);
      peso = peso - 1;
    }

    r = 11 - (sm % 11);
    if ((r == 10) || (r == 11)) dig10 = '0';
    else dig10 = (char) (r + 48);

    sm = 0;
    peso = 11;
    for (i = 0; i < 10; i++) {
      num = (int) (id.charAt(i) - 48);
      sm = sm + (num * peso);
      peso = peso - 1;
    }

    r = 11 - (sm % 11);
    if ((r == 10) || (r == 11)) dig11 = '0';
    else dig11 = (char) (r + 48);

    return (dig10 == id.charAt(9)) && (dig11 == id.charAt(10));
  }
}
