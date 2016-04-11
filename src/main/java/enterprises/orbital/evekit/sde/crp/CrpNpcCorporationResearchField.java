package enterprises.orbital.evekit.sde.crp;

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
 * The persistent class for the crpnpccorporationresearchfields database table.
 * 
 */
@Entity
@Table(
    name = "crpnpccorporationresearchfields")
@Immutable
public class CrpNpcCorporationResearchField {
  private static final Logger              log = Logger.getLogger(CrpNpcCorporationResearchField.class.getName());

  @EmbeddedId
  private CrpNpcCorporationResearchFieldPK id;

  public CrpNpcCorporationResearchField() {}

  public CrpNpcCorporationResearchFieldPK getId() {
    return this.id;
  }

  public static List<CrpNpcCorporationResearchField> access(
                                                            final int contid,
                                                            final int maxresults,
                                                            final AttributeSelector skillID,
                                                            final AttributeSelector corporationID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrpNpcCorporationResearchField>>() {
        @Override
        public List<CrpNpcCorporationResearchField> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrpNpcCorporationResearchField c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.skillID", skillID);
          AttributeSelector.addIntSelector(qs, "c", "id.corporationID", corporationID);
          // Return result
          TypedQuery<CrpNpcCorporationResearchField> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(),
                                                                                                             CrpNpcCorporationResearchField.class);
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
    return "CrpNpcCorporationResearchField [id=" + id + "]";
  }

}