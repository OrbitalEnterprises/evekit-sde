package enterprises.orbital.evekit.sde.map;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the mapuniverse database table.
 * 
 */
@Entity
public class MapUniverse {
  private static final Logger log = Logger.getLogger(MapUniverse.class.getName());

  @Id
  private int                 universeID;
  private double              radius;
  private String              universeName;
  private double              x;
  private double              xMax;
  private double              xMin;
  private double              y;
  private double              yMax;
  private double              yMin;
  private double              z;
  private double              zMax;
  private double              zMin;

  public MapUniverse() {}

  public MapUniverse(int universeID, double radius, String universeName, double x, double xMax, double xMin, double y, double yMax, double yMin, double z,
                     double zMax, double zMin) {
    super();
    this.universeID = universeID;
    this.radius = radius;
    this.universeName = universeName;
    this.x = x;
    this.xMax = xMax;
    this.xMin = xMin;
    this.y = y;
    this.yMax = yMax;
    this.yMin = yMin;
    this.z = z;
    this.zMax = zMax;
    this.zMin = zMin;
  }

  public int getUniverseID() {
    return this.universeID;
  }

  public double getRadius() {
    return this.radius;
  }

  public String getUniverseName() {
    return this.universeName;
  }

  public double getX() {
    return this.x;
  }

  public double getXMax() {
    return this.xMax;
  }

  public double getXMin() {
    return this.xMin;
  }

  public double getY() {
    return this.y;
  }

  public double getYMax() {
    return this.yMax;
  }

  public double getYMin() {
    return this.yMin;
  }

  public double getZ() {
    return this.z;
  }

  public double getZMax() {
    return this.zMax;
  }

  public double getZMin() {
    return this.zMin;
  }

  public static List<MapUniverse> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector universeID,
                                         final AttributeSelector radius,
                                         final AttributeSelector universeName,
                                         final AttributeSelector x,
                                         final AttributeSelector xMax,
                                         final AttributeSelector xMin,
                                         final AttributeSelector y,
                                         final AttributeSelector yMax,
                                         final AttributeSelector yMin,
                                         final AttributeSelector z,
                                         final AttributeSelector zMax,
                                         final AttributeSelector zMin) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapUniverse>>() {
        @Override
        public List<MapUniverse> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapUniverse c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "universeID", universeID);
          AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
          AttributeSelector.addStringSelector(qs, "c", "universeName", universeName, p);
          AttributeSelector.addDoubleSelector(qs, "c", "x", x);
          AttributeSelector.addDoubleSelector(qs, "c", "xMax", xMax);
          AttributeSelector.addDoubleSelector(qs, "c", "xMin", xMin);
          AttributeSelector.addDoubleSelector(qs, "c", "y", y);
          AttributeSelector.addDoubleSelector(qs, "c", "yMax", yMax);
          AttributeSelector.addDoubleSelector(qs, "c", "yMin", yMin);
          AttributeSelector.addDoubleSelector(qs, "c", "z", z);
          AttributeSelector.addDoubleSelector(qs, "c", "zMax", zMax);
          AttributeSelector.addDoubleSelector(qs, "c", "zMin", zMin);
          // Return result
          TypedQuery<MapUniverse> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapUniverse.class);
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
    return "MapUniverse [universeID=" + universeID + ", radius=" + radius + ", universeName=" + universeName + ", x=" + x + ", xMax=" + xMax + ", xMin=" + xMin
        + ", y=" + y + ", yMax=" + yMax + ", yMin=" + yMin + ", z=" + z + ", zMax=" + zMax + ", zMin=" + zMin + "]";
  }

}