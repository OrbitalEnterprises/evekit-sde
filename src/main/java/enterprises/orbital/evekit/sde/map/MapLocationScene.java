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
 * The persistent class for the maplocationscenes database table.
 * 
 */
@Entity
@Table(
    name = "maplocationscenes")
public class MapLocationScene {
  private static final Logger log = Logger.getLogger(MapLocationScene.class.getName());

  @Id
  private int                 locationID;
  private int                 graphicID;

  public MapLocationScene() {}

  public MapLocationScene(int locationID, int graphicID) {
    super();
    this.locationID = locationID;
    this.graphicID = graphicID;
  }

  public int getLocationID() {
    return this.locationID;
  }

  public int getGraphicID() {
    return this.graphicID;
  }

  public static List<MapLocationScene> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector locationID,
                                              final AttributeSelector graphicID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapLocationScene>>() {
        @Override
        public List<MapLocationScene> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapLocationScene c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "locationID", locationID);
          AttributeSelector.addIntSelector(qs, "c", "graphicID", graphicID);
          // Return result
          TypedQuery<MapLocationScene> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapLocationScene.class);
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
    return "MapLocationScene [locationID=" + locationID + ", graphicID=" + graphicID + "]";
  }

}