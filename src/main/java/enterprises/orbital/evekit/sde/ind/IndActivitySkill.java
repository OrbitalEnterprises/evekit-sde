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
 * The persistent class for the industryactivityskills database table.
 * 
 */
@Entity
@Table(
    name = "industryactivityskills")
public class IndActivitySkill {
  private static final Logger log = Logger.getLogger(IndActivitySkill.class.getName());

  @EmbeddedId
  private IndActivitySkillPK  id;
  private int                 level;

  public IndActivitySkill() {}

  public IndActivitySkill(int typeID, int activityID, int skillID, int level) {
    super();
    this.id = new IndActivitySkillPK(typeID, activityID, skillID);
    this.level = level;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getActivityID() {
    return id.getActivityID();
  }

  public int getSkillID() {
    return id.getSkillID();
  }

  public int getLevel() {
    return level;
  }

  public static List<IndActivitySkill> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector typeID,
                                              final AttributeSelector activityID,
                                              final AttributeSelector skillID,
                                              final AttributeSelector level) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM IndActivitySkill c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
        AttributeSelector.addIntSelector(qs, "c", "id.activityID", activityID);
        AttributeSelector.addIntSelector(qs, "c", "id.skillID", skillID);
        AttributeSelector.addIntSelector(qs, "c", "level", level);
        // Return result
        TypedQuery<IndActivitySkill> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), IndActivitySkill.class);
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
    return "IndActivitySkill [id=" + id + ", level=" + level + "]";
  }

}
