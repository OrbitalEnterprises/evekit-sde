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
 * The persistent class for the chrattributes database table.
 * 
 */
@Entity
@Table(
    name = "chrattributes")
@Immutable
public class ChrAttribute {
  public static final Logger log = Logger.getLogger(ChrAttribute.class.getName());

  @Id
  private byte               attributeID;
  private String             attributeName;
  private String             description;
  private int                iconID;
  private String             notes;
  private String             shortDescription;

  public ChrAttribute() {}

  public byte getAttributeID() {
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
      return SDE.getFactory().runTransaction(new RunInTransaction<List<ChrAttribute>>() {
        @Override
        public List<ChrAttribute> run() throws Exception {
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
        }
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