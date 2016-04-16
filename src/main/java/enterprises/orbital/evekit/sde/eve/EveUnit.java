package enterprises.orbital.evekit.sde.eve;

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
 * The persistent class for the eveunits database table.
 * 
 */
@Entity
@Table(
    name = "eveunits")
@Immutable
public class EveUnit {
  private static final Logger log = Logger.getLogger(EveUnit.class.getName());

  @Id
  private int                 unitID;
  private String              description;
  private String              displayName;
  private String              unitName;

  public EveUnit() {}

  public int getUnitID() {
    return this.unitID;
  }

  public String getDescription() {
    return this.description;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public String getUnitName() {
    return this.unitName;
  }

  public static List<EveUnit> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector unitID,
                                     final AttributeSelector description,
                                     final AttributeSelector displayName,
                                     final AttributeSelector unitName) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<EveUnit>>() {
        @Override
        public List<EveUnit> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM EveUnit c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "unitID", unitID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addStringSelector(qs, "c", "displayName", displayName, p);
          AttributeSelector.addStringSelector(qs, "c", "unitName", unitName, p);
          // Return result
          TypedQuery<EveUnit> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), EveUnit.class);
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
    return "EveUnit [unitID=" + unitID + ", description=" + description + ", displayName=" + displayName + ", unitName=" + unitName + "]";
  }

}