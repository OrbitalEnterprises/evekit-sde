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
  private Byte                collidable;
  private String              colorScheme;
  private String              description;
  private Integer             directoryID;
  private String              gfxRaceID;
  private String              graphicFile;
  private String              graphicName;
  private String              graphicType;
  private byte                obsolete;
  private String              sofHullName;

  public EveGraphic() {}

  public EveGraphic(int graphicID, Byte collidable, String colorScheme, String description, Integer directoryID, String gfxRaceID, String graphicFile,
                    String graphicName, String graphicType, byte obsolete, String sofHullName) {
    super();
    this.graphicID = graphicID;
    this.collidable = collidable;
    this.colorScheme = colorScheme;
    this.description = description;
    this.directoryID = directoryID;
    this.gfxRaceID = gfxRaceID;
    this.graphicFile = graphicFile;
    this.graphicName = graphicName;
    this.graphicType = graphicType;
    this.obsolete = obsolete;
    this.sofHullName = sofHullName;
  }

  public int getGraphicID() {
    return this.graphicID;
  }

  public Byte getCollidable() {
    return this.collidable;
  }

  public String getColorScheme() {
    return this.colorScheme;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getDirectoryID() {
    return this.directoryID;
  }

  public String getGfxRaceID() {
    return this.gfxRaceID;
  }

  public String getGraphicFile() {
    return this.graphicFile;
  }

  public String getGraphicName() {
    return this.graphicName;
  }

  public String getGraphicType() {
    return this.graphicType;
  }

  public byte getObsolete() {
    return this.obsolete;
  }

  public String getSofHullName() {
    return this.sofHullName;
  }

  public static List<EveGraphic> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector graphicID,
                                        final AttributeSelector collidable,
                                        final AttributeSelector colorScheme,
                                        final AttributeSelector description,
                                        final AttributeSelector directoryID,
                                        final AttributeSelector gfxRaceID,
                                        final AttributeSelector graphicFile,
                                        final AttributeSelector graphicName,
                                        final AttributeSelector graphicType,
                                        final AttributeSelector obsolete,
                                        final AttributeSelector sofHullName) {
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
          AttributeSelector.addIntSelector(qs, "c", "collidable", collidable);
          AttributeSelector.addStringSelector(qs, "c", "colorScheme", colorScheme, p);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "directoryID", directoryID);
          AttributeSelector.addStringSelector(qs, "c", "gfxRaceID", gfxRaceID, p);
          AttributeSelector.addStringSelector(qs, "c", "graphicFile", graphicFile, p);
          AttributeSelector.addStringSelector(qs, "c", "graphicName", graphicName, p);
          AttributeSelector.addStringSelector(qs, "c", "graphicType", graphicType, p);
          AttributeSelector.addIntSelector(qs, "c", "obsolete", obsolete);
          AttributeSelector.addStringSelector(qs, "c", "sofHullName", sofHullName, p);
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
    return "EveGraphic [graphicID=" + graphicID + ", collidable=" + collidable + ", colorScheme=" + colorScheme + ", description=" + description
        + ", directoryID=" + directoryID + ", gfxRaceID=" + gfxRaceID + ", graphicFile=" + graphicFile + ", graphicName=" + graphicName + ", graphicType="
        + graphicType + ", obsolete=" + obsolete + ", sofHullName=" + sofHullName + "]";
  }

}