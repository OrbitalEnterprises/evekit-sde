package enterprises.orbital.evekit.sde.dgm;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the dgmtypemasteries database table.
 * 
 */
@Embeddable
public class DgmTypeMasteryPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               masteryID;

  public DgmTypeMasteryPK() {}

  public DgmTypeMasteryPK(int typeID, int masteryID) {
    super();
    this.typeID = typeID;
    this.masteryID = masteryID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public int getMasteryID() {
    return this.masteryID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof DgmTypeMasteryPK)) { return false; }
    DgmTypeMasteryPK castOther = (DgmTypeMasteryPK) other;
    return (this.typeID == castOther.typeID) && (this.masteryID == castOther.masteryID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.typeID;
    hash = hash * prime + this.masteryID;

    return hash;
  }

  @Override
  public String toString() {
    return "DgmTypeMasteryPK [typeID=" + typeID + ", masteryID=" + masteryID + "]";
  }

}