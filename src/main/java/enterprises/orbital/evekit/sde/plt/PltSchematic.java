package enterprises.orbital.evekit.sde.plt;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the planetschematics database table.
 * 
 */
@Entity
@Table(
    name = "planetschematics")
public class PltSchematic {
  private static final Logger log = Logger.getLogger(PltSchematic.class.getName());

  @Id
  private int                 schematicID;
  private int                 cycleTime;
  private String              schematicName;

  public PltSchematic() {}

  public PltSchematic(int schematicID, int cycleTime, String schematicName) {
    super();
    this.schematicID = schematicID;
    this.cycleTime = cycleTime;
    this.schematicName = schematicName;
  }

  public int getSchematicID() {
    return this.schematicID;
  }

  public int getCycleTime() {
    return this.cycleTime;
  }

  public String getSchematicName() {
    return this.schematicName;
  }

  public static List<PltSchematic> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector schematicID,
                                          final AttributeSelector cycleTime,
                                          final AttributeSelector schematicName) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<PltSchematic>>() {
        @Override
        public List<PltSchematic> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM PltSchematic c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "schematicID", schematicID);
          AttributeSelector.addIntSelector(qs, "c", "cycleTime", cycleTime);
          AttributeSelector.addStringSelector(qs, "c", "schematicName", schematicName, p);
          // Return result
          TypedQuery<PltSchematic> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), PltSchematic.class);
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
    return "PltSchematic [schematicID=" + schematicID + ", cycleTime=" + cycleTime + ", schematicName=" + schematicName + "]";
  }

}