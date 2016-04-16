package enterprises.orbital.evekit.sde.inv;

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
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invtypematerials database table.
 * 
 */
@Entity
@Table(
    name = "invtypematerials")
@Immutable
public class InvTypeMaterial {
  private static final Logger log = Logger.getLogger(InvTypeMaterial.class.getName());

  @EmbeddedId
  private InvTypeMaterialPK   id;
  private int                 quantity;

  public InvTypeMaterial() {}

  public InvTypeMaterialPK getId() {
    return this.id;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public static List<InvTypeMaterial> access(
                                             final int contid,
                                             final int maxresults,
                                             final AttributeSelector typeID,
                                             final AttributeSelector materialTypeID,
                                             final AttributeSelector quantity) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvTypeMaterial>>() {
        @Override
        public List<InvTypeMaterial> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvTypeMaterial c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.materialTypeID", materialTypeID);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          // Return result
          TypedQuery<InvTypeMaterial> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvTypeMaterial.class);
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
    return "InvTypeMaterial [id=" + id + ", quantity=" + quantity + "]";
  }

}