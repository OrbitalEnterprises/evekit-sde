package enterprises.orbital.evekit.sde.chr;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the chrbloodlines database table.
 * 
 */
@Entity
@Table(
    name = "chrbloodlines")
public class ChrBloodline {
  public static final Logger log = Logger.getLogger(ChrBloodline.class.getName());

  @Id
  private int               bloodlineID;
  private String             bloodlineName;
  private int               raceID;
  @Lob
  @Column(
      length = 102400)
  private String             description;
  @Lob
  @Column(
      length = 102400)
  private String             maleDescription;
  @Lob
  @Column(
      length = 102400)
  private String             femaleDescription;
  private int                shipTypeID;
  private int                corporationID;
  private int               perception;
  private int               willpower;
  private int               charisma;
  private int               memory;
  private int               intelligence;
  private Integer                iconID;
  private String             shortDescription;
  private String             shortFemaleDescription;
  private String             shortMaleDescription;

  public ChrBloodline() {}

  public ChrBloodline(int bloodlineID, String bloodlineName, int charisma, int corporationID, String description, String femaleDescription, Integer iconID,
                      int intelligence, String maleDescription, int memory, int perception, int raceID, int shipTypeID, String shortDescription,
                      String shortFemaleDescription, String shortMaleDescription, int willpower) {
    super();
    this.bloodlineID = bloodlineID;
    this.bloodlineName = bloodlineName;
    this.charisma = charisma;
    this.corporationID = corporationID;
    this.description = description;
    this.femaleDescription = femaleDescription;
    this.iconID = iconID;
    this.intelligence = intelligence;
    this.maleDescription = maleDescription;
    this.memory = memory;
    this.perception = perception;
    this.raceID = raceID;
    this.shipTypeID = shipTypeID;
    this.shortDescription = shortDescription;
    this.shortFemaleDescription = shortFemaleDescription;
    this.shortMaleDescription = shortMaleDescription;
    this.willpower = willpower;
  }

  public int getBloodlineID() {
    return this.bloodlineID;
  }

  public String getBloodlineName() {
    return this.bloodlineName;
  }

  public int getCharisma() {
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

  public Integer getIconID() {
    return this.iconID;
  }

  public int getIntelligence() {
    return this.intelligence;
  }

  public String getMaleDescription() {
    return this.maleDescription;
  }

  public int getMemory() {
    return this.memory;
  }

  public int getPerception() {
    return this.perception;
  }

  public int getRaceID() {
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

  public int getWillpower() {
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
      return SDE.getFactory().runTransaction(() -> {
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