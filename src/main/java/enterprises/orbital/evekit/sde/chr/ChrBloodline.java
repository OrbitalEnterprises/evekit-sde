package enterprises.orbital.evekit.sde.chr;

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
 * The persistent class for the chrbloodlines database table.
 * 
 */
@Entity
@Table(
    name = "chrbloodlines")
@Immutable
public class ChrBloodline {
  public static final Logger log = Logger.getLogger(ChrBloodline.class.getName());

  @Id
  private byte               bloodlineID;
  private String             bloodlineName;
  private byte               charisma;
  private int                corporationID;
  private String             description;
  private String             femaleDescription;
  private int                iconID;
  private byte               intelligence;
  private String             maleDescription;
  private byte               memory;
  private byte               perception;
  private byte               raceID;
  private int                shipTypeID;
  private String             shortDescription;
  private String             shortFemaleDescription;
  private String             shortMaleDescription;
  private byte               willpower;

  public ChrBloodline() {}

  public byte getBloodlineID() {
    return this.bloodlineID;
  }

  public String getBloodlineName() {
    return this.bloodlineName;
  }

  public byte getCharisma() {
    return this.charisma;
  }

  public int getCorporationID() {
    return this.corporationID;
  }

  public String getDescription() {
    return this.description;
  }

  public String getFemaleDescription() {
    return this.femaleDescription;
  }

  public int getIconID() {
    return this.iconID;
  }

  public byte getIntelligence() {
    return this.intelligence;
  }

  public String getMaleDescription() {
    return this.maleDescription;
  }

  public byte getMemory() {
    return this.memory;
  }

  public byte getPerception() {
    return this.perception;
  }

  public byte getRaceID() {
    return this.raceID;
  }

  public int getShipTypeID() {
    return this.shipTypeID;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public String getShortFemaleDescription() {
    return this.shortFemaleDescription;
  }

  public String getShortMaleDescription() {
    return this.shortMaleDescription;
  }

  public byte getWillpower() {
    return this.willpower;
  }

  public static List<ChrBloodline> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector bloodlineID,
                                          final AttributeSelector bloodlineName,
                                          final AttributeSelector charisma,
                                          final AttributeSelector corporationID,
                                          final AttributeSelector description,
                                          final AttributeSelector femaleDescription,
                                          final AttributeSelector iconID,
                                          final AttributeSelector intelligence,
                                          final AttributeSelector maleDescription,
                                          final AttributeSelector memory,
                                          final AttributeSelector perception,
                                          final AttributeSelector raceID,
                                          final AttributeSelector shipTypeID,
                                          final AttributeSelector shortDescription,
                                          final AttributeSelector shortFemaleDescription,
                                          final AttributeSelector shortMaleDescription,
                                          final AttributeSelector willpower) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<ChrBloodline>>() {
        @Override
        public List<ChrBloodline> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM ChrBloodline c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "bloodlineID", bloodlineID);
          AttributeSelector.addStringSelector(qs, "c", "bloodlineName", bloodlineName, p);
          AttributeSelector.addIntSelector(qs, "c", "charisma", charisma);
          AttributeSelector.addIntSelector(qs, "c", "corporationID", corporationID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addStringSelector(qs, "c", "femaleDescription", femaleDescription, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "intelligence", intelligence);
          AttributeSelector.addStringSelector(qs, "c", "maleDescription", maleDescription, p);
          AttributeSelector.addIntSelector(qs, "c", "memory", memory);
          AttributeSelector.addIntSelector(qs, "c", "perception", perception);
          AttributeSelector.addIntSelector(qs, "c", "raceID", raceID);
          AttributeSelector.addIntSelector(qs, "c", "shipTypeID", shipTypeID);
          AttributeSelector.addStringSelector(qs, "c", "shortDescription", shortDescription, p);
          AttributeSelector.addStringSelector(qs, "c", "shortFemaleDescription", shortFemaleDescription, p);
          AttributeSelector.addStringSelector(qs, "c", "shortMaleDescription", shortMaleDescription, p);
          AttributeSelector.addIntSelector(qs, "c", "willpower", willpower);
          // Return result
          TypedQuery<ChrBloodline> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), ChrBloodline.class);
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
    return "ChrBloodline [bloodlineID=" + bloodlineID + ", bloodlineName=" + bloodlineName + ", charisma=" + charisma + ", corporationID=" + corporationID
        + ", description=" + description + ", femaleDescription=" + femaleDescription + ", iconID=" + iconID + ", intelligence=" + intelligence
        + ", maleDescription=" + maleDescription + ", memory=" + memory + ", perception=" + perception + ", raceID=" + raceID + ", shipTypeID=" + shipTypeID
        + ", shortDescription=" + shortDescription + ", shortFemaleDescription=" + shortFemaleDescription + ", shortMaleDescription=" + shortMaleDescription
        + ", willpower=" + willpower + "]";
  }

}