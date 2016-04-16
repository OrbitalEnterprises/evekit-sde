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
 * The persistent class for the dgmtypeattributes database table.
 * 
 */
@Entity
@Table(
    name = "dgmtypeattributes")
@Immutable
public class DgmTypeAttribute {
  private static final Logger log = Logger.getLogger(DgmTypeAttribute.class.getName());

  @EmbeddedId
  private DgmTypeAttributePK  id;
  private Double              valueFloat;
  private Integer             valueInt;

  public DgmTypeAttribute() {}

  public DgmTypeAttributePK getId() {
    return this.id;
  }

  public Double getValueFloat() {
    return this.valueFloat;
  }

  public Integer getValueInt() {
    return this.valueInt;
  }

  public static List<DgmTypeAttribute> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector typeID,
                                              final AttributeSelector attributeID,
                                              final AttributeSelector valueFloat,
                                              final AttributeSelector valueInt) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmTypeAttribute>>() {
        @Override
        public List<DgmTypeAttribute> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmTypeAttribute c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.attributeID", attributeID);
          AttributeSelector.addDoubleSelector(qs, "c", "valueFloat", valueFloat);
          AttributeSelector.addIntSelector(qs, "c", "valueInt", valueInt);
          // Return result
          TypedQuery<DgmTypeAttribute> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmTypeAttribute.class);
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
    return "DgmTypeAttribute [id=" + id + ", valueFloat=" + valueFloat + ", valueInt=" + valueInt + "]";
  }

}