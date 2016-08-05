package enterprises.orbital.evekit.sde.ram;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the ramassemblylinestations database table.
 * 
 */
@Embeddable
public class RamAssemblyLineStationPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private int               stationID;
  private int               assemblyLineTypeID;

  public RamAssemblyLineStationPK() {}

  public RamAssemblyLineStationPK(int stationID, int assemblyLineTypeID) {
    super();
    this.stationID = stationID;
    this.assemblyLineTypeID = assemblyLineTypeID;
  }

  public int getStationID() {
    return this.stationID;
  }

  public int getAssemblyLineTypeID() {
    return this.assemblyLineTypeID;
  }

  @Override
  public boolean equals(
                        Object other) {
    if (this == other) { return true; }
    if (!(other instanceof RamAssemblyLineStationPK)) { return false; }
    RamAssemblyLineStationPK castOther = (RamAssemblyLineStationPK) other;
    return (this.stationID == castOther.stationID) && (this.assemblyLineTypeID == castOther.assemblyLineTypeID);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.stationID;
    hash = hash * prime + (this.assemblyLineTypeID);

    return hash;
  }

  @Override
  public String toString() {
    return "RamAssemblyLineStationPK [stationID=" + stationID + ", assemblyLineTypeID=" + assemblyLineTypeID + "]";
  }

}