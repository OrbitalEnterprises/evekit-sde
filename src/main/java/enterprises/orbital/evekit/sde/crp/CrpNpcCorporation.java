package enterprises.orbital.evekit.sde.crp;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the crpnpccorporations database table.
 * 
 */
@Entity
@Table(
    name = "crpnpccorporations")
public class CrpNpcCorporation {
  private static final Logger log = Logger.getLogger(CrpNpcCorporation.class.getName());

  @Id
  private int                 corporationID;
  private String              size;
  private String              extent;
  private Integer             solarSystemID;
  private Integer             investorID1;
  private int                investorShares1;
  private Integer             investorID2;
  private int                investorShares2;
  private Integer             investorID3;
  private int                investorShares3;
  private Integer             investorID4;
  private int                investorShares4;
  private Integer             friendID;
  private Integer             enemyID;
  private long                publicShares;
  private int                 initialPrice;
  private double              minSecurity;
  private int                scattered;
  private int                fringe;
  private int                corridor;
  private int                hub;
  private int                border;
  private Integer             factionID;
  private Double              sizeFactor;
  private Short               stationCount;
  private Short               stationSystemCount;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private Integer             iconID;

  public CrpNpcCorporation() {}

  public CrpNpcCorporation(int corporationID, int border, int corridor, String description, Integer enemyID, String extent, Integer factionID,
                           Integer friendID, int fringe, int hub, Integer iconID, int initialPrice, Integer investorID1, Integer investorID2,
                           Integer investorID3, Integer investorID4, int investorShares1, int investorShares2, int investorShares3, int investorShares4,
                           double minSecurity, long publicShares, int scattered, String size, Double sizeFactor, Integer solarSystemID, Short stationCount,
                           Short stationSystemCount) {
    super();
    this.corporationID = corporationID;
    this.border = border;
    this.corridor = corridor;
    this.description = description;
    this.enemyID = enemyID;
    this.extent = extent;
    this.factionID = factionID;
    this.friendID = friendID;
    this.fringe = fringe;
    this.hub = hub;
    this.iconID = iconID;
    this.initialPrice = initialPrice;
    this.investorID1 = investorID1;
    this.investorID2 = investorID2;
    this.investorID3 = investorID3;
    this.investorID4 = investorID4;
    this.investorShares1 = investorShares1;
    this.investorShares2 = investorShares2;
    this.investorShares3 = investorShares3;
    this.investorShares4 = investorShares4;
    this.minSecurity = minSecurity;
    this.publicShares = publicShares;
    this.scattered = scattered;
    this.size = size;
    this.sizeFactor = sizeFactor;
    this.solarSystemID = solarSystemID;
    this.stationCount = stationCount;
    this.stationSystemCount = stationSystemCount;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  public int getBorder() {
    return this.border;
  }

  public int getCorridor() {
    return this.corridor;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getEnemyID() {
    return this.enemyID;
  }

  public String getExtent() {
    return this.extent;
  }

  public Integer getFactionID() {
    return this.factionID;
  }

  public Integer getFriendID() {
    return this.friendID;
  }

  public int getFringe() {
    return this.fringe;
  }

  public int getHub() {
    return this.hub;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public int getInitialPrice() {
    return this.initialPrice;
  }

  public Integer getInvestorID1() {
    return investorID1;
  }

  public Integer getInvestorID2() {
    return investorID2;
  }

  public Integer getInvestorID3() {
    return investorID3;
  }

  public Integer getInvestorID4() {
    return investorID4;
  }

  public int getInvestorShares1() {
    return this.investorShares1;
  }

  public int getInvestorShares2() {
    return this.investorShares2;
  }

  public int getInvestorShares3() {
    return this.investorShares3;
  }

  public int getInvestorShares4() {
    return this.investorShares4;
  }

  public double getMinSecurity() {
    return this.minSecurity;
  }

  public long getPublicShares() {
    return this.publicShares;
  }

  public int getScattered() {
    return this.scattered;
  }

  public String getSize() {
    return this.size;
  }

  public Double getSizeFactor() {
    return this.sizeFactor;
  }

  public Integer getSolarSystemID() {
    return this.solarSystemID;
  }

  public Short getStationCount() {
    return this.stationCount;
  }

  public Short getStationSystemCount() {
    return this.stationSystemCount;
  }

  public static List<CrpNpcCorporation> access(
                                               final int contid,
                                               final int maxresults,
                                               final AttributeSelector corporationID,
                                               final AttributeSelector border,
                                               final AttributeSelector corridor,
                                               final AttributeSelector description,
                                               final AttributeSelector enemyID,
                                               final AttributeSelector extent,
                                               final AttributeSelector factionID,
                                               final AttributeSelector friendID,
                                               final AttributeSelector fringe,
                                               final AttributeSelector hub,
                                               final AttributeSelector iconID,
                                               final AttributeSelector initialPrice,
                                               final AttributeSelector investorID1,
                                               final AttributeSelector investorID2,
                                               final AttributeSelector investorID3,
                                               final AttributeSelector investorID4,
                                               final AttributeSelector investorShares1,
                                               final AttributeSelector investorShares2,
                                               final AttributeSelector investorShares3,
                                               final AttributeSelector investorShares4,
                                               final AttributeSelector minSecurity,
                                               final AttributeSelector publicShares,
                                               final AttributeSelector scattered,
                                               final AttributeSelector size,
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
        qs.append("SELECT c FROM CrpNpcCorporation c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "corporationID", corporationID);
        AttributeSelector.addIntSelector(qs, "c", "border", border);
        AttributeSelector.addIntSelector(qs, "c", "corridor", corridor);
        AttributeSelector.addStringSelector(qs, "c", "description", description, p);
        AttributeSelector.addIntSelector(qs, "c", "enemyID", enemyID);
        AttributeSelector.addStringSelector(qs, "c", "extent", extent, p);
        AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
        AttributeSelector.addIntSelector(qs, "c", "friendID", friendID);
        AttributeSelector.addIntSelector(qs, "c", "fringe", fringe);
        AttributeSelector.addIntSelector(qs, "c", "hub", hub);
        AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
        AttributeSelector.addIntSelector(qs, "c", "initialPrice", initialPrice);
        AttributeSelector.addIntSelector(qs, "c", "investorID1", investorID1);
        AttributeSelector.addIntSelector(qs, "c", "investorID2", investorID2);
        AttributeSelector.addIntSelector(qs, "c", "investorID3", investorID3);
        AttributeSelector.addIntSelector(qs, "c", "investorID4", investorID4);
        AttributeSelector.addIntSelector(qs, "c", "investorShares1", investorShares1);
        AttributeSelector.addIntSelector(qs, "c", "investorShares2", investorShares2);
        AttributeSelector.addIntSelector(qs, "c", "investorShares3", investorShares3);
        AttributeSelector.addIntSelector(qs, "c", "investorShares4", investorShares4);
        AttributeSelector.addDoubleSelector(qs, "c", "minSecurity", minSecurity);
        AttributeSelector.addDoubleSelector(qs, "c", "publicShares", publicShares);
        AttributeSelector.addIntSelector(qs, "c", "scattered", scattered);
        AttributeSelector.addStringSelector(qs, "c", "size", size, p);
        AttributeSelector.addDoubleSelector(qs, "c", "sizeFactor", sizeFactor);
        AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
        AttributeSelector.addIntSelector(qs, "c", "stationCount", stationCount);
        AttributeSelector.addIntSelector(qs, "c", "stationSystemCount", stationSystemCount);
        // Return result
        TypedQuery<CrpNpcCorporation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrpNpcCorporation.class);
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
    return "CrpNpcCorporation [corporationID=" + corporationID + ", border=" + border + ", corridor=" + corridor + ", description=" + description + ", enemyID="
        + enemyID + ", extent=" + extent + ", factionID=" + factionID + ", friendID=" + friendID + ", fringe=" + fringe + ", hub=" + hub + ", iconID=" + iconID
        + ", initialPrice=" + initialPrice + ", investorID1=" + investorID1 + ", investorID2=" + investorID2 + ", investorID3=" + investorID3 + ", investorID4="
        + investorID4 + ", investorShares1=" + investorShares1 + ", investorShares2=" + investorShares2 + ", investorShares3=" + investorShares3
        + ", investorShares4=" + investorShares4 + ", minSecurity=" + minSecurity + ", publicShares=" + publicShares + ", scattered=" + scattered + ", size="
        + size + ", sizeFactor=" + sizeFactor + ", solarSystemID=" + solarSystemID + ", stationCount=" + stationCount + ", stationSystemCount="
        + stationSystemCount + "]";
  }

}