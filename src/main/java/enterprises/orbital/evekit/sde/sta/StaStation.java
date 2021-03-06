package enterprises.orbital.evekit.sde.sta;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the stastations database table.
 * 
 */
@Entity
@Table(
    name = "stastations")
public class StaStation {
  private static final Logger log = Logger.getLogger(StaStation.class.getName());

  @Id
  private long                stationID;
  private double              security;
  private double              dockingCostPerVolume;
  private double              maxShipVolumeDockable;
  private int                 officeRentalCost;
  private int                operationID;
  private int                 stationTypeID;
  private int                 corporationID;
  private int                 solarSystemID;
  private int                 constellationID;
  private int                 regionID;
  private String              stationName;
  private double              x;
  private double              y;
  private double              z;
  private double              reprocessingEfficiency;
  private double              reprocessingStationsTake;
  private int                reprocessingHangarFlag;

  public StaStation() {}

  public StaStation(long stationID, int constellationID, int corporationID, double dockingCostPerVolume, double maxShipVolumeDockable, int officeRentalCost,
                    int operationID, int regionID, double reprocessingEfficiency, int reprocessingHangarFlag, double reprocessingStationsTake,
                    double security, int solarSystemID, String stationName, int stationTypeID, double x, double y, double z) {
    super();
    this.stationID = stationID;
    this.constellationID = constellationID;
    this.corporationID = corporationID;
    this.dockingCostPerVolume = dockingCostPerVolume;
    this.maxShipVolumeDockable = maxShipVolumeDockable;
    this.officeRentalCost = officeRentalCost;
    this.operationID = operationID;
    this.regionID = regionID;
    this.reprocessingEfficiency = reprocessingEfficiency;
    this.reprocessingHangarFlag = reprocessingHangarFlag;
    this.reprocessingStationsTake = reprocessingStationsTake;
    this.security = security;
    this.solarSystemID = solarSystemID;
    this.stationName = stationName;
    this.stationTypeID = stationTypeID;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public long getStationID() {
    return this.stationID;
  }

  public int getConstellationID() {
    return this.constellationID;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  public double getDockingCostPerVolume() {
    return this.dockingCostPerVolume;
  }

  public double getMaxShipVolumeDockable() {
    return this.maxShipVolumeDockable;
  }

  public int getOfficeRentalCost() {
    return this.officeRentalCost;
  }

  public int getOperationID() {
    return this.operationID;
  }

  public int getRegionID() {
    return this.regionID;
  }

  public double getReprocessingEfficiency() {
    return this.reprocessingEfficiency;
  }

  public int getReprocessingHangarFlag() {
    return this.reprocessingHangarFlag;
  }

  public double getReprocessingStationsTake() {
    return this.reprocessingStationsTake;
  }

  public double getSecurity() {
    return this.security;
  }

  public int getSolarSystemID() {
    return this.solarSystemID;
  }

  public String getStationName() {
    return this.stationName;
  }

  public int getStationTypeID() {
    return this.stationTypeID;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }

  public static List<StaStation> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector stationID,
                                        final AttributeSelector constellationID,
                                        final AttributeSelector corporationID,
                                        final AttributeSelector dockingCostPerVolume,
                                        final AttributeSelector maxShipVolumeDockable,
                                        final AttributeSelector officeRentalCost,
                                        final AttributeSelector operationID,
                                        final AttributeSelector regionID,
                                        final AttributeSelector reprocessingEfficiency,
                                        final AttributeSelector reprocessingHangarFlag,
                                        final AttributeSelector reprocessingStationsTake,
                                        final AttributeSelector security,
                                        final AttributeSelector solarSystemID,
                                        final AttributeSelector stationName,
                                        final AttributeSelector stationTypeID,
                                        final AttributeSelector x,
                                        final AttributeSelector y,
                                        final AttributeSelector z) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM StaStation c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addLongSelector(qs, "c", "stationID", stationID);
        AttributeSelector.addIntSelector(qs, "c", "constellationID", constellationID);
        AttributeSelector.addIntSelector(qs, "c", "corporationID", corporationID);
        AttributeSelector.addDoubleSelector(qs, "c", "dockingCostPerVolume", dockingCostPerVolume);
        AttributeSelector.addDoubleSelector(qs, "c", "maxShipVolumeDockable", maxShipVolumeDockable);
        AttributeSelector.addIntSelector(qs, "c", "officeRentalCost", officeRentalCost);
        AttributeSelector.addIntSelector(qs, "c", "operationID", operationID);
        AttributeSelector.addIntSelector(qs, "c", "regionID", regionID);
        AttributeSelector.addDoubleSelector(qs, "c", "reprocessingEfficiency", reprocessingEfficiency);
        AttributeSelector.addIntSelector(qs, "c", "reprocessingHangarFlag", reprocessingHangarFlag);
        AttributeSelector.addDoubleSelector(qs, "c", "reprocessingStationsTake", reprocessingStationsTake);
        AttributeSelector.addIntSelector(qs, "c", "security", security);
        AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
        AttributeSelector.addStringSelector(qs, "c", "stationName", stationName, p);
        AttributeSelector.addIntSelector(qs, "c", "stationTypeID", stationTypeID);
        AttributeSelector.addDoubleSelector(qs, "c", "x", x);
        AttributeSelector.addDoubleSelector(qs, "c", "y", y);
        AttributeSelector.addDoubleSelector(qs, "c", "z", z);
        // Return result
        TypedQuery<StaStation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), StaStation.class);
        p.fillParams(query);
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
    return "StaStation [stationID=" + stationID + ", constellationID=" + constellationID + ", corporationID=" + corporationID + ", dockingCostPerVolume="
        + dockingCostPerVolume + ", maxShipVolumeDockable=" + maxShipVolumeDockable + ", officeRentalCost=" + officeRentalCost + ", operationID=" + operationID
        + ", regionID=" + regionID + ", reprocessingEfficiency=" + reprocessingEfficiency + ", reprocessingHangarFlag=" + reprocessingHangarFlag
        + ", reprocessingStationsTake=" + reprocessingStationsTake + ", security=" + security + ", solarSystemID=" + solarSystemID + ", stationName="
        + stationName + ", stationTypeID=" + stationTypeID + ", x=" + x + ", y=" + y + ", z=" + z + "]";
  }

}