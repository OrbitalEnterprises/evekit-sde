package enterprises.orbital.evekit.sde.ind;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invblueprinttypes database table.
 * 
 */
@Entity
@Table(
    name = "industryblueprints")
public class IndBlueprint {
  private static final Logger log = Logger.getLogger(IndBlueprint.class.getName());

  @Id
  private int                 typeID;
  private Integer             maxProductionLimit;

  public IndBlueprint() {}

  public IndBlueprint(int typeID, Integer maxProductionLimit) {
    super();
    this.typeID = typeID;
    this.maxProductionLimit = maxProductionLimit;
  }

  public int getTypeID() {
    return typeID;
  }

  public Integer getMaxProductionLimit() {
    return maxProductionLimit;
  }

  public static List<IndBlueprint> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector typeID,
                                          final AttributeSelector maxProductionLimit) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<IndBlueprint>>() {
        @Override
        public List<IndBlueprint> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM IndBlueprint c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "maxProductionLimit", maxProductionLimit);
          // Return result
          TypedQuery<IndBlueprint> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), IndBlueprint.class);
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
    return "IndBlueprint [typeID=" + typeID + ", maxProductionLimit=" + maxProductionLimit + "]";
  }

}