package enterprises.orbital.evekit.sde.crt;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the certmasteries database table.
 * 
 */
@Entity
@Table(
    name = "certmasteries")
public class CrtMastery {
  private static final Logger log = Logger.getLogger(CrtMastery.class.getName());

  @EmbeddedId
  private CrtMasteryPK        id;

  public CrtMastery() {}

  public CrtMastery(int typeID, int masteryLevel, int certID) {
    super();
    this.id = new CrtMasteryPK(typeID, masteryLevel, certID);
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getMasteryLevel() {
    return id.getMasteryLevel();
  }

  public int getCertID() {
    return id.getCertID();
  }

  public static List<CrtMastery> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector typeID,
                                        final AttributeSelector masteryLevel,
                                        final AttributeSelector certID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrtMastery>>() {
        @Override
        public List<CrtMastery> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrtMastery c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.masteryLevel", masteryLevel);
          AttributeSelector.addIntSelector(qs, "c", "id.certID", certID);
          // Return result
          TypedQuery<CrtMastery> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtMastery.class);
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
    return "CrtMastery [id=" + id + "]";
  }

}