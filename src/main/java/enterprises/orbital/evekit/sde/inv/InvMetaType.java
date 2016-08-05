package enterprises.orbital.evekit.sde.inv;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invmetatypes database table.
 * 
 */
@Entity
@Table(
    name = "invmetatypes")
public class InvMetaType {
  private static final Logger log = Logger.getLogger(InvMetaType.class.getName());

  @Id
  private int                 typeID;
  private int                 metaGroupID;
  private int                 parentTypeID;

  public InvMetaType() {}

  public InvMetaType(int typeID, int metaGroupID, int parentTypeID) {
    super();
    this.typeID = typeID;
    this.metaGroupID = metaGroupID;
    this.parentTypeID = parentTypeID;
  }

  public int getTypeID() {
    return this.typeID;
  }

  public int getMetaGroupID() {
    return this.metaGroupID;
  }

  public int getParentTypeID() {
    return this.parentTypeID;
  }

  public static List<InvMetaType> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector typeID,
                                         final AttributeSelector metaGroupID,
                                         final AttributeSelector parentTypeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvMetaType>>() {
        @Override
        public List<InvMetaType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvMetaType c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "metaGroupID", metaGroupID);
          AttributeSelector.addIntSelector(qs, "c", "parentTypeID", parentTypeID);
          // Return result
          TypedQuery<InvMetaType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvMetaType.class);
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
    return "InvMetaType [typeID=" + typeID + ", metaGroupID=" + metaGroupID + ", parentTypeID=" + parentTypeID + "]";
  }

}