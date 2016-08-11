package enterprises.orbital.evekit.sde.skn;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the skinship database table.
 * 
 */
@Entity
@Table(
    name = "skinship")
public class SknShip {
  private static final Logger log = Logger.getLogger(SknShip.class.getName());

  @EmbeddedId
  private SknShipPK           id;

  public SknShip() {}

  public SknShip(int skinID, int typeID) {
    super();
    this.id = new SknShipPK(skinID, typeID);
  }

  public int getSkinID() {
    return id.getSkinID();
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public static List<SknShip> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector skinID,
                                     final AttributeSelector typeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<SknShip>>() {
        @Override
        public List<SknShip> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM SknShip c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.skinID", skinID);
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          // Return result
          TypedQuery<SknShip> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), SknShip.class);
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
    return "SknShip [id=" + id + "]";
  }

}