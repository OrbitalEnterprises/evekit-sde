package enterprises.orbital.evekit.sde.inv;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invcontroltowerresources database table.
 * 
 */
@Entity
@Table(
    name = "invcontroltowerresources")
public class InvControlTowerResource {
  private static final Logger       log = Logger.getLogger(InvControlTowerResource.class.getName());

  @EmbeddedId
  private InvControlTowerResourcePK id;
  private Integer                   factionID;
  private Double                    minSecurityLevel;
  private byte                      purpose;
  private int                       quantity;

  public InvControlTowerResource() {}

  public InvControlTowerResource(int controlTowerTypeID, int resourceTypeID, Integer factionID, Double minSecurityLevel, byte purpose, int quantity) {
    super();
    this.id = new InvControlTowerResourcePK(controlTowerTypeID, resourceTypeID);
    this.factionID = factionID;
    this.minSecurityLevel = minSecurityLevel;
    this.purpose = purpose;
    this.quantity = quantity;
  }

  public InvControlTowerResourcePK id() {
    return this.id;
  }

  public int getControlTowerTypeID() {
    return id.getControlTowerTypeID();
  }

  public int getResourceTypeID() {
    return id.getResourceTypeID();
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public Double getMinSecurityLevel() {
    return this.minSecurityLevel;
  }

  public byte getPurpose() {
    return this.purpose;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public static List<InvControlTowerResource> access(
                                                     final int contid,
                                                     final int maxresults,
                                                     final AttributeSelector controlTowerTypeID,
                                                     final AttributeSelector resourceTypeID,
                                                     final AttributeSelector factionID,
                                                     final AttributeSelector minSecurityLevel,
                                                     final AttributeSelector purpose,
                                                     final AttributeSelector quantity) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvControlTowerResource>>() {
        @Override
        public List<InvControlTowerResource> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvControlTowerResource c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.controlTowerTypeID", controlTowerTypeID);
          AttributeSelector.addIntSelector(qs, "c", "id.resourceTypeID", resourceTypeID);
          AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
          AttributeSelector.addDoubleSelector(qs, "c", "minSecurityLevel", minSecurityLevel);
          AttributeSelector.addIntSelector(qs, "c", "purpose", purpose);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          // Return result
          TypedQuery<InvControlTowerResource> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvControlTowerResource.class);
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
    return "InvControlTowerResource [id=" + id + ", factionID=" + factionID + ", minSecurityLevel=" + minSecurityLevel + ", purpose=" + purpose + ", quantity="
        + quantity + "]";
  }

}