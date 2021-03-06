package enterprises.orbital.evekit.sde.inv;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the invtypes database table.
 * 
 */
@Entity
@Table(
    name = "invtypes")
public class InvType {
  private static final Logger log = Logger.getLogger(InvType.class.getName());

  @Id
  private int                 typeID;
  private int                 groupID;
  private String              typeName;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private Double              mass;
  private Double              volume;
  private Double              capacity;
  private int                 portionSize;
  private Integer             raceID;
  private BigDecimal          basePrice;
  private boolean                published;
  private Integer             marketGroupID;
  private Integer             iconID;
  private Integer             soundID;
  private Integer             graphicID;

  public InvType() {}

  public InvType(int typeID, BigDecimal basePrice, Double capacity, String description, Integer factionID, Integer graphicID, int groupID, Integer iconID,
                 Integer marketGroupID, Double mass, int portionSize, boolean published, Integer raceID, Integer soundID, String typeName, Double volume) {
    super();
    this.typeID = typeID;
    this.basePrice = basePrice;
    this.capacity = capacity;
    this.description = description;
    this.graphicID = graphicID;
    this.groupID = groupID;
    this.iconID = iconID;
    this.marketGroupID = marketGroupID;
    this.mass = mass;
    this.portionSize = portionSize;
    this.published = published;
    this.raceID = raceID;
    this.soundID = soundID;
    this.typeName = typeName;
    this.volume = volume;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public BigDecimal getBasePrice() {
    return this.basePrice;
  }

  public Double getCapacity() {
    return this.capacity;
  }

  public String getDescription() {
    return this.description;
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

  public Double getMass() {
    return this.mass;
  }

  public int getPortionSize() {
    return this.portionSize;
  }

  public boolean isPublished() {
    return this.published;
  }

  public Integer getRaceID() {
    return this.raceID;
  }

  public Integer getSoundID() {
    return this.soundID;
  }

  public String getTypeName() {
    return this.typeName;
  }

  public Double getVolume() {
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
                                     final AttributeSelector graphicID,
                                     final AttributeSelector groupID,
                                     final AttributeSelector iconID,
                                     final AttributeSelector marketGroupID,
                                     final AttributeSelector mass,
                                     final AttributeSelector portionSize,
                                     final AttributeSelector published,
                                     final AttributeSelector raceID,
                                     final AttributeSelector soundID,
                                     final AttributeSelector typeName,
                                     final AttributeSelector volume) {
    try {
      return SDE.getFactory().runTransaction(() -> {
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
        AttributeSelector.addIntSelector(qs, "c", "graphicID", graphicID);
        AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
        AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
        AttributeSelector.addIntSelector(qs, "c", "marketGroupID", marketGroupID);
        AttributeSelector.addDoubleSelector(qs, "c", "mass", mass);
        AttributeSelector.addIntSelector(qs, "c", "portionSize", portionSize);
        AttributeSelector.addBooleanSelector(qs, "c", "published", published);
        AttributeSelector.addIntSelector(qs, "c", "raceID", raceID);
        AttributeSelector.addIntSelector(qs, "c", "soundID", soundID);
        AttributeSelector.addStringSelector(qs, "c", "typeName", typeName, p);
        AttributeSelector.addDoubleSelector(qs, "c", "volume", volume);
        // Return result
        TypedQuery<InvType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvType.class);
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
    return "InvType [typeID=" + typeID + ", basePrice=" + basePrice + ", capacity=" + capacity + ", description=" + description + ", graphicID=" + graphicID
        + ", groupID=" + groupID + ", iconID=" + iconID + ", marketGroupID=" + marketGroupID + ", mass=" + mass + ", portionSize=" + portionSize
        + ", published=" + published + ", raceID=" + raceID + ", soundID=" + soundID + ", typeName=" + typeName + ", volume=" + volume + "]";
  }

}