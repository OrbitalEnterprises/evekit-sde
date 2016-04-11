package enterprises.orbital.evekit.sde.crp;

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
 * The persistent class for the crpnpccorporations database table.
 * 
 */
@Entity
@Table(
    name = "crpnpccorporations")
@Immutable
public class CrpNpcCorporation {
  private static final Logger log = Logger.getLogger(CrpNpcCorporation.class.getName());

  @Id
  private int                 corporationID;
  private byte                border;
  private byte                corridor;
  private String              description;
  private Integer             enemyID;
  private String              extent;
  private int                 factionID;
  private Integer             friendID;
  private byte                fringe;
  private byte                hub;
  private Integer             iconID;
  private int                 initialPrice;
  private Integer             investorID1;
  private Integer             investorID2;
  private Integer             investorID3;
  private Integer             investorID4;
  private byte                investorShares1;
  private byte                investorShares2;
  private byte                investorShares3;
  private byte                investorShares4;
  private double              minSecurity;
  private long                publicShares;
  private byte                scattered;
  private String              size;
  private Double              sizeFactor;
  private int                 solarSystemID;
  private Short               stationCount;
  private Short               stationSystemCount;

  public CrpNpcCorporation() {}

  public int getCorporationID() {
    return this.corporationID;
  }

  public byte getBorder() {
    return this.border;
  }

  public byte getCorridor() {
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

  public int getFactionID() {
    return this.factionID;
  }

  public Integer getFriendID() {
    return this.friendID;
  }

  public byte getFringe() {
    return this.fringe;
  }

  public byte getHub() {
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

  public byte getInvestorShares1() {
    return this.investorShares1;
  }

  public byte getInvestorShares2() {
    return this.investorShares2;
  }

  public byte getInvestorShares3() {
    return this.investorShares3;
  }

  public byte getInvestorShares4() {
    return this.investorShares4;
  }

  public double getMinSecurity() {
    return this.minSecurity;
  }

  public long getPublicShares() {
    return this.publicShares;
  }

  public byte getScattered() {
    return this.scattered;
  }

  public String getSize() {
    return this.size;
  }

  public Double getSizeFactor() {
    return this.sizeFactor;
  }

  public int getSolarSystemID() {
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
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrpNpcCorporation>>() {
        @Override
        public List<CrpNpcCorporation> run() throws Exception {
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
        }
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