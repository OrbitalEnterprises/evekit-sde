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
 * The persistent class for the evegraphics database table.
 * 
 */
@Entity
@Table(
    name = "evegraphics")
public class EveGraphic {
  private static final Logger log = Logger.getLogger(EveGraphic.class.getName());

  @Id
  private int                 graphicID;
  private String              sofFactionName;
  private String              graphicFile;
  private String              sofHullName;
  private String              sofRaceName;
  private String              description;

  public EveGraphic() {}

  public EveGraphic(int graphicID, String sofFactionName, String graphicFile, String sofHullName, String sofRaceName, String description) {
    super();
    this.graphicID = graphicID;
    this.sofFactionName = sofFactionName;
    this.graphicFile = graphicFile;
    this.sofHullName = sofHullName;
    this.sofRaceName = sofRaceName;
    this.description = description;
  }

  public int getGraphicID() {
    return graphicID;
  }

  public String getSofFactionName() {
    return sofFactionName;
  }

  public String getGraphicFile() {
    return graphicFile;
  }

  public String getSofHullName() {
    return sofHullName;
  }

  public String getSofRaceName() {
    return sofRaceName;
  }

  public String getDescription() {
    return description;
  }

  public static List<EveGraphic> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector graphicID,
                                        final AttributeSelector sofFactionName,
                                        final AttributeSelector graphicFile,
                                        final AttributeSelector sofHullName,
                                        final AttributeSelector sofRaceName,
                                        final AttributeSelector description) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<EveGraphic>>() {
        @Override
        public List<EveGraphic> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM EveGraphic c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "graphicID", graphicID);
          AttributeSelector.addStringSelector(qs, "c", "sofFactionName", sofFactionName, p);
          AttributeSelector.addStringSelector(qs, "c", "graphicFile", graphicFile, p);
          AttributeSelector.addStringSelector(qs, "c", "sofHullName", sofHullName, p);
          AttributeSelector.addStringSelector(qs, "c", "sofRaceName", sofRaceName, p);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          // Return result
          TypedQuery<EveGraphic> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), EveGraphic.class);
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
    return "EveGraphic [graphicID=" + graphicID + ", sofFactionName=" + sofFactionName + ", graphicFile=" + graphicFile + ", sofHullName=" + sofHullName
        + ", sofRaceName=" + sofRaceName + ", description=" + description + "]";
  }

}