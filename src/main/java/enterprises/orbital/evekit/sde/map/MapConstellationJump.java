package enterprises.orbital.evekit.sde.map;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the mapconstellationjumps database table.
 * 
 */
@Entity
@Table(
    name = "mapconstellationjumps")
public class MapConstellationJump {
  private static final Logger    log = Logger.getLogger(MapConstellationJump.class.getName());

  @EmbeddedId
  private MapConstellationJumpPK id;
  private int                    fromRegionID;
  private int                    toRegionID;

  public MapConstellationJump() {}

  public MapConstellationJump(int fromConstellationID, int toConstellationID, int fromRegionID, int toRegionID) {
    super();
    this.id = new MapConstellationJumpPK(fromConstellationID, toConstellationID);
    this.fromRegionID = fromRegionID;
    this.toRegionID = toRegionID;
  }

  public MapConstellationJumpPK id() {
    return this.id;
  }

  public int getFromConstellationID() {
    return id.getFromConstellationID();
  }

  public int getToConstellationID() {
    return id.getToConstellationID();
  }

  public int getFromRegionID() {
    return this.fromRegionID;
  }

  public int getToRegionID() {
    return this.toRegionID;
  }

  public static List<MapConstellationJump> access(
                                                  final int contid,
                                                  final int maxresults,
                                                  final AttributeSelector fromConstellationID,
                                                  final AttributeSelector toConstellationID,
                                                  final AttributeSelector fromRegionID,
                                                  final AttributeSelector toRegionID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM MapConstellationJump c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "id.fromConstellationID", fromConstellationID);
        AttributeSelector.addIntSelector(qs, "c", "id.toConstellationID", toConstellationID);
        AttributeSelector.addIntSelector(qs, "c", "fromRegionID", fromRegionID);
        AttributeSelector.addIntSelector(qs, "c", "toRegionID", toRegionID);
        // Return result
        TypedQuery<MapConstellationJump> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapConstellationJump.class);
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
    return "MapConstellationJump [id=" + id + ", fromRegionID=" + fromRegionID + ", toRegionID=" + toRegionID + "]";
  }

}