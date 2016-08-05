package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmtypetraits database table.
 * 
 */
@Entity
@Table(
    name = "dgmtypetraits")
@Immutable
public class DgmTypeTrait {
  private static final Logger log = Logger.getLogger(DgmTypeTrait.class.getName());

  @EmbeddedId
  private DgmTypeTraitPK      id;
  private Double              bonus;

  public DgmTypeTrait() {}

  public DgmTypeTrait(int typeID, int parentTypeID, int traitID, Double bonus) {
    super();
    this.id = new DgmTypeTraitPK(typeID, parentTypeID, traitID);
    this.bonus = bonus;
  }

  public DgmTypeTraitPK id() {
    return this.id;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getParentTypeID() {
    return id.getParentTypeID();
  }

  public int getTraitID() {
    return id.getTraitID();
  }

  public Double getBonus() {
    return this.bonus;
  }

  public static List<DgmTypeTrait> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector typeID,
                                          final AttributeSelector parentTypeID,
                                          final AttributeSelector traitID,
                                          final AttributeSelector bonus) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmTypeTrait>>() {
        @Override
        public List<DgmTypeTrait> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmTypeTrait c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.parentTypeID", parentTypeID);
          AttributeSelector.addIntSelector(qs, "c", "id.traitID", traitID);
          AttributeSelector.addDoubleSelector(qs, "c", "bonus", bonus);
          // Return result
          TypedQuery<DgmTypeTrait> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmTypeTrait.class);
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
    return "DgmTypeTrait [id=" + id + ", bonus=" + bonus + "]";
  }

}
