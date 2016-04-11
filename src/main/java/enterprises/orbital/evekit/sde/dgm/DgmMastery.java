package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmmasteries database table.
 * 
 */
@Entity
@Table(
    name = "dgmmasteries")
@Immutable
public class DgmMastery {
  private static final Logger log = Logger.getLogger(DgmMastery.class.getName());

  @Id
  private int                 masteryID;
  private int                 certificateID;
  private byte                grade;

  public DgmMastery() {}

  public int getMasteryID() {
    return this.masteryID;
  }

  public int getCertificateID() {
    return this.certificateID;
  }

  public byte getGrade() {
    return this.grade;
  }

  public static List<DgmMastery> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector masteryID,
                                        final AttributeSelector certificateID,
                                        final AttributeSelector grade) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmMastery>>() {
        @Override
        public List<DgmMastery> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmMastery c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "masteryID", masteryID);
          AttributeSelector.addIntSelector(qs, "c", "certificateID", certificateID);
          AttributeSelector.addIntSelector(qs, "c", "grade", grade);
          // Return result
          TypedQuery<DgmMastery> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmMastery.class);
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
    return "DgmMastery [masteryID=" + masteryID + ", certificateID=" + certificateID + ", grade=" + grade + "]";
  }

}