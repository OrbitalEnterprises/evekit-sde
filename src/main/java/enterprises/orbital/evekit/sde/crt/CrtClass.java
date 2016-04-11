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
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the crtclasses database table.
 * 
 */
@Entity
@Table(
    name = "crtclasses")
@Immutable
public class CrtClass {
  private static final Logger log = Logger.getLogger(CrtClass.class.getName());

  @Id
  private int                 classID;
  private String              className;
  private String              description;

  public CrtClass() {}

  public int getClassID() {
    return this.classID;
  }

  public String getClassName() {
    return this.className;
  }

  public String getDescription() {
    return this.description;
  }

  public static List<CrtClass> access(
                                      final int contid,
                                      final int maxresults,
                                      final AttributeSelector classID,
                                      final AttributeSelector className,
                                      final AttributeSelector description) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrtClass>>() {
        @Override
        public List<CrtClass> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrtClass c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "classID", classID);
          AttributeSelector.addStringSelector(qs, "c", "className", className, p);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          // Return result
          TypedQuery<CrtClass> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtClass.class);
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
    return "CrtClass [classID=" + classID + ", className=" + className + ", description=" + description + "]";
  }

}