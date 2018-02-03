package enterprises.orbital.evekit.sde.inv;

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
 * The persistent class for the invgroups database table.
 * 
 */
@Entity
@Table(
    name = "invgroups")
public class InvGroup {
  private static final Logger log = Logger.getLogger(InvGroup.class.getName());

  @Id
  private int                 groupID;
  private int                 categoryID;
  private String              groupName;
  private Integer             iconID;
  private boolean                useBasePrice;
  private boolean                anchored;
  private boolean                anchorable;
  private boolean                fittableNonSingleton;
  private boolean                published;

  public InvGroup() {}

  public InvGroup(int groupID, boolean anchorable, boolean anchored, int categoryID, boolean fittableNonSingleton, String groupName, Integer iconID, boolean published,
                  boolean useBasePrice) {
    super();
    this.groupID = groupID;
    this.anchorable = anchorable;
    this.anchored = anchored;
    this.categoryID = categoryID;
    this.fittableNonSingleton = fittableNonSingleton;
    this.groupName = groupName;
    this.iconID = iconID;
    this.published = published;
    this.useBasePrice = useBasePrice;
  }

  public int getGroupID() {
    return this.groupID;
  }

  public boolean isAnchorable() {
    return this.anchorable;
  }

  public boolean isAnchored() {
    return this.anchored;
  }

  public int getCategoryID() {
    return this.categoryID;
  }

  public boolean isFittableNonSingleton() {
    return this.fittableNonSingleton;
  }

  public String getGroupName() {
    return this.groupName;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public boolean isPublished() {
    return this.published;
  }

  public boolean isUseBasePrice() {
    return this.useBasePrice;
  }

  public static List<InvGroup> access(
                                      final int contid,
                                      final int maxresults,
                                      final AttributeSelector groupID,
                                      final AttributeSelector anchorable,
                                      final AttributeSelector anchored,
                                      final AttributeSelector categoryID,
                                      final AttributeSelector fittableNonSingleton,
                                      final AttributeSelector groupName,
                                      final AttributeSelector iconID,
                                      final AttributeSelector published,
                                      final AttributeSelector useBasePrice) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM InvGroup c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
        AttributeSelector.addBooleanSelector(qs, "c", "anchorable", anchorable);
        AttributeSelector.addBooleanSelector(qs, "c", "anchored", anchored);
        AttributeSelector.addIntSelector(qs, "c", "categoryID", categoryID);
        AttributeSelector.addBooleanSelector(qs, "c", "fittableNonSingleton", fittableNonSingleton);
        AttributeSelector.addStringSelector(qs, "c", "groupName", groupName, p);
        AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
        AttributeSelector.addBooleanSelector(qs, "c", "published", published);
        AttributeSelector.addBooleanSelector(qs, "c", "useBasePrice", useBasePrice);
        // Return result
        TypedQuery<InvGroup> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvGroup.class);
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
    return "InvGroup [groupID=" + groupID + ", anchorable=" + anchorable + ", anchored=" + anchored + ", categoryID=" + categoryID + ", fittableNonSingleton="
        + fittableNonSingleton + ", groupName=" + groupName + ", iconID=" + iconID + ", published=" + published + ", useBasePrice=" + useBasePrice + "]";
  }

}