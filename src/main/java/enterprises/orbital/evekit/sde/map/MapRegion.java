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
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the mapregions database table.
 * 
 */
@Entity
@Table(
    name = "mapregions")
public class MapRegion {
  private static final Logger log = Logger.getLogger(MapRegion.class.getName());

  @Id
  private int                 regionID;
  private String              regionName;
  private double              x;
  private double              y;
  private double              z;
  private double              xMin;
  private double              xMax;
  private double              yMin;
  private double              yMax;
  private double              zMin;
  private double              zMax;
  private Integer             factionID;
  private Double              radius;

  public MapRegion() {}

  public MapRegion(int regionID, Integer factionID, Double radius, String regionName, double x, double xMax, double xMin, double y, double yMax, double yMin,
                   double z, double zMax, double zMin) {
    super();
    this.regionID = regionID;
    this.factionID = factionID;
    this.radius = radius;
    this.regionName = regionName;
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

  public int getRegionID() {
    return this.regionID;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public Double getRadius() {
    return this.radius;
  }

  public String getRegionName() {
    return this.regionName;
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

  public static List<MapRegion> access(
                                       final int contid,
                                       final int maxresults,
                                       final AttributeSelector regionID,
                                       final AttributeSelector factionID,
                                       final AttributeSelector radius,
                                       final AttributeSelector regionName,
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
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapRegion>>() {
        @Override
        public List<MapRegion> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapRegion c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "regionID", regionID);
          AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
          AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
          AttributeSelector.addStringSelector(qs, "c", "regionName", regionName, p);
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
          TypedQuery<MapRegion> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapRegion.class);
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
    return "MapRegion [regionID=" + regionID + ", factionID=" + factionID + ", radius=" + radius + ", regionName=" + regionName + ", x=" + x + ", xMax=" + xMax
        + ", xMin=" + xMin + ", y=" + y + ", yMax=" + yMax + ", yMin=" + yMin + ", z=" + z + ", zMax=" + zMax + ", zMin=" + zMin + "]";
  }

}