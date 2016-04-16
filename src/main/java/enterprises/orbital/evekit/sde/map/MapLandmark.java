package enterprises.orbital.evekit.sde.map;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the maplandmarks database table.
 * 
 */
@Entity
@Table(
    name = "maplandmarks")
@Immutable
public class MapLandmark {
  private static final Logger log = Logger.getLogger(MapLandmark.class.getName());

  @Id
  private int                 landmarkID;
  private String              description;
  private Integer             iconID;
  private Byte                importance;
  private String              landmarkName;
  private Integer             locationID;
  private Double              radius;
  private double              x;
  private double              y;
  private double              z;

  public MapLandmark() {}

  public int getLandmarkID() {
    return this.landmarkID;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public Byte getImportance() {
    return this.importance;
  }

  public String getLandmarkName() {
    return this.landmarkName;
  }

  public Integer getLocationID() {
    return this.locationID;
  }

  public Double getRadius() {
    return this.radius;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }

  public static List<MapLandmark> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector landmarkID,
                                         final AttributeSelector description,
                                         final AttributeSelector iconID,
                                         final AttributeSelector importance,
                                         final AttributeSelector landmarkName,
                                         final AttributeSelector locationID,
                                         final AttributeSelector radius,
                                         final AttributeSelector x,
                                         final AttributeSelector y,
                                         final AttributeSelector z) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapLandmark>>() {
        @Override
        public List<MapLandmark> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapLandmark c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "landmarkID", landmarkID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "importance", importance);
          AttributeSelector.addStringSelector(qs, "c", "landmarkName", landmarkName, p);
          AttributeSelector.addIntSelector(qs, "c", "locationID", locationID);
          AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
          AttributeSelector.addDoubleSelector(qs, "c", "x", x);
          AttributeSelector.addDoubleSelector(qs, "c", "y", y);
          AttributeSelector.addDoubleSelector(qs, "c", "z", z);
          // Return result
          TypedQuery<MapLandmark> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapLandmark.class);
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
    return "MapLandmark [landmarkID=" + landmarkID + ", description=" + description + ", iconID=" + iconID + ", importance=" + importance + ", landmarkName="
        + landmarkName + ", locationID=" + locationID + ", radius=" + radius + ", x=" + x + ", y=" + y + ", z=" + z + "]";
  }

}