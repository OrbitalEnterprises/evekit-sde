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
 * The persistent class for the ramassemblylinetypedetailpergroup database table.
 * 
 */
@Entity
@Table(
    name = "ramassemblylinetypedetailpergroup")
public class RamAssemblyLineTypeDetailPerGroup {
  private static final Logger                 log = Logger.getLogger(RamAssemblyLineTypeDetailPerGroup.class.getName());

  @EmbeddedId
  private RamAssemblyLineTypeDetailPerGroupPK id;
  private double                              timeMultiplier;
  private double                              materialMultiplier;
  private double                              costMultiplier;

  public RamAssemblyLineTypeDetailPerGroup() {}

  public RamAssemblyLineTypeDetailPerGroup(int assemblyLineTypeID, int groupID, double costMultiplier, double materialMultiplier, double timeMultiplier) {
    super();
    this.id = new RamAssemblyLineTypeDetailPerGroupPK(assemblyLineTypeID, groupID);
    this.costMultiplier = costMultiplier;
    this.materialMultiplier = materialMultiplier;
    this.timeMultiplier = timeMultiplier;
  }

  public RamAssemblyLineTypeDetailPerGroupPK id() {
    return this.id;
  }

  public int getAssemblyLineTypeID() {
    return id.getAssemblyLineTypeID();
  }

  public int getGroupID() {
    return id.getGroupID();
  }

  public double getCostMultiplier() {
    return this.costMultiplier;
  }

  public double getMaterialMultiplier() {
    return this.materialMultiplier;
  }

  public double getTimeMultiplier() {
    return this.timeMultiplier;
  }

  public static List<RamAssemblyLineTypeDetailPerGroup> access(
                                                               final int contid,
                                                               final int maxresults,
                                                               final AttributeSelector assemblyLineTypeID,
                                                               final AttributeSelector groupID,
                                                               final AttributeSelector costMultiplier,
                                                               final AttributeSelector materialMultiplier,
                                                               final AttributeSelector timeMultiplier) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM RamAssemblyLineTypeDetailPerGroup c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "id.assemblyLineTypeID", assemblyLineTypeID);
        AttributeSelector.addIntSelector(qs, "c", "id.groupID", groupID);
        AttributeSelector.addDoubleSelector(qs, "c", "costMultiplier", costMultiplier);
        AttributeSelector.addDoubleSelector(qs, "c", "materialMultiplier", materialMultiplier);
        AttributeSelector.addDoubleSelector(qs, "c", "timeMultiplier", timeMultiplier);
        // Return result
        TypedQuery<RamAssemblyLineTypeDetailPerGroup> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(),
                                                                                                              RamAssemblyLineTypeDetailPerGroup.class);
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
    return "RamAssemblyLineTypeDetailPerGroup [id=" + id + ", costMultiplier=" + costMultiplier + ", materialMultiplier=" + materialMultiplier
        + ", timeMultiplier=" + timeMultiplier + "]";
  }

}