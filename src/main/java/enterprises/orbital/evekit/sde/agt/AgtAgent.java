package enterprises.orbital.evekit.sde.agt;

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
 * The persistent class for the agtagents database table.
 * 
 */
@Entity
@Table(
    name = "agtagents")
public class AgtAgent {
  public static final Logger log = Logger.getLogger(AgtAgent.class.getName());

  @Id
  private int                agentID;
  private int               divisionID;
  private int                corporationID;
  private int                locationID;
  private int               level;
  private short              quality;
  private int                agentTypeID;
  private boolean               isLocator;

  public AgtAgent() {}

  public AgtAgent(int agentID, int agentTypeID, int corporationID, int divisionID, boolean isLocator, int level, int locationID, short quality) {
    super();
    this.agentID = agentID;
    this.agentTypeID = agentTypeID;
    this.corporationID = corporationID;
    this.divisionID = divisionID;
    this.isLocator = isLocator;
    this.level = level;
    this.locationID = locationID;
    this.quality = quality;
  }

  public int getAgentID() {
    return this.agentID;
  }

  public int getAgentTypeID() {
    return this.agentTypeID;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  public int getDivisionID() {
    return this.divisionID;
  }

  public boolean isLocator() {
    return this.isLocator;
  }

  public int getLevel() {
    return this.level;
  }

  public int getLocationID() {
    return this.locationID;
  }

  public short getQuality() {
    return this.quality;
  }

  public static List<AgtAgent> access(
                                      final int contid,
                                      final int maxresults,
                                      final AttributeSelector agentID,
                                      final AttributeSelector agentTypeID,
                                      final AttributeSelector corporationID,
                                      final AttributeSelector divisionID,
                                      final AttributeSelector isLocator,
                                      final AttributeSelector level,
                                      final AttributeSelector locationID,
                                      final AttributeSelector quality) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM AgtAgent c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "agentID", agentID);
        AttributeSelector.addIntSelector(qs, "c", "agentTypeID", agentTypeID);
        AttributeSelector.addIntSelector(qs, "c", "corporationID", corporationID);
        AttributeSelector.addIntSelector(qs, "c", "divisionID", divisionID);
        AttributeSelector.addBooleanSelector(qs, "c", "isLocator", isLocator);
        AttributeSelector.addIntSelector(qs, "c", "level", level);
        AttributeSelector.addIntSelector(qs, "c", "locationID", locationID);
        AttributeSelector.addIntSelector(qs, "c", "quality", quality);
        // Return result
        TypedQuery<AgtAgent> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), AgtAgent.class);
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
    return "AgtAgent [agentID=" + agentID + ", agentTypeID=" + agentTypeID + ", corporationID=" + corporationID + ", divisionID=" + divisionID + ", isLocator="
        + isLocator + ", level=" + level + ", locationID=" + locationID + ", quality=" + quality + "]";
  }

}