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
 * The persistent class for the invtypereactions database table.
 * 
 */
@Entity
@Table(
    name = "invtypereactions")
@Immutable
public class InvTypeReaction {
  private static final Logger log = Logger.getLogger(InvTypeReaction.class.getName());

  @EmbeddedId
  private InvTypeReactionPK   id;
  private short               quantity;

  public InvTypeReaction() {}

  public InvTypeReactionPK getId() {
    return this.id;
  }

  public short getQuantity() {
    return this.quantity;
  }

  public static List<InvTypeReaction> access(
                                             final int contid,
                                             final int maxresults,
                                             final AttributeSelector reactionTypeID,
                                             final AttributeSelector input,
                                             final AttributeSelector typeID,
                                             final AttributeSelector quantity) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvTypeReaction>>() {
        @Override
        public List<InvTypeReaction> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvTypeReaction c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.reactionTypeID", reactionTypeID);
          AttributeSelector.addIntSelector(qs, "c", "id.input", input);
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          // Return result
          TypedQuery<InvTypeReaction> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvTypeReaction.class);
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
    return "InvTypeReaction [id=" + id + ", quantity=" + quantity + "]";
  }

}