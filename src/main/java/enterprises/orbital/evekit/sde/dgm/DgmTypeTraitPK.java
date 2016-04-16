package enterprises.orbital.evekit.sde.dgm;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the dgmtypetraits database table.
 * 
 */
@Embeddable
public class DgmTypeTraitPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               parentTypeID;
  private int               traitID;

  public DgmTypeTraitPK() {}

  public int getTypeID() {
    return this.typeID;
  }

  public int getParentTypeID() {
    return this.parentTypeID;
  }

  public int getTraitID() {
    return this.traitID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof DgmTypeTraitPK)) { return false; }
    DgmTypeTraitPK castOther = (DgmTypeTraitPK) other;
    return (this.typeID == castOther.typeID) && (this.parentTypeID == castOther.parentTypeID) && (this.traitID == castOther.traitID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.typeID;
    hash = hash * prime + this.parentTypeID;
    hash = hash * prime + this.traitID;

    return hash;
  }

  @Override
  public String toString() {
    return "DgmTypeTraitPK [typeID=" + typeID + ", parentTypeID=" + parentTypeID + ", traitID=" + traitID + "]";
  }

}