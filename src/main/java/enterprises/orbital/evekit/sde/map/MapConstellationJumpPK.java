package enterprises.orbital.evekit.sde.map;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the mapconstellationjumps database table.
 * 
 */
@Embeddable
public class MapConstellationJumpPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               fromConstellationID;
  private int               toConstellationID;

  public MapConstellationJumpPK() {}

  public int getFromConstellationID() {
    return this.fromConstellationID;
  }

  public int getToConstellationID() {
    return this.toConstellationID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof MapConstellationJumpPK)) { return false; }
    MapConstellationJumpPK castOther = (MapConstellationJumpPK) other;
    return (this.fromConstellationID == castOther.fromConstellationID) && (this.toConstellationID == castOther.toConstellationID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.fromConstellationID;
    hash = hash * prime + this.toConstellationID;

    return hash;
  }

  @Override
  public String toString() {
    return "MapConstellationJumpPK [fromConstellationID=" + fromConstellationID + ", toConstellationID=" + toConstellationID + "]";
  }

}