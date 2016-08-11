package enterprises.orbital.evekit.sde.crt;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the certskills database table.
 * 
 */
@Embeddable
public class CrtSkillPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;
  private int               certID;
  private int               skillID;
  private int               certLevelInt;
  private int               skillLevel;

  public CrtSkillPK() {}

  public CrtSkillPK(int certID, int skillID, int certLevelInt, int skillLevel) {
    super();
    this.certID = certID;
    this.skillID = skillID;
    this.certLevelInt = certLevelInt;
    this.skillLevel = skillLevel;
  }

  public int getCertID() {
    return certID;
  }

  public int getSkillID() {
    return skillID;
  }

  public int getCertLevelInt() {
    return certLevelInt;
  }

  public int getSkillLevel() {
    return skillLevel;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + certID;
    result = prime * result + certLevelInt;
    result = prime * result + skillID;
    result = prime * result + skillLevel;
    return result;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CrtSkillPK other = (CrtSkillPK) obj;
    if (certID != other.certID) return false;
    if (certLevelInt != other.certLevelInt) return false;
    if (skillID != other.skillID) return false;
    if (skillLevel != other.skillLevel) return false;
    return true;
  }

  @Override
  public String toString() {
    return "CrtSkillPK [certID=" + certID + ", skillID=" + skillID + ", certLevelInt=" + certLevelInt + ", skillLevel=" + skillLevel + "]";
  }

}