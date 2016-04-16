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
 * The persistent class for the invgroups database table.
 * 
 */
@Entity
@Table(
    name = "invgroups")
@Immutable
public class InvGroup {
  private static final Logger log = Logger.getLogger(InvGroup.class.getName());

  @Id
  private int                 groupID;
  private Byte                allowManufacture;
  private Byte                allowRecycler;
  private byte                anchorable;
  private byte                anchored;
  private int                 categoryID;
  private String              description;
  private byte                fittableNonSingleton;
  private String              groupName;
  private Integer             iconID;
  private byte                published;
  private byte                useBasePrice;

  public InvGroup() {}

  public int getGroupID() {
    return this.groupID;
  }

  public Byte getAllowManufacture() {
    return this.allowManufacture;
  }

  public Byte getAllowRecycler() {
    return this.allowRecycler;
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

  public String getDescription() {
    return this.description;
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
                                      final AttributeSelector allowManufacture,
                                      final AttributeSelector allowRecycler,
                                      final AttributeSelector anchorable,
                                      final AttributeSelector anchored,
                                      final AttributeSelector categoryID,
                                      final AttributeSelector description,
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
          AttributeSelector.addIntSelector(qs, "c", "allowManufacture", allowManufacture);
          AttributeSelector.addIntSelector(qs, "c", "allowRecycler", allowRecycler);
          AttributeSelector.addIntSelector(qs, "c", "anchorable", anchorable);
          AttributeSelector.addIntSelector(qs, "c", "anchored", anchored);
          AttributeSelector.addIntSelector(qs, "c", "categoryID", categoryID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
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
    return "InvGroup [groupID=" + groupID + ", allowManufacture=" + allowManufacture + ", allowRecycler=" + allowRecycler + ", anchorable=" + anchorable
        + ", anchored=" + anchored + ", categoryID=" + categoryID + ", description=" + description + ", fittableNonSingleton=" + fittableNonSingleton
        + ", groupName=" + groupName + ", iconID=" + iconID + ", published=" + published + ", useBasePrice=" + useBasePrice + "]";
  }

}