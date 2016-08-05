package enterprises.orbital.evekit.sde.ram;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the ramassemblylinetypedetailpercategory database table.
 * 
 */
@Entity
@Table(
    name = "ramassemblylinetypedetailpercategory")
public class RamAssemblyLineTypeDetailPerCategory {
  private static final Logger                    log = Logger.getLogger(RamAssemblyLineTypeDetailPerCategory.class.getName());

  @EmbeddedId
  private RamAssemblyLineTypeDetailPerCategoryPK id;
  private double                                 costMultiplier;
  private double                                 materialMultiplier;
  private double                                 timeMultiplier;

  public RamAssemblyLineTypeDetailPerCategory() {}

  public RamAssemblyLineTypeDetailPerCategory(int assemblyLineTypeID, int categoryID, double costMultiplier, double materialMultiplier, double timeMultiplier) {
    super();
    this.id = new RamAssemblyLineTypeDetailPerCategoryPK(assemblyLineTypeID, categoryID);
    this.costMultiplier = costMultiplier;
    this.materialMultiplier = materialMultiplier;
    this.timeMultiplier = timeMultiplier;
  }

  public RamAssemblyLineTypeDetailPerCategoryPK id() {
    return this.id;
  }

  public int getAssemblyLineTypeID() {
    return id.getAssemblyLineTypeID();
  }

  public int getCategoryID() {
    return id.getCategoryID();
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

  public static List<RamAssemblyLineTypeDetailPerCategory> access(
                                                                  final int contid,
                                                                  final int maxresults,
                                                                  final AttributeSelector assemblyLineTypeID,
                                                                  final AttributeSelector categoryID,
                                                                  final AttributeSelector costMultiplier,
                                                                  final AttributeSelector materialMultiplier,
                                                                  final AttributeSelector timeMultiplier) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<RamAssemblyLineTypeDetailPerCategory>>() {
        @Override
        public List<RamAssemblyLineTypeDetailPerCategory> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM RamAssemblyLineTypeDetailPerCategory c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.assemblyLineTypeID", assemblyLineTypeID);
          AttributeSelector.addIntSelector(qs, "c", "id.categoryID", categoryID);
          AttributeSelector.addDoubleSelector(qs, "c", "costMultiplier", costMultiplier);
          AttributeSelector.addDoubleSelector(qs, "c", "materialMultiplier", materialMultiplier);
          AttributeSelector.addDoubleSelector(qs, "c", "timeMultiplier", timeMultiplier);
          // Return result
          TypedQuery<RamAssemblyLineTypeDetailPerCategory> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(),
                                                                                                                   RamAssemblyLineTypeDetailPerCategory.class);
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
    return "RamAssemblyLineTypeDetailPerCategory [id=" + id + ", costMultiplier=" + costMultiplier + ", materialMultiplier=" + materialMultiplier
        + ", timeMultiplier=" + timeMultiplier + "]";
  }

}