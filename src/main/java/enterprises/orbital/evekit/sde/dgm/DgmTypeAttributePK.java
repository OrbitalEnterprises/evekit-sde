package enterprises.orbital.evekit.sde.dgm;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the dgmtypeattributes database table.
 * 
 */
@Embeddable
public class DgmTypeAttributePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               attributeID;

  public DgmTypeAttributePK() {}

  public DgmTypeAttributePK(int typeID, int attributeID) {
    super();
    this.typeID = typeID;
    this.attributeID = attributeID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public int getAttributeID() {
    return this.attributeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof DgmTypeAttributePK)) { return false; }
    DgmTypeAttributePK castOther = (DgmTypeAttributePK) other;
    return (this.typeID == castOther.typeID) && (this.attributeID == castOther.attributeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.typeID;
    hash = hash * prime + this.attributeID;

    return hash;
  }

  @Override
  public String toString() {
    return "DgmTypeAttributePK [typeID=" + typeID + ", attributeID=" + attributeID + "]";
  }

}