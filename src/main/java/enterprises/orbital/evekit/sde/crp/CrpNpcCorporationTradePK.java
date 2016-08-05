package enterprises.orbital.evekit.sde.crp;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the crpnpccorporationtrades database table.
 * 
 */
@Embeddable
public class CrpNpcCorporationTradePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               corporationID;
  private int               typeID;

  public CrpNpcCorporationTradePK() {}

  public CrpNpcCorporationTradePK(int corporationID, int typeID) {
    super();
    this.corporationID = corporationID;
    this.typeID = typeID;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof CrpNpcCorporationTradePK)) { return false; }
    CrpNpcCorporationTradePK castOther = (CrpNpcCorporationTradePK) other;
    return (this.corporationID == castOther.corporationID) && (this.typeID == castOther.typeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.corporationID;
    hash = hash * prime + this.typeID;

    return hash;
  }

  @Override
  public String toString() {
    return "CrpNpcCorporationTradePK [corporationID=" + corporationID + ", typeID=" + typeID + "]";
  }

}