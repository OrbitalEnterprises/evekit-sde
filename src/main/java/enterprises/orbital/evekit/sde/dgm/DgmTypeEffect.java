package enterprises.orbital.evekit.sde.dgm;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the dgmtypeeffects database table.
 * 
 */
@Entity
@Table(
    name = "dgmtypeeffects")
public class DgmTypeEffect {
  private static final Logger log = Logger.getLogger(DgmTypeEffect.class.getName());

  @EmbeddedId
  private DgmTypeEffectPK     id;
  private byte                isDefault;

  public DgmTypeEffect() {}

  public DgmTypeEffect(int typeID, int effectID, byte isDefault) {
    super();
    this.id = new DgmTypeEffectPK(typeID, effectID);
    this.isDefault = isDefault;
  }

  public DgmTypeEffectPK id() {
    return this.id;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getEffectID() {
    return id.getEffectID();
  }

  public byte getIsDefault() {
    return this.isDefault;
  }

  public static List<DgmTypeEffect> access(
                                           final int contid,
                                           final int maxresults,
                                           final AttributeSelector typeID,
                                           final AttributeSelector effectID,
                                           final AttributeSelector isDefault) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmTypeEffect>>() {
        @Override
        public List<DgmTypeEffect> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmTypeEffect c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.effectID", effectID);
          AttributeSelector.addIntSelector(qs, "c", "isDefault", isDefault);
          // Return result
          TypedQuery<DgmTypeEffect> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmTypeEffect.class);
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
    return "DgmTypeEffect [id=" + id + ", isDefault=" + isDefault + "]";
  }

}