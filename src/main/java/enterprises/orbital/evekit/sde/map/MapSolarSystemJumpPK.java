package enterprises.orbital.evekit.sde.map;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the mapsolarsystemjumps database table.
 * 
 */
@Embeddable
public class MapSolarSystemJumpPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               fromSolarSystemID;
  private int               toSolarSystemID;

  public MapSolarSystemJumpPK() {}

  public int getFromSolarSystemID() {
    return this.fromSolarSystemID;
  }

  public int getToSolarSystemID() {
    return this.toSolarSystemID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof MapSolarSystemJumpPK)) { return false; }
    MapSolarSystemJumpPK castOther = (MapSolarSystemJumpPK) other;
    return (this.fromSolarSystemID == castOther.fromSolarSystemID) && (this.toSolarSystemID == castOther.toSolarSystemID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.fromSolarSystemID;
    hash = hash * prime + this.toSolarSystemID;

    return hash;
  }

  @Override
  public String toString() {
    return "MapSolarSystemJumpPK [fromSolarSystemID=" + fromSolarSystemID + ", toSolarSystemID=" + toSolarSystemID + "]";
  }

}