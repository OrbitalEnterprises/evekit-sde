package enterprises.orbital.evekit.sde.skn;

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
 * The persistent class for the sknmaterials database table.
 * 
 */
@Entity
@Table(
    name = "sknmaterials")
@Immutable
public class SknMaterial {
  private static final Logger log = Logger.getLogger(SknMaterial.class.getName());

  @Id
  private int                 skinMaterialID;
  private String              colorHull;
  private String              colorPrimary;
  private String              colorSecondary;
  private String              colorWindow;
  private int                 displayNameID;
  private String              material;
  private int                 materialSetID;

  public SknMaterial() {}

  public int getSkinMaterialID() {
    return this.skinMaterialID;
  }

  public String getColorHull() {
    return this.colorHull;
  }

  public String getColorPrimary() {
    return this.colorPrimary;
  }

  public String getColorSecondary() {
    return this.colorSecondary;
  }

  public String getColorWindow() {
    return this.colorWindow;
  }

  public int getDisplayNameID() {
    return this.displayNameID;
  }

  public String getMaterial() {
    return this.material;
  }

  public int getMaterialSetID() {
    return this.materialSetID;
  }

  public static List<SknMaterial> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector skinMaterialID,
                                         final AttributeSelector colorHull,
                                         final AttributeSelector colorPrimary,
                                         final AttributeSelector colorSecondary,
                                         final AttributeSelector colorWindow,
                                         final AttributeSelector displayNameID,
                                         final AttributeSelector material,
                                         final AttributeSelector materialSetID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<SknMaterial>>() {
        @Override
        public List<SknMaterial> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM SknMaterial c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "skinMaterialID", skinMaterialID);
          AttributeSelector.addStringSelector(qs, "c", "colorHull", colorHull, p);
          AttributeSelector.addStringSelector(qs, "c", "colorPrimary", colorPrimary, p);
          AttributeSelector.addStringSelector(qs, "c", "colorSecondary", colorSecondary, p);
          AttributeSelector.addStringSelector(qs, "c", "colorWindow", colorWindow, p);
          AttributeSelector.addIntSelector(qs, "c", "displayNameID", displayNameID);
          AttributeSelector.addStringSelector(qs, "c", "material", material, p);
          AttributeSelector.addIntSelector(qs, "c", "materialSetID", materialSetID);
          // Return result
          TypedQuery<SknMaterial> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), SknMaterial.class);
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
    return "SknMaterial [skinMaterialID=" + skinMaterialID + ", colorHull=" + colorHull + ", colorPrimary=" + colorPrimary + ", colorSecondary="
        + colorSecondary + ", colorWindow=" + colorWindow + ", displayNameID=" + displayNameID + ", material=" + material + ", materialSetID=" + materialSetID
        + "]";
  }

}