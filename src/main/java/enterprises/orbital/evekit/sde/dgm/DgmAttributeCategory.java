package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmattributecategories database table.
 * 
 */
@Entity
@Table(
    name = "dgmattributecategories")
public class DgmAttributeCategory {
  private static final Logger log = Logger.getLogger(DgmAttributeCategory.class.getName());

  @Id
  private int                categoryID;
  private String              categoryName;
  private String              categoryDescription;

  public DgmAttributeCategory() {}

  public DgmAttributeCategory(int categoryID, String categoryDescription, String categoryName) {
    super();
    this.categoryID = categoryID;
    this.categoryDescription = categoryDescription;
    this.categoryName = categoryName;
  }

  public int getCategoryID() {
    return this.categoryID;
  }

  public String getCategoryDescription() {
    return this.categoryDescription;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  public static List<DgmAttributeCategory> access(
                                                  final int contid,
                                                  final int maxresults,
                                                  final AttributeSelector categoryID,
                                                  final AttributeSelector categoryDescription,
                                                  final AttributeSelector categoryName) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM DgmAttributeCategory c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "categoryID", categoryID);
        AttributeSelector.addStringSelector(qs, "c", "categoryDescription", categoryDescription, p);
        AttributeSelector.addStringSelector(qs, "c", "categoryName", categoryName, p);
        // Return result
        TypedQuery<DgmAttributeCategory> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmAttributeCategory.class);
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
    return "DgmAttributeCategory [categoryID=" + categoryID + ", categoryDescription=" + categoryDescription + ", categoryName=" + categoryName + "]";
  }

}