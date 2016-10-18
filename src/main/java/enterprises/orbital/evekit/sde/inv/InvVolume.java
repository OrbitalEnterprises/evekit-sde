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
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invvolumes database table.
 * 
 */
@Entity
@Table(
    name = "invvolumes")
public class InvVolume {
  private static final Logger log = Logger.getLogger(InvVolume.class.getName());

  @Id
  private int                 typeID;
  private int                 volume;

  public InvVolume() {}

  public InvVolume(int typeID, int volume) {
    super();
    this.typeID = typeID;
    this.volume = volume;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getVolume() {
    return volume;
  }

  public static List<InvVolume> access(
                                       final int contid,
                                       final int maxresults,
                                       final AttributeSelector typeID,
                                       final AttributeSelector volume) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvVolume>>() {
        @Override
        public List<InvVolume> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvVolume c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "volume", volume);
          // Return result
          TypedQuery<InvVolume> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvVolume.class);
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
    return "InvVolume [typeID=" + typeID + ", volume=" + volume + "]";
  }

}