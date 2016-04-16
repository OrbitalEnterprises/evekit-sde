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
 * The persistent class for the crtcertificates database table.
 * 
 */
@Entity
@Table(
    name = "crtcertificates")
@Immutable
public class CrtCertificate {
  private static final Logger log = Logger.getLogger(CrtCertificate.class.getName());

  @Id
  private int                 certificateID;
  private int                 classID;
  private Integer             corpID;
  private String              description;
  private Byte                grade;
  private int                 groupID;
  private Integer             iconID;

  public CrtCertificate() {}

  public int getCertificateID() {
    return this.certificateID;
  }

  public int getClassID() {
    return this.classID;
  }

  public Integer getCorpID() {
    return this.corpID;
  }

  public String getDescription() {
    return this.description;
  }

  public Byte getGrade() {
    return this.grade;
  }

  public int getGroupID() {
    return this.groupID;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public static List<CrtCertificate> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector certificateID,
                                            final AttributeSelector classID,
                                            final AttributeSelector corpID,
                                            final AttributeSelector description,
                                            final AttributeSelector grade,
                                            final AttributeSelector groupID,
                                            final AttributeSelector iconID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrtCertificate>>() {
        @Override
        public List<CrtCertificate> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrtCertificate c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "certificateID", certificateID);
          AttributeSelector.addIntSelector(qs, "c", "classID", classID);
          AttributeSelector.addIntSelector(qs, "c", "corpID", corpID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "grade", grade);
          AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          // Return result
          TypedQuery<CrtCertificate> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtCertificate.class);
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
    return "CrtCertificate [certificateID=" + certificateID + ", classID=" + classID + ", corpID=" + corpID + ", description=" + description + ", grade="
        + grade + ", groupID=" + groupID + ", iconID=" + iconID + "]";
  }

}