package enterprises.orbital.evekit.sde.war;

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
 * The persistent class for the warcombatzones database table.
 * 
 */
@Entity
@Table(
    name = "warcombatzones")
@Immutable
public class WarCombatZone {
  private static final Logger log = Logger.getLogger(WarCombatZone.class.getName());

  @Id
  private int                 combatZoneID;
  private int                 centerSystemID;
  private String              combatZoneName;
  private String              description;
  private int                 factionID;

  public WarCombatZone() {}

  public int getCombatZoneID() {
    return this.combatZoneID;
  }

  public int getCenterSystemID() {
    return this.centerSystemID;
  }

  public String getCombatZoneName() {
    return this.combatZoneName;
  }

  public String getDescription() {
    return this.description;
  }

  public int getFactionID() {
    return this.factionID;
  }

  public static List<WarCombatZone> access(
                                           final int contid,
                                           final int maxresults,
                                           final AttributeSelector combatZoneID,
                                           final AttributeSelector centerSystemID,
                                           final AttributeSelector combatZoneName,
                                           final AttributeSelector description,
                                           final AttributeSelector factionID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<WarCombatZone>>() {
        @Override
        public List<WarCombatZone> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM WarCombatZone c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "combatZoneID", combatZoneID);
          AttributeSelector.addIntSelector(qs, "c", "centerSystemID", centerSystemID);
          AttributeSelector.addStringSelector(qs, "c", "combatZoneName", combatZoneName, p);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "factionID", factionID);
          // Return result
          TypedQuery<WarCombatZone> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), WarCombatZone.class);
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
    return "WarCombatZone [combatZoneID=" + combatZoneID + ", centerSystemID=" + centerSystemID + ", combatZoneName=" + combatZoneName + ", description="
        + description + ", factionID=" + factionID + "]";
  }

}