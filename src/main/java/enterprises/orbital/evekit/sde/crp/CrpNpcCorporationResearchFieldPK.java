package enterprises.orbital.evekit.sde.crp;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the crpnpccorporationresearchfields database table.
 * 
 */
@Embeddable
public class CrpNpcCorporationResearchFieldPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               skillID;
  private int               corporationID;

  public CrpNpcCorporationResearchFieldPK() {}

  public int getSkillID() {
    return this.skillID;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof CrpNpcCorporationResearchFieldPK)) { return false; }
    CrpNpcCorporationResearchFieldPK castOther = (CrpNpcCorporationResearchFieldPK) other;
    return (this.skillID == castOther.skillID) && (this.corporationID == castOther.corporationID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.skillID;
    hash = hash * prime + this.corporationID;

    return hash;
  }

  @Override
  public String toString() {
    return "CrpNpcCorporationResearchFieldPK [skillID=" + skillID + ", corporationID=" + corporationID + "]";
  }

}