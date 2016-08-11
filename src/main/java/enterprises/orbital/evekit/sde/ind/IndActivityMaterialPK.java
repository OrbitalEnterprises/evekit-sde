package enterprises.orbital.evekit.sde.ind;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the industryactivitymaterial database table.
 * 
 */
@Embeddable
public class IndActivityMaterialPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               activityID;
  private int               materialTypeID;

  public IndActivityMaterialPK() {}

  public IndActivityMaterialPK(int typeID, int activityID, int materialTypeID) {
    super();
    this.typeID = typeID;
    this.activityID = activityID;
    this.materialTypeID = materialTypeID;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getActivityID() {
    return activityID;
  }

  public int getMaterialTypeID() {
    return materialTypeID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + activityID;
    result = prime * result + materialTypeID;
    result = prime * result + typeID;
    return result;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IndActivityMaterialPK other = (IndActivityMaterialPK) obj;
    if (activityID != other.activityID) return false;
    if (materialTypeID != other.materialTypeID) return false;
    if (typeID != other.typeID) return false;
    return true;
  }

  @Override
  public String toString() {
    return "IndActivityMaterialPK [typeID=" + typeID + ", activityID=" + activityID + ", materialTypeID=" + materialTypeID + "]";
  }

}