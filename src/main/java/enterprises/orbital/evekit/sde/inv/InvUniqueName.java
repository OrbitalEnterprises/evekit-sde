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
 * The persistent class for the invuniquenames database table.
 * 
 */
@Entity
@Table(
    name = "invuniquenames")
public class InvUniqueName {
  private static final Logger log = Logger.getLogger(InvUniqueName.class.getName());

  @Id
  private int                 itemID;
  private int                 groupID;
  private String              itemName;

  public InvUniqueName() {}

  public InvUniqueName(int itemID, int groupID, String itemName) {
    super();
    this.itemID = itemID;
    this.groupID = groupID;
    this.itemName = itemName;
  }

  public int getItemID() {
    return this.itemID;
  }

  public int getGroupID() {
    return this.groupID;
  }

  public String getItemName() {
    return this.itemName;
  }

  public static List<InvUniqueName> access(
                                           final int contid,
                                           final int maxresults,
                                           final AttributeSelector itemID,
                                           final AttributeSelector groupID,
                                           final AttributeSelector itemName) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvUniqueName>>() {
        @Override
        public List<InvUniqueName> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvUniqueName c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "itemID", itemID);
          AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
          AttributeSelector.addStringSelector(qs, "c", "itemName", itemName, p);
          // Return result
          TypedQuery<InvUniqueName> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvUniqueName.class);
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
    return "InvUniqueName [itemID=" + itemID + ", groupID=" + groupID + ", itemName=" + itemName + "]";
  }

}