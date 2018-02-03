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
 * The persistent class for the invnames database table.
 * 
 */
@Entity
@Table(
    name = "invnames")
public class InvName {
  private static final Logger log = Logger.getLogger(InvName.class.getName());

  @Id
  private long                itemID;
  private String              itemName;

  public InvName() {}

  public InvName(long itemID, String itemName) {
    super();
    this.itemID = itemID;
    this.itemName = itemName;
  }

  public long getItemID() {
    return this.itemID;
  }

  public String getItemName() {
    return this.itemName;
  }

  public static List<InvName> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector itemID,
                                     final AttributeSelector itemName) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM InvName c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addLongSelector(qs, "c", "itemID", itemID);
        AttributeSelector.addStringSelector(qs, "c", "itemName", itemName, p);
        // Return result
        TypedQuery<InvName> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvName.class);
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
    return "InvName [itemID=" + itemID + ", itemName=" + itemName + "]";
  }

}