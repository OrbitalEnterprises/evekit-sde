package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmattributetypes database table.
 * 
 */
@Entity
@Table(
    name = "dgmattributetypes")
public class DgmAttributeType {
  private static final Logger log = Logger.getLogger(DgmAttributeType.class.getName());

  @Id
  private int                 attributeID;
  private String              attributeName;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private Integer             iconID;
  private double              defaultValue;
  private byte                published;
  private String              displayName;
  private Integer             unitID;
  private byte                stackable;
  private byte                highIsGood;
  private Byte                categoryID;

  public DgmAttributeType() {}

  public DgmAttributeType(int attributeID, String attributeName, Byte categoryID, double defaultValue, String description, String displayName, byte highIsGood,
                          Integer iconID, byte published, byte stackable, Integer unitID) {
    super();
    this.attributeID = attributeID;
    this.attributeName = attributeName;
    this.categoryID = categoryID;
    this.defaultValue = defaultValue;
    this.description = description;
    this.displayName = displayName;
    this.highIsGood = highIsGood;
    this.iconID = iconID;
    this.published = published;
    this.stackable = stackable;
    this.unitID = unitID;
  }

  public int getAttributeID() {
    return this.attributeID;
  }

  public String getAttributeName() {
    return this.attributeName;
  }

  public Byte getCategoryID() {
    return this.categoryID;
  }

  public double getDefaultValue() {
    return this.defaultValue;
  }

  public String getDescription() {
    return this.description;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public byte getHighIsGood() {
    return this.highIsGood;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public byte getPublished() {
    return this.published;
  }

  public byte getStackable() {
    return this.stackable;
  }

  public Integer getUnitID() {
    return this.unitID;
  }

  public static List<DgmAttributeType> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector attributeID,
                                              final AttributeSelector attributeName,
                                              final AttributeSelector categoryID,
                                              final AttributeSelector defaultValue,
                                              final AttributeSelector description,
                                              final AttributeSelector displayName,
                                              final AttributeSelector highIsGood,
                                              final AttributeSelector iconID,
                                              final AttributeSelector published,
                                              final AttributeSelector stackable,
                                              final AttributeSelector unitID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmAttributeType>>() {
        @Override
        public List<DgmAttributeType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmAttributeType c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "attributeID", attributeID);
          AttributeSelector.addStringSelector(qs, "c", "attributeName", attributeName, p);
          AttributeSelector.addIntSelector(qs, "c", "categoryID", categoryID);
          AttributeSelector.addDoubleSelector(qs, "c", "defaultValue", defaultValue);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addStringSelector(qs, "c", "displayName", displayName, p);
          AttributeSelector.addIntSelector(qs, "c", "highIsGood", highIsGood);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "published", published);
          AttributeSelector.addIntSelector(qs, "c", "stackable", stackable);
          AttributeSelector.addIntSelector(qs, "c", "unitID", unitID);
          // Return result
          TypedQuery<DgmAttributeType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmAttributeType.class);
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
    return "DgmAttributeType [attributeID=" + attributeID + ", attributeName=" + attributeName + ", categoryID=" + categoryID + ", defaultValue=" + defaultValue
        + ", description=" + description + ", displayName=" + displayName + ", highIsGood=" + highIsGood + ", iconID=" + iconID + ", published=" + published
        + ", stackable=" + stackable + ", unitID=" + unitID + "]";
  }

}