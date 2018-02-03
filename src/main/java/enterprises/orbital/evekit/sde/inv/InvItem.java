package enterprises.orbital.evekit.sde.inv;

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
 * The persistent class for the invitems database table.
 * 
 */
@Entity
@Table(
    name = "invitems")
public class InvItem {
  private static final Logger log = Logger.getLogger(InvItem.class.getName());

  @Id
  private long                itemID;
  private int                 typeID;
  private int                 ownerID;
  private long                locationID;
  private int                 flagID;
  private int                 quantity;

  public InvItem() {}

  public InvItem(long itemID, int flagID, long locationID, int ownerID, int quantity, int typeID) {
    super();
    this.itemID = itemID;
    this.flagID = flagID;
    this.locationID = locationID;
    this.ownerID = ownerID;
    this.quantity = quantity;
    this.typeID = typeID;
  }

  public long getItemID() {
    return this.itemID;
  }

  public int getFlagID() {
    return this.flagID;
  }

  public long getLocationID() {
    return this.locationID;
  }

  public int getOwnerID() {
    return this.ownerID;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public static List<InvItem> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector itemID,
                                     final AttributeSelector flagID,
                                     final AttributeSelector locationID,
                                     final AttributeSelector ownerID,
                                     final AttributeSelector quantity,
                                     final AttributeSelector typeID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM InvItem c WHERE 1 = 1");
        AttributeSelector.addLongSelector(qs, "c", "itemID", itemID);
        AttributeSelector.addIntSelector(qs, "c", "flagID", flagID);
        AttributeSelector.addDoubleSelector(qs, "c", "locationID", locationID);
        AttributeSelector.addIntSelector(qs, "c", "ownerID", ownerID);
        AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
        AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
        // Return result
        TypedQuery<InvItem> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvItem.class);
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
    return "InvItem [itemID=" + itemID + ", flagID=" + flagID + ", locationID=" + locationID + ", ownerID=" + ownerID + ", quantity=" + quantity + ", typeID="
        + typeID + "]";
  }

}