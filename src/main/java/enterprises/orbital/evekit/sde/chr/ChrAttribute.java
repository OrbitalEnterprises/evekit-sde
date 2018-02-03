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
 * The persistent class for the chrattributes database table.
 * 
 */
@Entity
@Table(
    name = "chrattributes")
public class ChrAttribute {
  public static final Logger log = Logger.getLogger(ChrAttribute.class.getName());

  @Id
  private int               attributeID;
  private String             attributeName;
  @Lob
  @Column(
      length = 102400)
  private String             description;
  private int                iconID;
  private String             shortDescription;
  private String             notes;

  public ChrAttribute() {}

  public ChrAttribute(int attributeID, String attributeName, String description, int iconID, String notes, String shortDescription) {
    super();
    this.attributeID = attributeID;
    this.attributeName = attributeName;
    this.description = description;
    this.iconID = iconID;
    this.notes = notes;
    this.shortDescription = shortDescription;
  }

  public int getAttributeID() {
    return this.attributeID;
  }

  public String getAttributeName() {
    return this.attributeName;
  }

  public String getDescription() {
    return this.description;
  }

  public int getIconID() {
    return this.iconID;
  }

  public String getNotes() {
    return this.notes;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public static List<ChrAttribute> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector attributeID,
                                          final AttributeSelector attributeName,
                                          final AttributeSelector description,
                                          final AttributeSelector iconID,
                                          final AttributeSelector notes,
                                          final AttributeSelector shortDescription) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM ChrAttribute c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "attributeID", attributeID);
        AttributeSelector.addStringSelector(qs, "c", "attributeName", attributeName, p);
        AttributeSelector.addStringSelector(qs, "c", "description", description, p);
        AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
        AttributeSelector.addStringSelector(qs, "c", "notes", notes, p);
        AttributeSelector.addStringSelector(qs, "c", "shortDescription", shortDescription, p);
        // Return result
        TypedQuery<ChrAttribute> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), ChrAttribute.class);
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
    return "ChrAttribute [attributeID=" + attributeID + ", attributeName=" + attributeName + ", description=" + description + ", iconID=" + iconID + ", notes="
        + notes + ", shortDescription=" + shortDescription + "]";
  }

}