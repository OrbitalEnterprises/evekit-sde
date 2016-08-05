package enterprises.orbital.evekit.sde.plt;

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
 * The persistent class for the planetschematicstypemap database table.
 * 
 */
@Entity
@Table(
    name = "planetschematicstypemap")
public class PltSchematicsTypeMap {
  private static final Logger    log = Logger.getLogger(PltSchematicsTypeMap.class.getName());

  @EmbeddedId
  private PltSchematicsTypeMapPK id;
  private byte                   isInput;
  private short                  quantity;

  public PltSchematicsTypeMap() {}

  public PltSchematicsTypeMap(int schematicID, int typeID, byte isInput, short quantity) {
    super();
    this.id = new PltSchematicsTypeMapPK(schematicID, typeID);
    this.isInput = isInput;
    this.quantity = quantity;
  }

  public PltSchematicsTypeMapPK id() {
    return this.id;
  }

  public int getSchematicID() {
    return id.getSchematicID();
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public byte getIsInput() {
    return this.isInput;
  }

  public short getQuantity() {
    return this.quantity;
  }

  public static List<PltSchematicsTypeMap> access(
                                                  final int contid,
                                                  final int maxresults,
                                                  final AttributeSelector schematicID,
                                                  final AttributeSelector typeID,
                                                  final AttributeSelector isInput,
                                                  final AttributeSelector quantity) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<PltSchematicsTypeMap>>() {
        @Override
        public List<PltSchematicsTypeMap> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM PltSchematicsTypeMap c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.schematicID", schematicID);
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "isInput", isInput);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          // Return result
          TypedQuery<PltSchematicsTypeMap> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), PltSchematicsTypeMap.class);
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
    return "PltSchematicsTypeMap [id=" + id + ", isInput=" + isInput + ", quantity=" + quantity + "]";
  }

}