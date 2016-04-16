package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmtraits database table.
 * 
 */
@Entity
@Table(
    name = "dgmtraits")
@Immutable
public class DgmTrait {
  private static final Logger log = Logger.getLogger(DgmTrait.class.getName());

  @Id
  private int                 traitID;
  private String              bonusText;
  private Integer             unitID;

  public DgmTrait() {}

  public int getTraitID() {
    return this.traitID;
  }

  public String getBonusText() {
    return this.bonusText;
  }

  public Integer getUnitID() {
    return this.unitID;
  }

  public static List<DgmTrait> access(
                                      final int contid,
                                      final int maxresults,
                                      final AttributeSelector traitID,
                                      final AttributeSelector bonusText,
                                      final AttributeSelector unitID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmTrait>>() {
        @Override
        public List<DgmTrait> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmTrait c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "traitID", traitID);
          AttributeSelector.addStringSelector(qs, "c", "bonusText", bonusText, p);
          AttributeSelector.addIntSelector(qs, "c", "unitID", unitID);
          // Return result
          TypedQuery<DgmTrait> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmTrait.class);
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
    return "DgmTrait [traitID=" + traitID + ", bonusText=" + bonusText + ", unitID=" + unitID + "]";
  }

}