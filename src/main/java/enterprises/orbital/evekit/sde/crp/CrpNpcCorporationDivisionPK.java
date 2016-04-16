package enterprises.orbital.evekit.sde.crp;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the crpnpccorporationdivisions database table.
 * 
 */
@Embeddable
public class CrpNpcCorporationDivisionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               corporationID;
  private byte              divisionID;

  public CrpNpcCorporationDivisionPK() {}

  public int getCorporationID() {
    return this.corporationID;
  }

  public byte getDivisionID() {
    return this.divisionID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof CrpNpcCorporationDivisionPK)) { return false; }
    CrpNpcCorporationDivisionPK castOther = (CrpNpcCorporationDivisionPK) other;
    return (this.corporationID == castOther.corporationID) && (this.divisionID == castOther.divisionID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.corporationID;
    hash = hash * prime + (this.divisionID);

    return hash;
  }

  @Override
  public String toString() {
    return "CrpNpcCorporationDivisionPK [corporationID=" + corporationID + ", divisionID=" + divisionID + "]";
  }

}