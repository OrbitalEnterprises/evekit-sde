package enterprises.orbital.evekit.sde.map;

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
 * The persistent class for the mapjumps database table.
 * 
 */
@Entity
@Table(
    name = "mapjumps")
public class MapJump {
  private static final Logger log = Logger.getLogger(MapJump.class.getName());

  @Id
  private int                 stargateID;
  private int                 destinationID;

  public MapJump() {}

  public MapJump(int stargateID, int destinationID) {
    super();
    this.stargateID = stargateID;
    this.destinationID = destinationID;
  }

  public int getStargateID() {
    return this.stargateID;
  }

  public int getDestinationID() {
    return this.destinationID;
  }

  public static List<MapJump> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector stargateID,
                                     final AttributeSelector destinationID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapJump>>() {
        @Override
        public List<MapJump> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapJump c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "stargateID", stargateID);
          AttributeSelector.addIntSelector(qs, "c", "celestialID", destinationID);
          // Return result
          TypedQuery<MapJump> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapJump.class);
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
    return "MapJump [stargateID=" + stargateID + ", celestialID=" + destinationID + "]";
  }

}