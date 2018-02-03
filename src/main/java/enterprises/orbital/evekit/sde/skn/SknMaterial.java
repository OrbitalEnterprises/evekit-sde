package enterprises.orbital.evekit.sde.skn;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the sknmaterials database table.
 * 
 */
@Entity
@Table(
    name = "skinmaterials")
public class SknMaterial {
  private static final Logger log = Logger.getLogger(SknMaterial.class.getName());

  @Id
  private int                 skinMaterialID;
  private int                 displayNameID;
  private int                 materialSetID;

  public SknMaterial() {}

  public SknMaterial(int skinMaterialID, int displayNameID, int materialSetID) {
    super();
    this.skinMaterialID = skinMaterialID;
    this.displayNameID = displayNameID;
    this.materialSetID = materialSetID;
  }

  public int getSkinMaterialID() {
    return this.skinMaterialID;
  }

  public int getDisplayNameID() {
    return this.displayNameID;
  }

  public int getMaterialSetID() {
    return this.materialSetID;
  }

  public static List<SknMaterial> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector skinMaterialID,
                                         final AttributeSelector displayNameID,
                                         final AttributeSelector materialSetID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM SknMaterial c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "skinMaterialID", skinMaterialID);
        AttributeSelector.addIntSelector(qs, "c", "displayNameID", displayNameID);
        AttributeSelector.addIntSelector(qs, "c", "materialSetID", materialSetID);
        // Return result
        TypedQuery<SknMaterial> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), SknMaterial.class);
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
    return "SknMaterial [skinMaterialID=" + skinMaterialID + ", displayNameID=" + displayNameID + ", materialSetID=" + materialSetID + "]";
  }

}