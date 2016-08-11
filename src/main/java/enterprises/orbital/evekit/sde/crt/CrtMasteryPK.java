package enterprises.orbital.evekit.sde.crt;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the certmasteries database table.
 * 
 */
@Embeddable
public class CrtMasteryPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               masteryLevel;
  private int               certID;

  public CrtMasteryPK() {}

  public CrtMasteryPK(int typeID, int masteryLevel, int certID) {
    super();
    this.typeID = typeID;
    this.masteryLevel = masteryLevel;
    this.certID = certID;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getMasteryLevel() {
    return masteryLevel;
  }

  public int getCertID() {
    return certID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + certID;
    result = prime * result + masteryLevel;
    result = prime * result + typeID;
    return result;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CrtMasteryPK other = (CrtMasteryPK) obj;
    if (certID != other.certID) return false;
    if (masteryLevel != other.masteryLevel) return false;
    if (typeID != other.typeID) return false;
    return true;
  }

  @Override
  public String toString() {
    return "CrtMasteryPK [typeID=" + typeID + ", masteryLevel=" + masteryLevel + ", certID=" + certID + "]";
  }

}