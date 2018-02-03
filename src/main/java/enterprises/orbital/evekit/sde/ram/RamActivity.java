package enterprises.orbital.evekit.sde.ram;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the ramactivities database table.
 * 
 */
@Entity
@Table(
    name = "ramactivities")
public class RamActivity {
  private static final Logger log = Logger.getLogger(RamActivity.class.getName());

  @Id
  private int                activityID;
  private String              activityName;
  private String              iconNo;
  private String              description;
  private boolean                published;

  public RamActivity() {}

  public RamActivity(int activityID, String activityName, String description, String iconNo, boolean published) {
    super();
    this.activityID = activityID;
    this.activityName = activityName;
    this.description = description;
    this.iconNo = iconNo;
    this.published = published;
  }

  public int getActivityID() {
    return this.activityID;
  }

  public String getActivityName() {
    return this.activityName;
  }

  public String getDescription() {
    return this.description;
  }

  public String getIconNo() {
    return this.iconNo;
  }

  public boolean isPublished() {
    return this.published;
  }

  public static List<RamActivity> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector activityID,
                                         final AttributeSelector activityName,
                                         final AttributeSelector description,
                                         final AttributeSelector iconNo,
                                         final AttributeSelector published) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM RamActivity c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "activityID", activityID);
        AttributeSelector.addStringSelector(qs, "c", "activityName", activityName, p);
        AttributeSelector.addStringSelector(qs, "c", "description", description, p);
        AttributeSelector.addStringSelector(qs, "c", "iconNo", iconNo, p);
        AttributeSelector.addBooleanSelector(qs, "c", "published", published);
        // Return result
        TypedQuery<RamActivity> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), RamActivity.class);
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
    return "RamActivity [activityID=" + activityID + ", activityName=" + activityName + ", description=" + description + ", iconNo=" + iconNo + ", published="
        + published + "]";
  }

}