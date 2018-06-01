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
 * The persistent class for the mapsolarsystems database table.
 * 
 */
@Entity
@Table(
    name = "mapsolarsystems")
public class MapSolarSystem {
  private static final Logger log = Logger.getLogger(MapSolarSystem.class.getName());

  @Id
  private int                 solarSystemID;
  private int                 regionID;
  private int                 constellationID;
  private String              solarSystemName;
  private double              x;
  private double              y;
  private double              z;
  private double              xMin;
  private double              xMax;
  private double              yMin;
  private double              yMax;
  private double              zMin;
  private double              zMax;
  private double              luminosity;
  private boolean                border;
  private boolean                fringe;
  private boolean                corridor;
  private boolean                hub;
  private boolean                international;
  private boolean                regional;
  private Boolean                constellation;
  private double              security;
  private Integer             factionID;
  private double              radius;
  private Integer                 sunTypeID;
  private String              securityClass;

  public MapSolarSystem() {}

  public MapSolarSystem(int solarSystemID, boolean border, Boolean constellation, int constellationID, boolean corridor, Integer factionID, boolean fringe, boolean hub,
                        boolean international, double luminosity, double radius, boolean regional, int regionID, double security, String securityClass,
                        String solarSystemName, Integer sunTypeID, double x, double xMax, double xMin, double y, double yMax, double yMin, double z, double zMax,
                        double zMin) {
    super();
    this.solarSystemID = solarSystemID;
    this.border = border;
    this.constellation = constellation;
    this.constellationID = constellationID;
    this.corridor = corridor;
    this.factionID = factionID;
    this.fringe = fringe;
    this.hub = hub;
    this.international = international;
    this.luminosity = luminosity;
    this.radius = radius;
    this.regional = regional;
    this.regionID = regionID;
    this.security = security;
    this.securityClass = securityClass;
    this.solarSystemName = solarSystemName;
    this.sunTypeID = sunTypeID;
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

  public int getSolarSystemID() {
    return this.solarSystemID;
  }

  public boolean isBorder() {
    return this.border;
  }

  public Boolean isConstellation() {
    return this.constellation;
  }

  public int getConstellationID() {
    return this.constellationID;
  }

  public boolean isCorridor() {
    return this.corridor;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public boolean isFringe() {
    return this.fringe;
  }

  public boolean isHub() {
    return this.hub;
  }

  public boolean isInternational() {
    return this.international;
  }

  public double getLuminosity() {
    return this.luminosity;
  }

  public double getRadius() {
    return this.radius;
  }

  public boolean isRegional() {
    return this.regional;
  }

  public int getRegionID() {
    return this.regionID;
  }

  public double getSecurity() {
    return this.security;
  }

  public String getSecurityClass() {
    return this.securityClass;
  }

  public String getSolarSystemName() {
    return this.solarSystemName;
  }

  public Integer getSunTypeID() {
    return this.sunTypeID;
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

  public static List<MapSolarSystem> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector solarSystemID,
                                            final AttributeSelector border,
                                            final AttributeSelector constellation,
                                            final AttributeSelector constellationID,
                                            final AttributeSelector corridor,
                                            final AttributeSelector factionID,
                                            final AttributeSelector fringe,
                                            final AttributeSelector hub,
                                            final AttributeSelector international,
                                            final AttributeSelector luminosity,
                                            final AttributeSelector radius,
                                            final AttributeSelector regional,
                                            final AttributeSelector regionID,
                                            final AttributeSelector security,
                                            final AttributeSelector securityClass,
                                            final AttributeSelector solarSystemName,
                                            final AttributeSelector sunTypeID,
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
        qs.append("SELECT c FROM MapSolarSystem c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
        AttributeSelector.addBooleanSelector(qs, "c", "border", border);
        AttributeSelector.addBooleanSelector(qs, "c", "constellation", constellation);
        AttributeSelector.addIntSelector(qs, "c", "constellationID", constellationID);
        AttributeSelector.addBooleanSelector(qs, "c", "corridor", corridor);
        AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
        AttributeSelector.addBooleanSelector(qs, "c", "fringe", fringe);
        AttributeSelector.addBooleanSelector(qs, "c", "hub", hub);
        AttributeSelector.addBooleanSelector(qs, "c", "international", international);
        AttributeSelector.addDoubleSelector(qs, "c", "luminosity", luminosity);
        AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
        AttributeSelector.addBooleanSelector(qs, "c", "regional", regional);
        AttributeSelector.addIntSelector(qs, "c", "regionID", regionID);
        AttributeSelector.addDoubleSelector(qs, "c", "security", security);
        AttributeSelector.addStringSelector(qs, "c", "securityClass", securityClass, p);
        AttributeSelector.addStringSelector(qs, "c", "solarSystemName", solarSystemName, p);
        AttributeSelector.addIntSelector(qs, "c", "sunTypeID", sunTypeID);
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
        TypedQuery<MapSolarSystem> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapSolarSystem.class);
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
    return "MapSolarSystem [solarSystemID=" + solarSystemID + ", border=" + border + ", constellation=" + constellation + ", constellationID=" + constellationID
        + ", corridor=" + corridor + ", factionID=" + factionID + ", fringe=" + fringe + ", hub=" + hub + ", international=" + international + ", luminosity="
        + luminosity + ", radius=" + radius + ", regional=" + regional + ", regionID=" + regionID + ", security=" + security + ", securityClass="
        + securityClass + ", solarSystemName=" + solarSystemName + ", sunTypeID=" + sunTypeID + ", x=" + x + ", xMax=" + xMax + ", xMin=" + xMin + ", y=" + y
        + ", yMax=" + yMax + ", yMin=" + yMin + ", z=" + z + ", zMax=" + zMax + ", zMin=" + zMin + "]";
  }

}