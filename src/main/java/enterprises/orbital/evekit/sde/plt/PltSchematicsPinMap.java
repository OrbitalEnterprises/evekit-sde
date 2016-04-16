package enterprises.orbital.evekit.sde.plt;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the planetschematicspinmap database table.
 * 
 */
@Entity
@Table(
    name = "planetschematicspinmap")
@Immutable
public class PltSchematicsPinMap {
  private static final Logger   log = Logger.getLogger(PltSchematicsPinMap.class.getName());

  @EmbeddedId
  private PltSchematicsPinMapPK id;

  public PltSchematicsPinMap() {}

  public PltSchematicsPinMapPK getId() {
    return this.id;
  }

  public static List<PltSchematicsPinMap> access(
                                                 final int contid,
                                                 final int maxresults,
                                                 final AttributeSelector schematicID,
                                                 final AttributeSelector pinTypeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<PltSchematicsPinMap>>() {
        @Override
        public List<PltSchematicsPinMap> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM PltSchematicsPinMap c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.schematicID", schematicID);
          AttributeSelector.addIntSelector(qs, "c", "id.pinTypeID", pinTypeID);
          // Return result
          TypedQuery<PltSchematicsPinMap> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), PltSchematicsPinMap.class);
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
    return "PltSchematicsPinMap [id=" + id + "]";
  }

}