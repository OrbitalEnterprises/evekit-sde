package enterprises.orbital.evekit.sde.dgm;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the dgmtypeeffects database table.
 * 
 */
@Embeddable
public class DgmTypeEffectPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               effectID;

  public DgmTypeEffectPK() {}

  public DgmTypeEffectPK(int typeID, int effectID) {
    super();
    this.typeID = typeID;
    this.effectID = effectID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public int getEffectID() {
    return this.effectID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof DgmTypeEffectPK)) { return false; }
    DgmTypeEffectPK castOther = (DgmTypeEffectPK) other;
    return (this.typeID == castOther.typeID) && (this.effectID == castOther.effectID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.typeID;
    hash = hash * prime + this.effectID;

    return hash;
  }

  @Override
  public String toString() {
    return "DgmTypeEffectPK [typeID=" + typeID + ", effectID=" + effectID + "]";
  }

}