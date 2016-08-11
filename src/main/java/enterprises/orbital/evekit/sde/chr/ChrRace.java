package enterprises.orbital.evekit.sde.chr;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the chrraces database table.
 * 
 */
@Entity
@Table(
    name = "chrraces")
public class ChrRace {
  public static final Logger log = Logger.getLogger(ChrRace.class.getName());

  @Id
  private int                raceID;
  private String             raceName;
  @Lob
  @Column(
      length = 102400)
  private String             description;
  private Integer            iconID;
  private String             shortDescription;

  public ChrRace() {}

  public ChrRace(int raceID, String description, Integer iconID, String raceName, String shortDescription) {
    super();
    this.raceID = raceID;
    this.description = description;
    this.iconID = iconID;
    this.raceName = raceName;
    this.shortDescription = shortDescription;
  }

  public int getRaceID() {
    return this.raceID;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public String getRaceName() {
    return this.raceName;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public static List<ChrRace> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector raceID,
                                     final AttributeSelector description,
                                     final AttributeSelector iconID,
                                     final AttributeSelector raceName,
                                     final AttributeSelector shortDescription) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<ChrRace>>() {
        @Override
        public List<ChrRace> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM ChrRace c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "raceID", raceID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addStringSelector(qs, "c", "raceName", raceName, p);
          AttributeSelector.addStringSelector(qs, "c", "shortDescription", shortDescription, p);
          // Return result
          TypedQuery<ChrRace> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), ChrRace.class);
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
    return "ChrRace [raceID=" + raceID + ", description=" + description + ", iconID=" + iconID + ", raceName=" + raceName + ", shortDescription="
        + shortDescription + "]";
  }

}