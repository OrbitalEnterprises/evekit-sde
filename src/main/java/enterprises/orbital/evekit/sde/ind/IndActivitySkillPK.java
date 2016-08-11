package enterprises.orbital.evekit.sde.ind;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the industryactivityskill database table.
 * 
 */
@Embeddable
public class IndActivitySkillPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               activityID;
  private int               skillID;

  public IndActivitySkillPK() {}

  public IndActivitySkillPK(int typeID, int activityID, int skillID) {
    super();
    this.typeID = typeID;
    this.activityID = activityID;
    this.skillID = skillID;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getActivityID() {
    return activityID;
  }

  public int getSkillID() {
    return skillID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + activityID;
    result = prime * result + skillID;
    result = prime * result + typeID;
    return result;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IndActivitySkillPK other = (IndActivitySkillPK) obj;
    if (activityID != other.activityID) return false;
    if (skillID != other.skillID) return false;
    if (typeID != other.typeID) return false;
    return true;
  }

  @Override
  public String toString() {
    return "IndActivitySkillPK [typeID=" + typeID + ", activityID=" + activityID + ", skillID=" + skillID + "]";
  }

}