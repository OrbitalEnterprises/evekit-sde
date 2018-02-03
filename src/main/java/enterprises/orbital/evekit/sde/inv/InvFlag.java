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
 * The persistent class for the invflags database table.
 * 
 */
@Entity
@Table(
    name = "invflags")
public class InvFlag {
  private static final Logger log = Logger.getLogger(InvFlag.class.getName());

  @Id
  private int                 flagID;
  private String              flagName;
  private String              flagText;
  private int                 orderID;

  public InvFlag() {}

  public InvFlag(int flagID, String flagName, String flagText, int orderID) {
    super();
    this.flagID = flagID;
    this.flagName = flagName;
    this.flagText = flagText;
    this.orderID = orderID;
  }

  public int getFlagID() {
    return this.flagID;
  }

  public String getFlagName() {
    return this.flagName;
  }

  public String getFlagText() {
    return this.flagText;
  }

  public int getOrderID() {
    return this.orderID;
  }

  public static List<InvFlag> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector flagID,
                                     final AttributeSelector flagName,
                                     final AttributeSelector flagText,
                                     final AttributeSelector orderID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM InvFlag c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "flagID", flagID);
        AttributeSelector.addStringSelector(qs, "c", "flagName", flagName, p);
        AttributeSelector.addStringSelector(qs, "c", "flagText", flagText, p);
        AttributeSelector.addIntSelector(qs, "c", "orderID", orderID);
        // Return result
        TypedQuery<InvFlag> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvFlag.class);
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
    return "InvFlag [flagID=" + flagID + ", flagName=" + flagName + ", flagText=" + flagText + ", orderID=" + orderID + "]";
  }

}