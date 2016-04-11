package enterprises.orbital.evekit.sde.plt;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the planetschematicspinmap database table.
 * 
 */
@Embeddable
public class PltSchematicsPinMapPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               schematicID;
  private int               pinTypeID;

  public PltSchematicsPinMapPK() {}

  public int getSchematicID() {
    return this.schematicID;
  }

  public int getPinTypeID() {
    return this.pinTypeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof PltSchematicsPinMapPK)) { return false; }
    PltSchematicsPinMapPK castOther = (PltSchematicsPinMapPK) other;
    return (this.schematicID == castOther.schematicID) && (this.pinTypeID == castOther.pinTypeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.schematicID;
    hash = hash * prime + this.pinTypeID;

    return hash;
  }

  @Override
  public String toString() {
    return "PltSchematicsPinMapPK [schematicID=" + schematicID + ", pinTypeID=" + pinTypeID + "]";
  }

}