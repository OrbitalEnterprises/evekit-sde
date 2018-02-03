package enterprises.orbital.evekit.sde.ram;

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
 * The persistent class for the ramassemblylinetypes database table.
 * 
 */
@Entity
@Table(
    name = "ramassemblylinetypes")
public class RamAssemblyLineType {
  private static final Logger log = Logger.getLogger(RamAssemblyLineType.class.getName());

  @Id
  private int                 assemblyLineTypeID;
  private String              assemblyLineTypeName;
  private String              description;
  private double              baseTimeMultiplier;
  private double              baseMaterialMultiplier;
  private double              baseCostMultiplier;
  private double              volume;
  private int                activityID;
  private Double              minCostPerHour;

  public RamAssemblyLineType() {}

  public RamAssemblyLineType(int assemblyLineTypeID, int activityID, String assemblyLineTypeName, double baseCostMultiplier, double baseMaterialMultiplier,
                             double baseTimeMultiplier, String description, Double minCostPerHour, double volume) {
    super();
    this.assemblyLineTypeID = assemblyLineTypeID;
    this.activityID = activityID;
    this.assemblyLineTypeName = assemblyLineTypeName;
    this.baseCostMultiplier = baseCostMultiplier;
    this.baseMaterialMultiplier = baseMaterialMultiplier;
    this.baseTimeMultiplier = baseTimeMultiplier;
    this.description = description;
    this.minCostPerHour = minCostPerHour;
    this.volume = volume;
  }

  public int getAssemblyLineTypeID() {
    return this.assemblyLineTypeID;
  }

  public int getActivityID() {
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
      return SDE.getFactory().runTransaction(() -> {
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