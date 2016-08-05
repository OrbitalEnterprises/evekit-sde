package enterprises.orbital.evekit.sde.map;

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
 * The persistent class for the mapsolarsystemjumps database table.
 * 
 */
@Entity
@Table(
    name = "mapsolarsystemjumps")
public class MapSolarSystemJump {
  private static final Logger  log = Logger.getLogger(MapSolarSystemJump.class.getName());

  @EmbeddedId
  private MapSolarSystemJumpPK id;
  private int                  fromConstellationID;
  private int                  fromRegionID;
  private int                  toConstellationID;
  private int                  toRegionID;

  public MapSolarSystemJump() {}

  public MapSolarSystemJump(int fromSolarSystemID, int toSolarSystemID, int fromConstellationID, int fromRegionID, int toConstellationID, int toRegionID) {
    super();
    this.id = new MapSolarSystemJumpPK(fromSolarSystemID, toSolarSystemID);
    this.fromConstellationID = fromConstellationID;
    this.fromRegionID = fromRegionID;
    this.toConstellationID = toConstellationID;
    this.toRegionID = toRegionID;
  }

  public MapSolarSystemJumpPK id() {
    return this.id;
  }

  public int getFromSolarSystemID() {
    return id.getFromSolarSystemID();
  }

  public int getToSolarSystemID() {
    return id.getToSolarSystemID();
  }

  public int getFromConstellationID() {
    return this.fromConstellationID;
  }

  public int getFromRegionID() {
    return this.fromRegionID;
  }

  public int getToConstellationID() {
    return this.toConstellationID;
  }

  public int getToRegionID() {
    return this.toRegionID;
  }

  public static List<MapSolarSystemJump> access(
                                                final int contid,
                                                final int maxresults,
                                                final AttributeSelector fromSolarSystemID,
                                                final AttributeSelector toSolarSystemID,
                                                final AttributeSelector fromConstellationID,
                                                final AttributeSelector fromRegionID,
                                                final AttributeSelector toConstellationID,
                                                final AttributeSelector toRegionID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapSolarSystemJump>>() {
        @Override
        public List<MapSolarSystemJump> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapSolarSystemJump c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.fromSolarSystemID", fromSolarSystemID);
          AttributeSelector.addIntSelector(qs, "c", "id.toSolarSystemID", toSolarSystemID);
          AttributeSelector.addIntSelector(qs, "c", "fromConstellationID", fromConstellationID);
          AttributeSelector.addIntSelector(qs, "c", "fromRegionID", fromRegionID);
          AttributeSelector.addIntSelector(qs, "c", "toConstellationID", toConstellationID);
          AttributeSelector.addIntSelector(qs, "c", "toRegionID", toRegionID);
          // Return result
          TypedQuery<MapSolarSystemJump> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapSolarSystemJump.class);
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
    return "MapSolarSystemJump [id=" + id + ", fromConstellationID=" + fromConstellationID + ", fromRegionID=" + fromRegionID + ", toConstellationID="
        + toConstellationID + ", toRegionID=" + toRegionID + "]";
  }

}