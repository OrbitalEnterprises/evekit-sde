package enterprises.orbital.evekit.sde.sta;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the stastations database table.
 * 
 */
@Entity
@Table(
    name = "stastations")
@Immutable
public class StaStation {
  private static final Logger log = Logger.getLogger(StaStation.class.getName());

  @Id
  private int                 stationID;
  private int                 constellationID;
  private int                 corporationID;
  private double              dockingCostPerVolume;
  private double              maxShipVolumeDockable;
  private int                 officeRentalCost;
  private byte                operationID;
  private int                 regionID;
  private double              reprocessingEfficiency;
  private byte                reprocessingHangarFlag;
  private double              reprocessingStationsTake;
  private short               security;
  private int                 solarSystemID;
  private String              stationName;
  private int                 stationTypeID;
  private double              x;
  private double              y;
  private double              z;

  public StaStation() {}

  public int getStationID() {
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

  public byte getOperationID() {
    return this.operationID;
  }

  public int getRegionID() {
    return this.regionID;
  }

  public double getReprocessingEfficiency() {
    return this.reprocessingEfficiency;
  }

  public byte getReprocessingHangarFlag() {
    return this.reprocessingHangarFlag;
  }

  public double getReprocessingStationsTake() {
    return this.reprocessingStationsTake;
  }

  public short getSecurity() {
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
      return SDE.getFactory().runTransaction(new RunInTransaction<List<StaStation>>() {
        @Override
        public List<StaStation> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM StaStation c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "stationID", stationID);
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
        }
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