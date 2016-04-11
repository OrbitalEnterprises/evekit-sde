package enterprises.orbital.evekit.sde.ram;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the ramassemblylinetypedetailpercategory database table.
 * 
 */
@Embeddable
public class RamAssemblyLineTypeDetailPerCategoryPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               assemblyLineTypeID;
  private int               categoryID;

  public RamAssemblyLineTypeDetailPerCategoryPK() {}

  public int getAssemblyLineTypeID() {
    return this.assemblyLineTypeID;
  }

  public int getCategoryID() {
    return this.categoryID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof RamAssemblyLineTypeDetailPerCategoryPK)) { return false; }
    RamAssemblyLineTypeDetailPerCategoryPK castOther = (RamAssemblyLineTypeDetailPerCategoryPK) other;
    return (this.assemblyLineTypeID == castOther.assemblyLineTypeID) && (this.categoryID == castOther.categoryID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + (this.assemblyLineTypeID);
    hash = hash * prime + this.categoryID;

    return hash;
  }

  @Override
  public String toString() {
    return "RamAssemblyLineTypeDetailPerCategoryPK [assemblyLineTypeID=" + assemblyLineTypeID + ", categoryID=" + categoryID + "]";
  }

}