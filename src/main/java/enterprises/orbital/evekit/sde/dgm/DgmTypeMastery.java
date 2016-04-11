package enterprises.orbital.evekit.sde.dgm;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the dgmtypemasteries database table.
 * 
 */
@Entity
@Table(
    name = "dgmtypemasteries")
@Immutable
public class DgmTypeMastery {
  private static final Logger log = Logger.getLogger(DgmTypeMastery.class.getName());

  @EmbeddedId
  private DgmTypeMasteryPK    id;

  public DgmTypeMastery() {}

  public DgmTypeMasteryPK getId() {
    return this.id;
  }

  public static List<DgmTypeMastery> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector typeID,
                                            final AttributeSelector masteryID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmTypeMastery>>() {
        @Override
        public List<DgmTypeMastery> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmTypeMastery c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.masteryID", masteryID);
          // Return result
          TypedQuery<DgmTypeMastery> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmTypeMastery.class);
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
    return "DgmTypeMastery [id=" + id + "]";
  }

}