package enterprises.orbital.evekit.sde.crt;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the crtcertificates database table.
 * 
 */
@Entity
@Table(
    name = "certcerts")
public class CrtCertificate {
  private static final Logger log = Logger.getLogger(CrtCertificate.class.getName());

  @Id
  private int                 certID;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private int                 groupID;
  private String              name;

  public CrtCertificate() {}

  public CrtCertificate(int certificateID, String description, int groupID, String name) {
    super();
    this.certID = certificateID;
    this.description = description;
    this.groupID = groupID;
    this.name = name;
  }

  public int getCertID() {
    return this.certID;
  }

  public String getDescription() {
    return this.description;
  }

  public int getGroupID() {
    return this.groupID;
  }

  public String getName() {
    return name;
  }

  public static List<CrtCertificate> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector certID,
                                            final AttributeSelector description,
                                            final AttributeSelector groupID,
                                            final AttributeSelector name) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM CrtCertificate c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "certID", certID);
        AttributeSelector.addStringSelector(qs, "c", "description", description, p);
        AttributeSelector.addIntSelector(qs, "c", "groupID", groupID);
        AttributeSelector.addStringSelector(qs, "c", "name", name, p);
        // Return result
        TypedQuery<CrtCertificate> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtCertificate.class);
        p.fillParams(query);
        query.setMaxResults(maxcount);
        query.setFirstResult(offset);
        return query.getResultList();
      });
    } catch (Exception e) {
      log.log(Level.SEVERE, "query error", e);
    }
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    return "CrtCertificate [certID=" + certID + ", description=" + description + ", groupID=" + groupID + ", name=" + name + "]";
  }

}