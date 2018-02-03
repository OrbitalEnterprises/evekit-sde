package enterprises.orbital.evekit.sde.ram;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the ramassemblylinestations database table.
 * 
 */
@Entity
@Table(
    name = "ramassemblylinestations")
public class RamAssemblyLineStation {
  private static final Logger      log = Logger.getLogger(RamAssemblyLineStation.class.getName());

  @EmbeddedId
  private RamAssemblyLineStationPK id;
  private int                     quantity;
  private int                      stationTypeID;
  private int                      ownerID;
  private int                      solarSystemID;
  private int                      regionID;

  public RamAssemblyLineStation() {}

  public RamAssemblyLineStation(int stationID, int assemblyLineTypeID, int ownerID, int quantity, int regionID, int solarSystemID, int stationTypeID) {
    super();
    this.id = new RamAssemblyLineStationPK(stationID, assemblyLineTypeID);
    this.ownerID = ownerID;
    this.quantity = quantity;
    this.regionID = regionID;
    this.solarSystemID = solarSystemID;
    this.stationTypeID = stationTypeID;
  }

  public RamAssemblyLineStationPK id() {
    return this.id;
  }

  public int getStationID() {
    return id.getStationID();
  }

  public int getAssemblyLineTypeID() {
    return id.getAssemblyLineTypeID();
  }

  public int getOwnerID() {
    return this.ownerID;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public int getRegionID() {
    return this.regionID;
  }

  public int getSolarSystemID() {
    return this.solarSystemID;
  }

  public int getStationTypeID() {
    return this.stationTypeID;
  }

  public static List<RamAssemblyLineStation> access(
                                                    final int contid,
                                                    final int maxresults,
                                                    final AttributeSelector stationID,
                                                    final AttributeSelector assemblyLineTypeID,
                                                    final AttributeSelector ownerID,
                                                    final AttributeSelector quantity,
                                                    final AttributeSelector regionID,
                                                    final AttributeSelector solarSystemID,
                                                    final AttributeSelector stationTypeID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM RamAssemblyLineStation c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "id.stationID", stationID);
        AttributeSelector.addIntSelector(qs, "c", "id.assemblyLineTypeID", assemblyLineTypeID);
        AttributeSelector.addIntSelector(qs, "c", "ownerID", ownerID);
        AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
        AttributeSelector.addIntSelector(qs, "c", "regionID", regionID);
        AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
        AttributeSelector.addIntSelector(qs, "c", "stationTypeID", stationTypeID);
        // Return result
        TypedQuery<RamAssemblyLineStation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), RamAssemblyLineStation.class);
        query.setMaxResults(maxcount);
        query.setFirstResult(offset);
        return query.getResultList();
      });
    } catch (Exception e) {
      log.log(Level.SEVERE, "query error", e);
    }
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    return "RamAssemblyLineStation [id=" + id + ", ownerID=" + ownerID + ", quantity=" + quantity + ", regionID=" + regionID + ", solarSystemID="
        + solarSystemID + ", stationTypeID=" + stationTypeID + "]";
  }

}