package enterprises.orbital.evekit.sde.ind;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the industryactivity database table.
 * 
 */
@Embeddable
public class IndActivityPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               activityID;

  public IndActivityPK() {}

  public IndActivityPK(int typeID, int activityID) {
    super();
    this.typeID = typeID;
    this.activityID = activityID;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getActivityID() {
    return activityID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + activityID;
    result = prime * result + typeID;
    return result;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IndActivityPK other = (IndActivityPK) obj;
    if (activityID != other.activityID) return false;
    if (typeID != other.typeID) return false;
    return true;
  }

  @Override
  public String toString() {
    return "IndActivityPK [typeID=" + typeID + ", activityID=" + activityID + "]";
  }

}