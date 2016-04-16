package enterprises.orbital.evekit.sde.crt;

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
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the crtrelationships database table.
 * 
 */
@Entity
@Table(
    name = "crtrelationships")
@Immutable
public class CrtRelationship {
  private static final Logger log = Logger.getLogger(CrtRelationship.class.getName());

  @Id
  private int                 relationshipID;
  private int                 childID;
  private byte                grade;
  private Integer             parentID;
  private byte                parentLevel;
  private int                 parentTypeID;

  public CrtRelationship() {}

  public int getRelationshipID() {
    return this.relationshipID;
  }

  public int getChildID() {
    return this.childID;
  }

  public byte getGrade() {
    return this.grade;
  }

  public Integer getParentID() {
    return this.parentID;
  }

  public byte getParentLevel() {
    return this.parentLevel;
  }

  public int getParentTypeID() {
    return this.parentTypeID;
  }

  public static List<CrtRelationship> access(
                                             final int contid,
                                             final int maxresults,
                                             final AttributeSelector relationshipID,
                                             final AttributeSelector childID,
                                             final AttributeSelector grade,
                                             final AttributeSelector parentID,
                                             final AttributeSelector parentLevel,
                                             final AttributeSelector parentTypeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrtRelationship>>() {
        @Override
        public List<CrtRelationship> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrtRelationship c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "relationshipID", relationshipID);
          AttributeSelector.addIntSelector(qs, "c", "childID", childID);
          AttributeSelector.addIntSelector(qs, "c", "grade", grade);
          AttributeSelector.addIntSelector(qs, "c", "parentID", parentID);
          AttributeSelector.addIntSelector(qs, "c", "parentLevel", parentLevel);
          AttributeSelector.addIntSelector(qs, "c", "parentTypeID", parentTypeID);
          // Return result
          TypedQuery<CrtRelationship> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtRelationship.class);
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
    return "CrtRelationship [relationshipID=" + relationshipID + ", childID=" + childID + ", grade=" + grade + ", parentID=" + parentID + ", parentLevel="
        + parentLevel + ", parentTypeID=" + parentTypeID + "]";
  }

}