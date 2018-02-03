package enterprises.orbital.evekit.sde.chr;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the chrfactions database table.
 * 
 */
@Entity
@Table(
    name = "chrfactions")
public class ChrFaction {
  public static final Logger log = Logger.getLogger(ChrFaction.class.getName());

  @Id
  private int                factionID;
  private String             factionName;
  @Lob
  @Column(
      length = 102400)
  private String             description;
  private int                raceIDs;
  private int                solarSystemID;
  private int                corporationID;
  private double             sizeFactor;
  private short              stationCount;
  private short              stationSystemCount;
  private Integer            militiaCorporationID;
  private int                iconID;

  public ChrFaction() {}

  public ChrFaction(int factionID, int corporationID, String description, String factionName, int iconID, Integer militiaCorporationID, int raceIDs,
                    double sizeFactor, int solarSystemID, short stationCount, short stationSystemCount) {
    super();
    this.factionID = factionID;
    this.corporationID = corporationID;
    this.description = description;
    this.factionName = factionName;
    this.iconID = iconID;
    this.militiaCorporationID = militiaCorporationID;
    this.raceIDs = raceIDs;
    this.sizeFactor = sizeFactor;
    this.solarSystemID = solarSystemID;
    this.stationCount = stationCount;
    this.stationSystemCount = stationSystemCount;
  }

  public int getFactionID() {
    return this.factionID;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  public String getDescription() {
    return this.description;
  }

  public String getFactionName() {
    return this.factionName;
  }

  public int getIconID() {
    return this.iconID;
  }

  public Integer getMilitiaCorporationID() {
    return militiaCorporationID;
  }

  public int getRaceIDs() {
    return this.raceIDs;
  }

  public double getSizeFactor() {
    return this.sizeFactor;
  }

  public int getSolarSystemID() {
    return this.solarSystemID;
  }

  public short getStationCount() {
    return this.stationCount;
  }

  public short getStationSystemCount() {
    return this.stationSystemCount;
  }

  public static List<ChrFaction> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector factionID,
                                        final AttributeSelector corporationID,
                                        final AttributeSelector description,
                                        final AttributeSelector factionName,
                                        final AttributeSelector iconID,
                                        final AttributeSelector militiaCorporationID,
                                        final AttributeSelector raceIDs,
                                        final AttributeSelector sizeFactor,
                                        final AttributeSelector solarSystemID,
                                        final AttributeSelector stationCount,
                                        final AttributeSelector stationSystemCount) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM ChrFaction c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
        AttributeSelector.addIntSelector(qs, "c", "corporationID", corporationID);
        AttributeSelector.addStringSelector(qs, "c", "description", description, p);
        AttributeSelector.addStringSelector(qs, "c", "factionName", factionName, p);
        AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
        AttributeSelector.addIntSelector(qs, "c", "militiaCorporationID", militiaCorporationID);
        AttributeSelector.addIntSelector(qs, "c", "raceIDs", raceIDs);
        AttributeSelector.addDoubleSelector(qs, "c", "sizeFactor", sizeFactor);
        AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
        AttributeSelector.addIntSelector(qs, "c", "stationCount", stationCount);
        AttributeSelector.addIntSelector(qs, "c", "stationSystemCount", stationSystemCount);
        // Return result
        TypedQuery<ChrFaction> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), ChrFaction.class);
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
    return "ChrFaction [factionID=" + factionID + ", corporationID=" + corporationID + ", description=" + description + ", factionName=" + factionName
        + ", iconID=" + iconID + ", militiaCorporationID=" + militiaCorporationID + ", raceIDs=" + raceIDs + ", sizeFactor=" + sizeFactor + ", solarSystemID="
        + solarSystemID + ", stationCount=" + stationCount + ", stationSystemCount=" + stationSystemCount + "]";
  }

}