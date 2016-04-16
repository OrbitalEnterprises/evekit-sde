package enterprises.orbital.evekit.sde.inv;

import java.math.BigDecimal;
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
 * The persistent class for the invtypes database table.
 * 
 */
@Entity
@Table(
    name = "invtypes")
@Immutable
public class InvType {
  private static final Logger log = Logger.getLogger(InvType.class.getName());

  @Id
  private int                 typeID;
  private BigDecimal          basePrice;
  private double              capacity;
  private double              chanceOfDuplicating;
  private String              description;
  private Integer             factionID;
  private Integer             graphicID;
  private int                 groupID;
  private Integer             iconID;
  private Integer             marketGroupID;
  private double              mass;
  private int                 portionSize;
  private byte                published;
  private Integer             raceID;
  private Double              radius;
  private Integer             soundID;
  private String              typeName;
  private double              volume;

  public InvType() {}

  public int getTypeID() {
    return this.typeID;
  }

  public BigDecimal getBasePrice() {
    return this.basePrice;
  }

  public double getCapacity() {
    return this.capacity;
  }

  public double getChanceOfDuplicating() {
    return this.chanceOfDuplicating;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public Integer getGraphicID() {
    return this.graphicID;
  }

  public int getGroupID() {
    return this.groupID;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public Integer getMarketGroupID() {
    return this.marketGroupID;
  }

  public double getMass() {
    return this.mass;
  }

  public int getPortionSize() {
    return this.portionSize;
  }

  public byte getPublished() {
    return this.published;
  }

  public Integer getRaceID() {
    return this.raceID;
  }

  public Double getRadius() {
    return this.radius;
  }

  public Integer getSoundID() {
    return this.soundID;
  }

  public String getTypeName() {
    return this.typeName;
  }

  public double getVolume() {
    return this.volume;
  }

  public static List<InvType> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector typeID,
                                     final AttributeSelector basePrice,
                                     final AttributeSelector capacity,
                                     final AttributeSelector chanceOfDuplicating,
                                     final AttributeSelector description,
                                     final AttributeSelector factionID,
                                     final AttributeSelector graphicID,
                                     final AttributeSelector groupID,
                                     final AttributeSelector iconID,
                                     final AttributeSelector marketGroupID,
                                     final AttributeSelector mass,
                                     final AttributeSelector portionSize,
                                     final AttributeSelector published,
                                     final AttributeSelector raceID,
                                     final AttributeSelector radius,
                                     final AttributeSelector soundID,
                                     final AttributeSelector typeName,
                                     final AttributeSelector volume) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvType>>() {
        @Override
        public List<InvType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvType c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
          AttributeSelector.addDoubleSelector(qs, "c", "basePrice", basePrice);
          AttributeSelector.addDoubleSelector(qs, "c", "capacity", capacity);
          AttributeSelector.addDoubleSelector(qs, "c", "chanceOfDuplicating", chanceOfDuplicating);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
          AttributeSelector.addIntSelector(qs, "c", "graphicID", graphicID);
          AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "marketGroupID", marketGroupID);
          AttributeSelector.addDoubleSelector(qs, "c", "mass", mass);
          AttributeSelector.addIntSelector(qs, "c", "portionSize", portionSize);
          AttributeSelector.addIntSelector(qs, "c", "published", published);
          AttributeSelector.addIntSelector(qs, "c", "raceID", raceID);
          AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
          AttributeSelector.addIntSelector(qs, "c", "soundID", soundID);
          AttributeSelector.addStringSelector(qs, "c", "typeName", typeName, p);
          AttributeSelector.addDoubleSelector(qs, "c", "volume", volume);
          // Return result
          TypedQuery<InvType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvType.class);
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
    return "InvType [typeID=" + typeID + ", basePrice=" + basePrice + ", capacity=" + capacity + ", chanceOfDuplicating=" + chanceOfDuplicating
        + ", description=" + description + ", factionID=" + factionID + ", graphicID=" + graphicID + ", groupID=" + groupID + ", iconID=" + iconID
        + ", marketGroupID=" + marketGroupID + ", mass=" + mass + ", portionSize=" + portionSize + ", published=" + published + ", raceID=" + raceID
        + ", radius=" + radius + ", soundID=" + soundID + ", typeName=" + typeName + ", volume=" + volume + "]";
  }

}