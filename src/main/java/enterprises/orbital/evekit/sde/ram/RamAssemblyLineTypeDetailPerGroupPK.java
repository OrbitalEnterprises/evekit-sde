package enterprises.orbital.evekit.sde.ram;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the ramassemblylinetypedetailpergroup database table.
 * 
 */
@Embeddable
public class RamAssemblyLineTypeDetailPerGroupPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               assemblyLineTypeID;
  private int               groupID;

  public RamAssemblyLineTypeDetailPerGroupPK() {}

  public int getAssemblyLineTypeID() {
    return this.assemblyLineTypeID;
  }

  public int getGroupID() {
    return this.groupID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof RamAssemblyLineTypeDetailPerGroupPK)) { return false; }
    RamAssemblyLineTypeDetailPerGroupPK castOther = (RamAssemblyLineTypeDetailPerGroupPK) other;
    return (this.assemblyLineTypeID == castOther.assemblyLineTypeID) && (this.groupID == castOther.groupID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + (this.assemblyLineTypeID);
    hash = hash * prime + this.groupID;

    return hash;
  }

  @Override
  public String toString() {
    return "RamAssemblyLineTypeDetailPerGroupPK [assemblyLineTypeID=" + assemblyLineTypeID + ", groupID=" + groupID + "]";
  }

}