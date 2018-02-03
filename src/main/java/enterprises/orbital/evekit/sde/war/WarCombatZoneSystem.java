package enterprises.orbital.evekit.sde.war;

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
 * The persistent class for the warcombatzonesystems database table.
 * 
 */
@Entity
@Table(
    name = "warcombatzonesystems")
public class WarCombatZoneSystem {
  private static final Logger log = Logger.getLogger(WarCombatZoneSystem.class.getName());
  @Id
  private int                 solarSystemID;
  private int                 combatZoneID;

  public WarCombatZoneSystem() {}

  public WarCombatZoneSystem(int solarSystemID, int combatZoneID) {
    super();
    this.solarSystemID = solarSystemID;
    this.combatZoneID = combatZoneID;
  }

  public int getSolarSystemID() {
    return this.solarSystemID;
  }

  public int getCombatZoneID() {
    return this.combatZoneID;
  }

  public static List<WarCombatZoneSystem> access(
                                                 final int contid,
                                                 final int maxresults,
                                                 final AttributeSelector solarSystemID,
                                                 final AttributeSelector combatZoneID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM WarCombatZoneSystem c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "solarSystemID", solarSystemID);
        AttributeSelector.addIntSelector(qs, "c", "combatZoneID", combatZoneID);
        // Return result
        TypedQuery<WarCombatZoneSystem> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), WarCombatZoneSystem.class);
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
    return "WarCombatZoneSystem [solarSystemID=" + solarSystemID + ", combatZoneID=" + combatZoneID + "]";
  }

}