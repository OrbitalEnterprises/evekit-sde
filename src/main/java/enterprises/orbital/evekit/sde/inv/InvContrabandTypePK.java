package enterprises.orbital.evekit.sde.inv;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the invcontrabandtypes database table.
 * 
 */
@Embeddable
public class InvContrabandTypePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               factionID;
  private int               typeID;

  public InvContrabandTypePK() {}

  public int getFactionID() {
    return this.factionID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof InvContrabandTypePK)) { return false; }
    InvContrabandTypePK castOther = (InvContrabandTypePK) other;
    return (this.factionID == castOther.factionID) && (this.typeID == castOther.typeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.factionID;
    hash = hash * prime + this.typeID;

    return hash;
  }

  @Override
  public String toString() {
    return "InvContrabandTypePK [factionID=" + factionID + ", typeID=" + typeID + "]";
  }

}