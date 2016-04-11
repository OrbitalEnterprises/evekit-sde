package enterprises.orbital.evekit.sde.map;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the mapregionjumps database table.
 * 
 */
@Entity
@Table(
    name = "mapregionjumps")
@Immutable
public class MapRegionJump {
  private static final Logger log = Logger.getLogger(MapRegionJump.class.getName());

  @EmbeddedId
  private MapRegionJumpPK     id;

  public MapRegionJump() {}

  public MapRegionJumpPK getId() {
    return this.id;
  }

  public static List<MapRegionJump> access(
                                           final int contid,
                                           final int maxresults,
                                           final AttributeSelector fromRegionID,
                                           final AttributeSelector toRegionID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapRegionJump>>() {
        @Override
        public List<MapRegionJump> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapRegionJump c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.fromRegionID", fromRegionID);
          AttributeSelector.addIntSelector(qs, "c", "id.toRegionID", toRegionID);
          // Return result
          TypedQuery<MapRegionJump> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapRegionJump.class);
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
    return "MapRegionJump [id=" + id + "]";
  }

}