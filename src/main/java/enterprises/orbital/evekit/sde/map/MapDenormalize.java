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
 * The persistent class for the mapdenormalize database table.
 * 
 */
@Entity
public class MapDenormalize {
  private static final Logger log = Logger.getLogger(MapDenormalize.class.getName());

  @Id
  private int                 itemID;
  private int                 typeID;
  private int                 groupID;
  private Integer             solarSystemID;
  private Integer             constellationID;
  private Integer             regionID;
  private Integer             orbitID;
  private double              x;
  private double              y;
  private double              z;
  private Double              radius;
  private String              itemName;
  private Double              security;
  private Byte                celestialIndex;
  private Byte                orbitIndex;

  public MapDenormalize() {}

  public MapDenormalize(int itemID, Byte celestialIndex, Integer constellationID, int groupID, String itemName, Integer orbitID, Byte orbitIndex, Double radius,
                        Integer regionID, Double security, Integer solarSystemID, int typeID, double x, double y, double z) {
    super();
    this.itemID = itemID;
    this.celestialIndex = celestialIndex;
    this.constellationID = constellationID;
    this.groupID = groupID;
    this.itemName = itemName;
    this.orbitID = orbitID;
    this.orbitIndex = orbitIndex;
    this.radius = radius;
    this.regionID = regionID;
    this.security = security;
    this.solarSystemID = solarSystemID;
    this.typeID = typeID;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getItemID() {
    return this.itemID;
  }

  public Byte getCelestialIndex() {
    return this.celestialIndex;
  }

  public Integer getConstellationID() {
    return this.constellationID;
  }

  public int getGroupID() {
    return this.groupID;
  }

  public String getItemName() {
    return this.itemName;
  }

  public Integer getOrbitID() {
    return this.orbitID;
  }

  public Byte getOrbitIndex() {
    return this.orbitIndex;
  }

  public Double getRadius() {
    return this.radius;
  }

  public Integer getRegionID() {
    return this.regionID;
  }

  public Double getSecurity() {
    return this.security;
  }

  public Integer getSolarSystemID() {
    return this.solarSystemID;
  }

  public int getTypeID() {
    return this.typeID;
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

  public static List<MapDenormalize> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector itemID,
                                            final AttributeSelector celestialIndex,
                                            final AttributeSelector constellationID,
                                            final AttributeSelector groupID,
                                            final AttributeSelector itemName,
                                            final AttributeSelector orbitID,
                                            final AttributeSelector orbitIndex,
                                            final AttributeSelector radius,
                                            final AttributeSelector regionID,
                                            final AttributeSelector security,
                                            final AttributeSelector solarSystemID,
                                            final AttributeSelector typeID,
                                            final AttributeSelector x,
                                            final AttributeSelector y,
                                            final AttributeSelector z) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapDenormalize>>() {
        @Override
        public List<MapDenormalize> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapDenormalize c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "itemID", itemID);
          AttributeSelector.addIntSelector(qs, "c", "celestialIndex", celestialIndex);
          AttributeSelector.addIntSelector(qs, "c", "constellationID", constellationID);
          AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
          AttributeSelector.addStringSelector(qs, "c", "itemName", itemName, p);
          AttributeSelector.addIntSelector(qs, "c", "orbitID", orbitID);
          AttributeSelector.addIntSelector(qs, "c", "orbitIndex", orbitIndex);
          AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
          AttributeSelector.addIntSelector(qs, "c", "regionID", regionID);
          AttributeSelector.addDoubleSelector(qs, "c", "security", security);
          AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
          AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
          AttributeSelector.addDoubleSelector(qs, "c", "x", x);
          AttributeSelector.addDoubleSelector(qs, "c", "y", y);
          AttributeSelector.addDoubleSelector(qs, "c", "z", z);
          // Return result
          TypedQuery<MapDenormalize> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapDenormalize.class);
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
    return "MapDenormalize [itemID=" + itemID + ", celestialIndex=" + celestialIndex + ", constellationID=" + constellationID + ", groupID=" + groupID
        + ", itemName=" + itemName + ", orbitID=" + orbitID + ", orbitIndex=" + orbitIndex + ", radius=" + radius + ", regionID=" + regionID + ", security="
        + security + ", solarSystemID=" + solarSystemID + ", typeID=" + typeID + ", x=" + x + ", y=" + y + ", z=" + z + "]";
  }

}