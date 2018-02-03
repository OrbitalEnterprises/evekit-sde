package enterprises.orbital.evekit.sde.map;

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
 * The persistent class for the maplocationwormholeclasses database table.
 * 
 */
@Entity
@Table(
    name = "maplocationwormholeclasses")
public class MapLocationWormholeClass {
  private static final Logger log = Logger.getLogger(MapLocationWormholeClass.class.getName());

  @Id
  private int                 locationID;
  private int                wormholeClassID;

  public MapLocationWormholeClass(int locationID, int wormholeClassID) {
    super();
    this.locationID = locationID;
    this.wormholeClassID = wormholeClassID;
  }

  public MapLocationWormholeClass() {}

  public int getLocationID() {
    return this.locationID;
  }

  public int getWormholeClassID() {
    return this.wormholeClassID;
  }

  public static List<MapLocationWormholeClass> access(
                                                      final int contid,
                                                      final int maxresults,
                                                      final AttributeSelector locationID,
                                                      final AttributeSelector wormholeClassID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM MapLocationWormholeClass c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "locationID", locationID);
        AttributeSelector.addIntSelector(qs, "c", "wormholeClassID", wormholeClassID);
        // Return result
        TypedQuery<MapLocationWormholeClass> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapLocationWormholeClass.class);
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
    return "MapLocationWormholeClass [locationID=" + locationID + ", wormholeClassID=" + wormholeClassID + "]";
  }

}