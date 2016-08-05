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
 * The persistent class for the chrancestries database table.
 * 
 */
@Entity
@Table(
    name = "chrancestries")
public class ChrAncestry {
  public static final Logger log = Logger.getLogger(ChrAncestry.class.getName());

  @Id
  private byte               ancestryID;
  private String             ancestryName;
  private byte               bloodlineID;
  private byte               charisma;
  @Lob
  @Column(
      length = 102400)
  private String             description;
  private Integer            iconID;
  private byte               intelligence;
  private byte               memory;
  private byte               perception;
  private String             shortDescription;
  private byte               willpower;

  public ChrAncestry() {}

  public ChrAncestry(byte ancestryID, String ancestryName, byte bloodlineID, byte charisma, String description, Integer iconID, byte intelligence, byte memory,
                     byte perception, String shortDescription, byte willpower) {
    super();
    this.ancestryID = ancestryID;
    this.ancestryName = ancestryName;
    this.bloodlineID = bloodlineID;
    this.charisma = charisma;
    this.description = description;
    this.iconID = iconID;
    this.intelligence = intelligence;
    this.memory = memory;
    this.perception = perception;
    this.shortDescription = shortDescription;
    this.willpower = willpower;
  }

  public byte getAncestryID() {
    return this.ancestryID;
  }

  public String getAncestryName() {
    return this.ancestryName;
  }

  public byte getBloodlineID() {
    return this.bloodlineID;
  }

  public byte getCharisma() {
    return this.charisma;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getIconID() {
    return iconID;
  }

  public byte getIntelligence() {
    return this.intelligence;
  }

  public byte getMemory() {
    return this.memory;
  }

  public byte getPerception() {
    return this.perception;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public byte getWillpower() {
    return this.willpower;
  }

  public static List<ChrAncestry> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector ancestryID,
                                         final AttributeSelector ancestryName,
                                         final AttributeSelector bloodlineID,
                                         final AttributeSelector charisma,
                                         final AttributeSelector description,
                                         final AttributeSelector iconID,
                                         final AttributeSelector intelligence,
                                         final AttributeSelector memory,
                                         final AttributeSelector perception,
                                         final AttributeSelector shortDescription,
                                         final AttributeSelector willpower) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<ChrAncestry>>() {
        @Override
        public List<ChrAncestry> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM ChrAncestry c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "ancestryID", ancestryID);
          AttributeSelector.addStringSelector(qs, "c", "ancestryName", ancestryName, p);
          AttributeSelector.addIntSelector(qs, "c", "bloodlineID", bloodlineID);
          AttributeSelector.addIntSelector(qs, "c", "charisma", charisma);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "intelligence", intelligence);
          AttributeSelector.addIntSelector(qs, "c", "memory", memory);
          AttributeSelector.addIntSelector(qs, "c", "perception", perception);
          AttributeSelector.addStringSelector(qs, "c", "shortDescription", shortDescription, p);
          AttributeSelector.addIntSelector(qs, "c", "willpower", willpower);
          // Return result
          TypedQuery<ChrAncestry> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), ChrAncestry.class);
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
    return "ChrAncestry [ancestryID=" + ancestryID + ", ancestryName=" + ancestryName + ", bloodlineID=" + bloodlineID + ", charisma=" + charisma
        + ", description=" + description + ", iconID=" + iconID + ", intelligence=" + intelligence + ", memory=" + memory + ", perception=" + perception
        + ", shortDescription=" + shortDescription + ", willpower=" + willpower + "]";
  }

}