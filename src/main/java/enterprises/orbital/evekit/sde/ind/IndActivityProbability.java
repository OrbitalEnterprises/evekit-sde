package enterprises.orbital.evekit.sde.ind;

import enterprises.orbital.evekit.sde.AttributeParameters;
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
 * The persistent class for the industryactivityprobabilities database table.
 * 
 */
@Entity
@Table(
    name = "industryactivityprobabilities")
public class IndActivityProbability {
  private static final Logger      log = Logger.getLogger(IndActivityProbability.class.getName());

  @EmbeddedId
  private IndActivityProbabilityPK id;
  private double                   probability;

  public IndActivityProbability() {}

  public IndActivityProbability(int typeID, int activityID, int productTypeID, double probability) {
    super();
    this.id = new IndActivityProbabilityPK(typeID, activityID, productTypeID);
    this.probability = probability;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getActivityID() {
    return id.getActivityID();
  }

  public int getProductTypeID() {
    return id.getProductTypeID();
  }

  public double getProbability() {
    return probability;
  }

  public static List<IndActivityProbability> access(
                                                    final int contid,
                                                    final int maxresults,
                                                    final AttributeSelector typeID,
                                                    final AttributeSelector activityID,
                                                    final AttributeSelector productTypeID,
                                                    final AttributeSelector probability) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM IndActivityProbability c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
        AttributeSelector.addIntSelector(qs, "c", "id.activityID", activityID);
        AttributeSelector.addIntSelector(qs, "c", "id.productTypeID", productTypeID);
        AttributeSelector.addDoubleSelector(qs, "c", "probability", probability);
        // Return result
        TypedQuery<IndActivityProbability> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), IndActivityProbability.class);
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
    return "IndActivityProbability [id=" + id + ", probability=" + probability + "]";
  }

}