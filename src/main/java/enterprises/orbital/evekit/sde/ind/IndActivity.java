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
 * The persistent class for the industryactivity database table.
 * 
 */
@Entity
@Table(
    name = "industryactivity")
public class IndActivity {
  private static final Logger log = Logger.getLogger(IndActivity.class.getName());

  @EmbeddedId
  private IndActivityPK       id;
  private int                 time;

  public IndActivity() {}

  public IndActivity(int typeID, int activityID, int time) {
    super();
    this.id = new IndActivityPK(typeID, activityID);
    this.time = time;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getActivityID() {
    return id.getActivityID();
  }

  public int getTime() {
    return time;
  }

  public static List<IndActivity> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector typeID,
                                         final AttributeSelector activityID,
                                         final AttributeSelector time) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM IndActivity c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
        AttributeSelector.addIntSelector(qs, "c", "id.activityID", activityID);
        AttributeSelector.addIntSelector(qs, "c", "time", time);
        // Return result
        TypedQuery<IndActivity> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), IndActivity.class);
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
    return "IndActivity [id=" + id + ", time=" + time + "]";
  }

}