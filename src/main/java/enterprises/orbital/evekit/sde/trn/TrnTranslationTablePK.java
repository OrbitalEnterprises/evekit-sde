package enterprises.orbital.evekit.sde.trn;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the translationtables database table.
 * 
 */
@Embeddable
public class TrnTranslationTablePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private String            sourceTable;
  private String            translatedKey;

  public TrnTranslationTablePK() {}

  public String getSourceTable() {
    return this.sourceTable;
  }

  public String getTranslatedKey() {
    return this.translatedKey;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof TrnTranslationTablePK)) { return false; }
    TrnTranslationTablePK castOther = (TrnTranslationTablePK) other;
    return this.sourceTable.equals(castOther.sourceTable) && this.translatedKey.equals(castOther.translatedKey);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.sourceTable.hashCode();
    hash = hash * prime + this.translatedKey.hashCode();

    return hash;
  }

  @Override
  public String toString() {
    return "TranslationTablePK [sourceTable=" + sourceTable + ", translatedKey=" + translatedKey + "]";
  }

}