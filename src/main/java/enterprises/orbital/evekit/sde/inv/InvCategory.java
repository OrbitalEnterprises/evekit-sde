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
 * The persistent class for the invcategories database table.
 * 
 */
@Entity
@Table(
    name = "invcategories")
public class InvCategory {
  private static final Logger log = Logger.getLogger(InvCategory.class.getName());

  @Id
  private int                 categoryID;
  private String              categoryName;
  private String              description;
  private Integer             iconID;
  private byte                published;

  public InvCategory() {}

  public InvCategory(int categoryID, String categoryName, String description, Integer iconID, byte published) {
    super();
    this.categoryID = categoryID;
    this.categoryName = categoryName;
    this.description = description;
    this.iconID = iconID;
    this.published = published;
  }

  public int getCategoryID() {
    return this.categoryID;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public byte getPublished() {
    return this.published;
  }

  public static List<InvCategory> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector categoryID,
                                         final AttributeSelector categoryName,
                                         final AttributeSelector description,
                                         final AttributeSelector iconID,
                                         final AttributeSelector published) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvCategory>>() {
        @Override
        public List<InvCategory> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvCategory c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "categoryID", categoryID);
          AttributeSelector.addStringSelector(qs, "c", "categoryName", categoryName, p);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "published", published);
          // Return result
          TypedQuery<InvCategory> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvCategory.class);
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
    return "InvCategory [categoryID=" + categoryID + ", categoryName=" + categoryName + ", description=" + description + ", iconID=" + iconID + ", published="
        + published + "]";
  }

}