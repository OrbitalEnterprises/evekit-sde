package enterprises.orbital.evekit.sde.inv;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the invtypematerials database table.
 * 
 */
@Embeddable
public class InvTypeMaterialPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               materialTypeID;

  public InvTypeMaterialPK() {}

  public InvTypeMaterialPK(int typeID, int materialTypeID) {
    super();
    this.typeID = typeID;
    this.materialTypeID = materialTypeID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public int getMaterialTypeID() {
    return this.materialTypeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof InvTypeMaterialPK)) { return false; }
    InvTypeMaterialPK castOther = (InvTypeMaterialPK) other;
    return (this.typeID == castOther.typeID) && (this.materialTypeID == castOther.materialTypeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.typeID;
    hash = hash * prime + this.materialTypeID;

    return hash;
  }

  @Override
  public String toString() {
    return "InvTypeMaterialPK [typeID=" + typeID + ", materialTypeID=" + materialTypeID + "]";
  }

}