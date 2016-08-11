package enterprises.orbital.evekit.sde.inv;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

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
  private byte                useBasePrice;
  private byte                anchored;
  private byte                anchorable;
  private byte                fittableNonSingleton;
  private byte                published;

  public InvGroup() {}

  public InvGroup(int groupID, byte anchorable, byte anchored, int categoryID, byte fittableNonSingleton, String groupName, Integer iconID, byte published,
                  byte useBasePrice) {
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

  public byte getAnchorable() {
    return this.anchorable;
  }

  public byte getAnchored() {
    return this.anchored;
  }

  public int getCategoryID() {
    return this.categoryID;
  }

  public byte getFittableNonSingleton() {
    return this.fittableNonSingleton;
  }

  public String getGroupName() {
    return this.groupName;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public byte getPublished() {
    return this.published;
  }

  public byte getUseBasePrice() {
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
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvGroup>>() {
        @Override
        public List<InvGroup> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvGroup c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
          AttributeSelector.addIntSelector(qs, "c", "anchorable", anchorable);
          AttributeSelector.addIntSelector(qs, "c", "anchored", anchored);
          AttributeSelector.addIntSelector(qs, "c", "categoryID", categoryID);
          AttributeSelector.addIntSelector(qs, "c", "fittableNonSingleton", fittableNonSingleton);
          AttributeSelector.addStringSelector(qs, "c", "groupName", groupName, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "published", published);
          AttributeSelector.addIntSelector(qs, "c", "useBasePrice", useBasePrice);
          // Return result
          TypedQuery<InvGroup> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvGroup.class);
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
    return "InvGroup [groupID=" + groupID + ", anchorable=" + anchorable + ", anchored=" + anchored + ", categoryID=" + categoryID + ", fittableNonSingleton="
        + fittableNonSingleton + ", groupName=" + groupName + ", iconID=" + iconID + ", published=" + published + ", useBasePrice=" + useBasePrice + "]";
  }

}