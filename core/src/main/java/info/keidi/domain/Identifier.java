package info.keidi.domain;

import info.keidi.exception.InvalidIdentifierException;

import java.util.Objects;

public abstract class Identifier {
  private final String id;

  public Identifier(String id) {
    var cleanedId = id.replaceAll("[^\\d]", "");
    if (!isIdValid(cleanedId)) throw new InvalidIdentifierException("Invalid identifier " + id);
    this.id = cleanedId;
  }

  abstract boolean isIdValid(String id);

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Identifier that = (Identifier) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
