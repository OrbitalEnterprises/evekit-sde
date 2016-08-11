package enterprises.orbital.evekit.sde.skn;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the skinship database table.
 * 
 */
@Embeddable
public class SknShipPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               skinID;
  private int               typeID;

  public SknShipPK() {}

  public SknShipPK(int skinID, int typeID) {
    super();
    this.skinID = skinID;
    this.typeID = typeID;
  }

  public int getSkinID() {
    return skinID;
  }

  public int getTypeID() {
    return typeID;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    SknShipPK other = (SknShipPK) obj;
    if (skinID != other.skinID) return false;
    if (typeID != other.typeID) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + skinID;
    result = prime * result + typeID;
    return result;
  }

  @Override
  public String toString() {
    return "SknShipPK [skinID=" + skinID + ", typeID=" + typeID + "]";
  }

}