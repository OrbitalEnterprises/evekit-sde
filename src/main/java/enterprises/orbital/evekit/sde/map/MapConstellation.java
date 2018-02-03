package enterprises.orbital.evekit.sde.map;

import enterprises.orbital.evekit.sde.AttributeParameters;
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
 * The persistent class for the mapconstellations database table.
 * 
 */
@Entity
@Table(
    name = "mapconstellations")
public class MapConstellation {
  private static final Logger log = Logger.getLogger(MapConstellation.class.getName());

  @Id
  private int                 constellationID;
  private int                 regionID;
  private String              constellationName;
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
  private double              radius;

  public MapConstellation() {}

  public MapConstellation(int constellationID, String constellationName, Integer factionID, double radius, int regionID, double x, double xMax, double xMin,
                          double y, double yMax, double yMin, double z, double zMax, double zMin) {
    super();
    this.constellationID = constellationID;
    this.constellationName = constellationName;
    this.factionID = factionID;
    this.radius = radius;
    this.regionID = regionID;
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

  public int getConstellationID() {
    return this.constellationID;
  }

  public String getConstellationName() {
    return this.constellationName;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public double getRadius() {
    return this.radius;
  }

  public int getRegionID() {
    return this.regionID;
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

  public static List<MapConstellation> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector constellationID,
                                              final AttributeSelector constellationName,
                                              final AttributeSelector factionID,
                                              final AttributeSelector radius,
                                              final AttributeSelector regionID,
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
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM MapConstellation c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "constellationID", constellationID);
        AttributeSelector.addStringSelector(qs, "c", "constellationName", constellationName, p);
        AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
        AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
        AttributeSelector.addIntSelector(qs, "c", "regionID", regionID);
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
        TypedQuery<MapConstellation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapConstellation.class);
        p.fillParams(query);
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
    return "MapConstellation [constellationID=" + constellationID + ", constellationName=" + constellationName + ", factionID=" + factionID + ", radius="
        + radius + ", regionID=" + regionID + ", x=" + x + ", xMax=" + xMax + ", xMin=" + xMin + ", y=" + y + ", yMax=" + yMax + ", yMin=" + yMin + ", z=" + z
        + ", zMax=" + zMax + ", zMin=" + zMin + "]";
  }

}