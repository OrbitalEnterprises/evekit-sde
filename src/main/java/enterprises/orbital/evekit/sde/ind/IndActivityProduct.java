package enterprises.orbital.evekit.sde.ind;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the industryactivityproducts database table.
 * 
 */
@Entity
@Table(
    name = "industryactivityproducts")
public class IndActivityProduct {
  private static final Logger  log = Logger.getLogger(IndActivityProduct.class.getName());

  @EmbeddedId
  private IndActivityProductPK id;
  private int                  quantity;

  public IndActivityProduct() {}

  public IndActivityProduct(int typeID, int activityID, int productTypeID, int quantity) {
    super();
    this.id = new IndActivityProductPK(typeID, activityID, productTypeID);
    this.quantity = quantity;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public int getActivityID() {
    return id.getActivityID();
  }

  public int getProductTypeID() {
    return id.getProductTypeID();
  }

  public int getQuantity() {
    return quantity;
  }

  public static List<IndActivityProduct> access(
                                                final int contid,
                                                final int maxresults,
                                                final AttributeSelector typeID,
                                                final AttributeSelector activityID,
                                                final AttributeSelector productTypeID,
                                                final AttributeSelector quantity) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<IndActivityProduct>>() {
        @Override
        public List<IndActivityProduct> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM IndActivityProduct c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.activityID", activityID);
          AttributeSelector.addIntSelector(qs, "c", "id.productTypeID", productTypeID);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          // Return result
          TypedQuery<IndActivityProduct> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), IndActivityProduct.class);
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
    return "IndActivityProduct [id=" + id + ", quantity=" + quantity + "]";
  }

}
