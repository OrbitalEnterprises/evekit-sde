package enterprises.orbital.evekit.sde.ram;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the ramtyperequirements database table.
 * 
 */
@Embeddable
public class RamTypeRequirementPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private byte              activityID;
  private int               requiredTypeID;

  public RamTypeRequirementPK() {}

  public int getTypeID() {
    return this.typeID;
  }

  public byte getActivityID() {
    return this.activityID;
  }

  public int getRequiredTypeID() {
    return this.requiredTypeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof RamTypeRequirementPK)) { return false; }
    RamTypeRequirementPK castOther = (RamTypeRequirementPK) other;
    return (this.typeID == castOther.typeID) && (this.activityID == castOther.activityID) && (this.requiredTypeID == castOther.requiredTypeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.typeID;
    hash = hash * prime + (this.activityID);
    hash = hash * prime + this.requiredTypeID;

    return hash;
  }

  @Override
  public String toString() {
    return "RamTypeRequirementPK [typeID=" + typeID + ", activityID=" + activityID + ", requiredTypeID=" + requiredTypeID + "]";
  }

}