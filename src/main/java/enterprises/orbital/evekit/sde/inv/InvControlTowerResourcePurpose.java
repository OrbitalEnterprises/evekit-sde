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
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invcontroltowerresourcepurposes database table.
 * 
 */
@Entity
@Table(
    name = "invcontroltowerresourcepurposes")
@Immutable
public class InvControlTowerResourcePurpose {
  private static final Logger log = Logger.getLogger(InvControlTowerResourcePurpose.class.getName());

  @Id
  private byte                purpose;
  private String              purposeText;

  public InvControlTowerResourcePurpose() {}

  public byte getPurpose() {
    return this.purpose;
  }

  public String getPurposeText() {
    return this.purposeText;
  }

  public static List<InvControlTowerResourcePurpose> access(
                                                            final int contid,
                                                            final int maxresults,
                                                            final AttributeSelector purpose,
                                                            final AttributeSelector purposeText) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvControlTowerResourcePurpose>>() {
        @Override
        public List<InvControlTowerResourcePurpose> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvControlTowerResourcePurpose c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "purpose", purpose);
          AttributeSelector.addStringSelector(qs, "c", "purposeText", purposeText, p);
          // Return result
          TypedQuery<InvControlTowerResourcePurpose> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(),
                                                                                                             InvControlTowerResourcePurpose.class);
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
    return "InvControlTowerResourcePurpose [purpose=" + purpose + ", purposeText=" + purposeText + "]";
  }

}