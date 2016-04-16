package enterprises.orbital.evekit.sde.inv;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the invcontroltowerresources database table.
 * 
 */
@Embeddable
public class InvControlTowerResourcePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               controlTowerTypeID;
  private int               resourceTypeID;

  public InvControlTowerResourcePK() {}

  public int getControlTowerTypeID() {
    return this.controlTowerTypeID;
  }

  public int getResourceTypeID() {
    return this.resourceTypeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof InvControlTowerResourcePK)) { return false; }
    InvControlTowerResourcePK castOther = (InvControlTowerResourcePK) other;
    return (this.controlTowerTypeID == castOther.controlTowerTypeID) && (this.resourceTypeID == castOther.resourceTypeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.controlTowerTypeID;
    hash = hash * prime + this.resourceTypeID;

    return hash;
  }

  @Override
  public String toString() {
    return "InvControlTowerResourcePK [controlTowerTypeID=" + controlTowerTypeID + ", resourceTypeID=" + resourceTypeID + "]";
  }

}