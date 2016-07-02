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
 * The persistent class for the stastationtypes database table.
 * 
 */
@Entity
@Table(
    name = "stastationtypes")
@Immutable
public class StaStationType {
  private static final Logger log = Logger.getLogger(StaStationType.class.getName());
  @Id
  private int                 stationTypeID;
  private byte                conquerable;
  private double              dockEntryX;
  private double              dockEntryY;
  private double              dockEntryZ;
  private double              dockOrientationX;
  private double              dockOrientationY;
  private double              dockOrientationZ;
  private Byte                officeSlots;
  private Byte                operationID;
  private Double              reprocessingEfficiency;

  public StaStationType() {}

  public int getStationTypeID() {
    return this.stationTypeID;
  }

  public byte getConquerable() {
    return this.conquerable;
  }

  public double getDockEntryX() {
    return this.dockEntryX;
  }

  public double getDockEntryY() {
    return this.dockEntryY;
  }

  public double getDockEntryZ() {
    return this.dockEntryZ;
  }

  public double getDockOrientationX() {
    return this.dockOrientationX;
  }

  public double getDockOrientationY() {
    return this.dockOrientationY;
  }

  public double getDockOrientationZ() {
    return this.dockOrientationZ;
  }

  public Byte getOfficeSlots() {
    return this.officeSlots;
  }

  public Byte getOperationID() {
    return this.operationID;
  }

  public Double getReprocessingEfficiency() {
    return this.reprocessingEfficiency;
  }

  public static List<StaStationType> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector stationTypeID,
                                            final AttributeSelector conquerable,
                                            final AttributeSelector dockEntryX,
                                            final AttributeSelector dockEntryY,
                                            final AttributeSelector dockEntryZ,
                                            final AttributeSelector dockOrientationX,
                                            final AttributeSelector dockOrientationY,
                                            final AttributeSelector dockOrientationZ,
                                            final AttributeSelector officeSlots,
                                            final AttributeSelector operationID,
                                            final AttributeSelector reprocessingEfficiency) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<StaStationType>>() {
        @Override
        public List<StaStationType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM StaStationType c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "stationTypeID", stationTypeID);
          AttributeSelector.addIntSelector(qs, "c", "conquerable", conquerable);
          AttributeSelector.addDoubleSelector(qs, "c", "dockEntryX", dockEntryX);
          AttributeSelector.addDoubleSelector(qs, "c", "dockEntryY", dockEntryY);
          AttributeSelector.addDoubleSelector(qs, "c", "dockEntryZ", dockEntryZ);
          AttributeSelector.addDoubleSelector(qs, "c", "dockOrientationX", dockOrientationX);
          AttributeSelector.addDoubleSelector(qs, "c", "dockOrientationY", dockOrientationY);
          AttributeSelector.addDoubleSelector(qs, "c", "dockOrientationZ", dockOrientationZ);
          AttributeSelector.addIntSelector(qs, "c", "officeSlots", officeSlots);
          AttributeSelector.addIntSelector(qs, "c", "operationID", operationID);
          AttributeSelector.addDoubleSelector(qs, "c", "reprocessingEfficiency", reprocessingEfficiency);
          // Return result
          TypedQuery<StaStationType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), StaStationType.class);
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
    return "StaStationType [stationTypeID=" + stationTypeID + ", conquerable=" + conquerable + ", dockEntryX=" + dockEntryX + ", dockEntryY=" + dockEntryY
        + ", dockEntryZ=" + dockEntryZ + ", dockOrientationX=" + dockOrientationX + ", dockOrientationY=" + dockOrientationY + ", dockOrientationZ="
        + dockOrientationZ + ", officeSlots=" + officeSlots + ", operationID=" + operationID + ", reprocessingEfficiency=" + reprocessingEfficiency + "]";
  }

}