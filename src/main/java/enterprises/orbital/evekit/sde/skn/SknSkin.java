package enterprises.orbital.evekit.sde.skn;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the sknskins database table.
 * 
 */
@Entity
@Table(
    name = "sknskins")
public class SknSkin {
  private static final Logger log = Logger.getLogger(SknSkin.class.getName());

  @Id
  private int                 skinID;
  private byte                allowCCPDevs;
  private String              internalName;
  private int                 skinMaterialID;
  private Integer             typeID;
  private byte                visibleSerenity;
  private byte                visibleTranquility;

  public SknSkin() {}

  public SknSkin(int skinID, byte allowCCPDevs, String internalName, int skinMaterialID, Integer typeID, byte visibleSerenity, byte visibleTranquility) {
    super();
    this.skinID = skinID;
    this.allowCCPDevs = allowCCPDevs;
    this.internalName = internalName;
    this.skinMaterialID = skinMaterialID;
    this.typeID = typeID;
    this.visibleSerenity = visibleSerenity;
    this.visibleTranquility = visibleTranquility;
  }

  public int getSkinID() {
    return this.skinID;
  }

  public byte getAllowCCPDevs() {
    return this.allowCCPDevs;
  }

  public String getInternalName() {
    return this.internalName;
  }

  public int getSkinMaterialID() {
    return this.skinMaterialID;
  }

  public Integer getTypeID() {
    return this.typeID;
  }

  public byte getVisibleSerenity() {
    return this.visibleSerenity;
  }

  public byte getVisibleTranquility() {
    return this.visibleTranquility;
  }

  public static List<SknSkin> access(
                                     final int contid,
                                     final int maxresults,
                                     final AttributeSelector skinID,
                                     final AttributeSelector allowCCPDevs,
                                     final AttributeSelector internalName,
                                     final AttributeSelector skinMaterialID,
                                     final AttributeSelector typeID,
                                     final AttributeSelector visibleSerenity,
                                     final AttributeSelector visibleTranquility) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<SknSkin>>() {
        @Override
        public List<SknSkin> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM SknSkin c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "skinID", skinID);
          AttributeSelector.addIntSelector(qs, "c", "allowCCPDevs", allowCCPDevs);
          AttributeSelector.addStringSelector(qs, "c", "internalName", internalName, p);
          AttributeSelector.addIntSelector(qs, "c", "skinMaterialID", skinMaterialID);
          AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "visibleSerenity", visibleSerenity);
          AttributeSelector.addIntSelector(qs, "c", "visibleTranquility", visibleTranquility);
          // Return result
          TypedQuery<SknSkin> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), SknSkin.class);
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
    return "SknSkin [skinID=" + skinID + ", allowCCPDevs=" + allowCCPDevs + ", internalName=" + internalName + ", skinMaterialID=" + skinMaterialID
        + ", typeID=" + typeID + ", visibleSerenity=" + visibleSerenity + ", visibleTranquility=" + visibleTranquility + "]";
  }

}