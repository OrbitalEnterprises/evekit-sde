package enterprises.orbital.evekit.sde.eve;

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
 * The persistent class for the eveicons database table.
 * 
 */
@Entity
@Table(
    name = "eveicons")
public class EveIcon {
  private static final Logger log = Logger.getLogger(EveGraphic.class.getName());

  @Id
  private int                 iconID;
  private String              iconFile;
  private String              description;

  public EveIcon() {}

  public EveIcon(int iconID, String description, String iconFile) {
    super();
    this.iconID = iconID;
    this.description = description;
    this.iconFile = iconFile;
  }

  public int getIconID() {
    return this.iconID;
  }

  public String getDescription() {
    return this.description;
  }

  public String getIconFile() {
    return this.iconFile;
  }

  public static List<EveIcon> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector iconID,
                                     final AttributeSelector description,
                                     final AttributeSelector iconFile) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<EveIcon>>() {
        @Override
        public List<EveIcon> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM EveIcon c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addStringSelector(qs, "c", "iconFile", iconFile, p);
          // Return result
          TypedQuery<EveIcon> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), EveIcon.class);
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
    return "EveIcon [iconID=" + iconID + ", description=" + description + ", iconFile=" + iconFile + "]";
  }

}