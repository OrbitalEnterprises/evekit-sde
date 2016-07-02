package enterprises.orbital.evekit.sde.ram;

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
 * The persistent class for the ramassemblylinetypes database table.
 * 
 */
@Entity
@Table(
    name = "ramassemblylinetypes")
@Immutable
public class RamAssemblyLineType {
  private static final Logger log = Logger.getLogger(RamAssemblyLineType.class.getName());

  @Id
  private int                 assemblyLineTypeID;
  private byte                activityID;
  private String              assemblyLineTypeName;
  private double              baseCostMultiplier;
  private double              baseMaterialMultiplier;
  private double              baseTimeMultiplier;
  private String              description;
  private Double              minCostPerHour;
  private double              volume;

  public RamAssemblyLineType() {}

  public int getAssemblyLineTypeID() {
    return this.assemblyLineTypeID;
  }

  public byte getActivityID() {
    return this.activityID;
  }

  public String getAssemblyLineTypeName() {
    return this.assemblyLineTypeName;
  }

  public double getBaseCostMultiplier() {
    return this.baseCostMultiplier;
  }

  public double getBaseMaterialMultiplier() {
    return this.baseMaterialMultiplier;
  }

  public double getBaseTimeMultiplier() {
    return this.baseTimeMultiplier;
  }

  public String getDescription() {
    return this.description;
  }

  public Double getMinCostPerHour() {
    return this.minCostPerHour;
  }

  public double getVolume() {
    return this.volume;
  }

  public static List<RamAssemblyLineType> access(
                                                 final int contid,
                                                 final int maxresults,
                                                 final AttributeSelector assemblyLineTypeID,
                                                 final AttributeSelector activityID,
                                                 final AttributeSelector assemblyLineTypeName,
                                                 final AttributeSelector baseCostMultiplier,
                                                 final AttributeSelector baseMaterialMultiplier,
                                                 final AttributeSelector baseTimeMultiplier,
                                                 final AttributeSelector description,
                                                 final AttributeSelector minCostPerHour,
                                                 final AttributeSelector volume) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<RamAssemblyLineType>>() {
        @Override
        public List<RamAssemblyLineType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM RamAssemblyLineType c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "assemblyLineTypeID", assemblyLineTypeID);
          AttributeSelector.addIntSelector(qs, "c", "activityID", activityID);
          AttributeSelector.addStringSelector(qs, "c", "assemblyLineTypeName", assemblyLineTypeName, p);
          AttributeSelector.addDoubleSelector(qs, "c", "baseCostMultiplier", baseCostMultiplier);
          AttributeSelector.addDoubleSelector(qs, "c", "baseMaterialMultiplier", baseMaterialMultiplier);
          AttributeSelector.addDoubleSelector(qs, "c", "baseTimeMultiplier", baseTimeMultiplier);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addDoubleSelector(qs, "c", "minCostPerHour", minCostPerHour);
          AttributeSelector.addDoubleSelector(qs, "c", "volume", volume);
          // Return result
          TypedQuery<RamAssemblyLineType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), RamAssemblyLineType.class);
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
    return "RamAssemblyLineType [assemblyLineTypeID=" + assemblyLineTypeID + ", activityID=" + activityID + ", assemblyLineTypeName=" + assemblyLineTypeName
        + ", baseCostMultiplier=" + baseCostMultiplier + ", baseMaterialMultiplier=" + baseMaterialMultiplier + ", baseTimeMultiplier=" + baseTimeMultiplier
        + ", description=" + description + ", minCostPerHour=" + minCostPerHour + ", volume=" + volume + "]";
  }

}