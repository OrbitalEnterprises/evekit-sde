package enterprises.orbital.evekit.sde.ind;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the industryactivityproduct database table.
 * 
 */
@Embeddable
public class IndActivityProductPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               typeID;
  private int               activityID;
  private int               productTypeID;

  public IndActivityProductPK() {}

  public IndActivityProductPK(int typeID, int activityID, int productTypeID) {
    super();
    this.typeID = typeID;
    this.activityID = activityID;
    this.productTypeID = productTypeID;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getActivityID() {
    return activityID;
  }

  public int getProductTypeID() {
    return productTypeID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + activityID;
    result = prime * result + productTypeID;
    result = prime * result + typeID;
    return result;
  }

  @Override
  public boolean equals(
                        Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IndActivityProductPK other = (IndActivityProductPK) obj;
    if (activityID != other.activityID) return false;
    if (productTypeID != other.productTypeID) return false;
    if (typeID != other.typeID) return false;
    return true;
  }

  @Override
  public String toString() {
    return "IndActivityProductPK [typeID=" + typeID + ", activityID=" + activityID + ", productTypeID=" + productTypeID + "]";
  }

}