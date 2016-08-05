package enterprises.orbital.evekit.sde.plt;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the planetschematicstypemap database table.
 * 
 */
@Embeddable
public class PltSchematicsTypeMapPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               schematicID;
  private int               typeID;

  public PltSchematicsTypeMapPK() {}

  public PltSchematicsTypeMapPK(int schematicID, int typeID) {
    super();
    this.schematicID = schematicID;
    this.typeID = typeID;
  }

  public int getSchematicID() {
    return this.schematicID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof PltSchematicsTypeMapPK)) { return false; }
    PltSchematicsTypeMapPK castOther = (PltSchematicsTypeMapPK) other;
    return (this.schematicID == castOther.schematicID) && (this.typeID == castOther.typeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.schematicID;
    hash = hash * prime + this.typeID;

    return hash;
  }

  @Override
  public String toString() {
    return "PltSchematicsTypeMapPK [schematicID=" + schematicID + ", typeID=" + typeID + "]";
  }

}