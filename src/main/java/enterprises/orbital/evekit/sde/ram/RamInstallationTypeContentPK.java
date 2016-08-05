package enterprises.orbital.evekit.sde.ram;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the raminstallationtypecontents database table.
 * 
 */
@Embeddable
public class RamInstallationTypeContentPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               installationTypeID;
  private int               assemblyLineTypeID;

  public RamInstallationTypeContentPK() {}

  public RamInstallationTypeContentPK(int installationTypeID, int assemblyLineTypeID) {
    super();
    this.installationTypeID = installationTypeID;
    this.assemblyLineTypeID = assemblyLineTypeID;
  }

  public int getInstallationTypeID() {
    return this.installationTypeID;
  }

  public int getAssemblyLineTypeID() {
    return this.assemblyLineTypeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof RamInstallationTypeContentPK)) { return false; }
    RamInstallationTypeContentPK castOther = (RamInstallationTypeContentPK) other;
    return (this.installationTypeID == castOther.installationTypeID) && (this.assemblyLineTypeID == castOther.assemblyLineTypeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.installationTypeID;
    hash = hash * prime + (this.assemblyLineTypeID);

    return hash;
  }

  @Override
  public String toString() {
    return "RamInstallationTypeContentPK [installationTypeID=" + installationTypeID + ", assemblyLineTypeID=" + assemblyLineTypeID + "]";
  }

}