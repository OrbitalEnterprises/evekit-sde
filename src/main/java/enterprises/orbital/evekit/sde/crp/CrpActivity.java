package enterprises.orbital.evekit.sde.crp;

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
 * The persistent class for the crpactivities database table.
 * 
 */
@Entity
@Table(
    name = "crpactivities")
@Immutable
public class CrpActivity {
  private static final Logger log = Logger.getLogger(CrpActivity.class.getName());

  @Id
  private byte                activityID;
  private String              activityName;
  private String              description;

  public CrpActivity() {}

  public byte getActivityID() {
    return this.activityID;
  }

  public String getActivityName() {
    return this.activityName;
  }

  public String getDescription() {
    return this.description;
  }

  public static List<CrpActivity> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector activityID,
                                         final AttributeSelector activityName,
                                         final AttributeSelector description) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrpActivity>>() {
        @Override
        public List<CrpActivity> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrpActivity c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "activityID", activityID);
          AttributeSelector.addStringSelector(qs, "c", "activityName", activityName, p);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          // Return result
          TypedQuery<CrpActivity> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrpActivity.class);
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
    return "CrpActivity [activityID=" + activityID + ", activityName=" + activityName + ", description=" + description + "]";
  }

}