package enterprises.orbital.evekit.sde.trn;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the trntranslations database table.
 * 
 */
@Embeddable
public class TrnTranslationPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               tcID;
  private int               keyID;
  private String            languageID;

  public TrnTranslationPK() {}

  public int getTcID() {
    return this.tcID;
  }

  public int getKeyID() {
    return this.keyID;
  }

  public String getLanguageID() {
    return this.languageID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof TrnTranslationPK)) { return false; }
    TrnTranslationPK castOther = (TrnTranslationPK) other;
    return (this.tcID == castOther.tcID) && (this.keyID == castOther.keyID) && this.languageID.equals(castOther.languageID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.tcID;
    hash = hash * prime + this.keyID;
    hash = hash * prime + this.languageID.hashCode();

    return hash;
  }

  @Override
  public String toString() {
    return "TrnTranslationPK [tcID=" + tcID + ", keyID=" + keyID + ", languageID=" + languageID + "]";
  }

}