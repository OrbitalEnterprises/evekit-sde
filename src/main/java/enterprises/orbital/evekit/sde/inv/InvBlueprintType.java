package enterprises.orbital.evekit.sde.inv;

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
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invblueprinttypes database table.
 * 
 */
@Entity
@Table(
    name = "invblueprinttypes")
@Immutable
public class InvBlueprintType {
  private static final Logger log = Logger.getLogger(InvBlueprintType.class.getName());

  @Id
  private int                 blueprintTypeID;
  private Integer             duplicatingTime;
  private Integer             inventionTime;
  private Short               materialModifier;
  private Integer             maxProductionLimit;
  private Integer             parentBlueprintTypeID;
  private Integer             productionTime;
  private Integer             productivityModifier;
  private Integer             productTypeID;
  private Integer             researchCopyTime;
  private Integer             researchMaterialTime;
  private Integer             researchProductivityTime;
  private Integer             researchTechTime;
  private Integer             reverseEngineeringTime;
  private Short               techLevel;
  private Short               wasteFactor;

  public InvBlueprintType() {}

  public int getBlueprintTypeID() {
    return this.blueprintTypeID;
  }

  public Integer getDuplicatingTime() {
    return this.duplicatingTime;
  }

  public Integer getInventionTime() {
    return this.inventionTime;
  }

  public Short getMaterialModifier() {
    return this.materialModifier;
  }

  public Integer getMaxProductionLimit() {
    return this.maxProductionLimit;
  }

  public Integer getParentBlueprintTypeID() {
    return this.parentBlueprintTypeID;
  }

  public Integer getProductionTime() {
    return this.productionTime;
  }

  public Integer getProductivityModifier() {
    return this.productivityModifier;
  }

  public Integer getProductTypeID() {
    return this.productTypeID;
  }

  public Integer getResearchCopyTime() {
    return this.researchCopyTime;
  }

  public Integer getResearchMaterialTime() {
    return this.researchMaterialTime;
  }

  public Integer getResearchProductivityTime() {
    return this.researchProductivityTime;
  }

  public Integer getResearchTechTime() {
    return this.researchTechTime;
  }

  public Integer getReverseEngineeringTime() {
    return this.reverseEngineeringTime;
  }

  public Short getTechLevel() {
    return this.techLevel;
  }

  public Short getWasteFactor() {
    return this.wasteFactor;
  }

  public static List<InvBlueprintType> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector blueprintTypeID,
                                              final AttributeSelector duplicatingTime,
                                              final AttributeSelector inventionTime,
                                              final AttributeSelector materialModifier,
                                              final AttributeSelector maxProductionLimit,
                                              final AttributeSelector parentBlueprintTypeID,
                                              final AttributeSelector productionTime,
                                              final AttributeSelector productivityModifier,
                                              final AttributeSelector productTypeID,
                                              final AttributeSelector researchCopyTime,
                                              final AttributeSelector researchMaterialTime,
                                              final AttributeSelector researchProductivityTime,
                                              final AttributeSelector researchTechTime,
                                              final AttributeSelector reverseEngineeringTime,
                                              final AttributeSelector techLevel,
                                              final AttributeSelector wasteFactor) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvBlueprintType>>() {
        @Override
        public List<InvBlueprintType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvBlueprintType c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "blueprintTypeID", blueprintTypeID);
          AttributeSelector.addIntSelector(qs, "c", "duplicatingTime", duplicatingTime);
          AttributeSelector.addIntSelector(qs, "c", "inventionTime", inventionTime);
          AttributeSelector.addIntSelector(qs, "c", "materialModifier", materialModifier);
          AttributeSelector.addIntSelector(qs, "c", "maxProductionLimit", maxProductionLimit);
          AttributeSelector.addIntSelector(qs, "c", "parentBlueprintTypeID", parentBlueprintTypeID);
          AttributeSelector.addIntSelector(qs, "c", "productionTime", productionTime);
          AttributeSelector.addIntSelector(qs, "c", "productivityModifier", productivityModifier);
          AttributeSelector.addIntSelector(qs, "c", "productTypeID", productTypeID);
          AttributeSelector.addIntSelector(qs, "c", "researchCopyTime", researchCopyTime);
          AttributeSelector.addIntSelector(qs, "c", "researchMaterialTime", researchMaterialTime);
          AttributeSelector.addIntSelector(qs, "c", "researchProductivityTime", researchProductivityTime);
          AttributeSelector.addIntSelector(qs, "c", "researchTechTime", researchTechTime);
          AttributeSelector.addIntSelector(qs, "c", "reverseEngineeringTime", reverseEngineeringTime);
          AttributeSelector.addIntSelector(qs, "c", "techLevel", techLevel);
          AttributeSelector.addIntSelector(qs, "c", "wasteFactor", wasteFactor);
          // Return result
          TypedQuery<InvBlueprintType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvBlueprintType.class);
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
    return "InvBlueprintType [blueprintTypeID=" + blueprintTypeID + ", duplicatingTime=" + duplicatingTime + ", inventionTime=" + inventionTime
        + ", materialModifier=" + materialModifier + ", maxProductionLimit=" + maxProductionLimit + ", parentBlueprintTypeID=" + parentBlueprintTypeID
        + ", productionTime=" + productionTime + ", productivityModifier=" + productivityModifier + ", productTypeID=" + productTypeID + ", researchCopyTime="
        + researchCopyTime + ", researchMaterialTime=" + researchMaterialTime + ", researchProductivityTime=" + researchProductivityTime + ", researchTechTime="
        + researchTechTime + ", reverseEngineeringTime=" + reverseEngineeringTime + ", techLevel=" + techLevel + ", wasteFactor=" + wasteFactor + "]";
  }

}