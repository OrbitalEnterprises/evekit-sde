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
 * The persistent class for the sknskins database table.
 * 
 */
@Entity
@Table(
    name = "skins")
public class SknSkin {
  private static final Logger log = Logger.getLogger(SknSkin.class.getName());

  @Id
  private int                 skinID;
  private String              internalName;
  private int                 skinMaterialID;

  public SknSkin() {}

  public SknSkin(int skinID, String internalName, int skinMaterialID) {
    super();
    this.skinID = skinID;
    this.internalName = internalName;
    this.skinMaterialID = skinMaterialID;
  }

  public int getSkinID() {
    return this.skinID;
  }

  public String getInternalName() {
    return this.internalName;
  }

  public int getSkinMaterialID() {
    return this.skinMaterialID;
  }

  public static List<SknSkin> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector skinID,
                                     final AttributeSelector internalName,
                                     final AttributeSelector skinMaterialID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM SknSkin c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "skinID", skinID);
        AttributeSelector.addStringSelector(qs, "c", "internalName", internalName, p);
        AttributeSelector.addIntSelector(qs, "c", "skinMaterialID", skinMaterialID);
        // Return result
        TypedQuery<SknSkin> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), SknSkin.class);
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
    return "SknSkin [skinID=" + skinID + ", internalName=" + internalName + ", skinMaterialID=" + skinMaterialID + "]";
  }

}