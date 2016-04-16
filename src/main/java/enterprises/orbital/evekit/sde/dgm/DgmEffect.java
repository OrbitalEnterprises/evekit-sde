package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmeffects database table.
 * 
 */
@Entity
@Table(
    name = "dgmeffects")
@Immutable
public class DgmEffect {
  private static final Logger log = Logger.getLogger(DgmEffect.class.getName());

  @Id
  private int                 effectID;
  private String              description;
  private byte                disallowAutoRepeat;
  private Integer             dischargeAttributeID;
  private String              displayName;
  private Byte                distribution;
  private Integer             durationAttributeID;
  private short               effectCategory;
  private String              effectName;
  private byte                electronicChance;
  private Integer             falloffAttributeID;
  private Integer             fittingUsageChanceAttributeID;
  private String              guid;
  private Integer             iconID;
  private byte                isAssistance;
  private byte                isOffensive;
  private byte                isWarpSafe;
  private String              modifierInfo;
  private Integer             npcActivationChanceAttributeID;
  private Integer             npcUsageChanceAttributeID;
  private int                 postExpression;
  private int                 preExpression;
  private byte                propulsionChance;
  private byte                published;
  private Integer             rangeAttributeID;
  private byte                rangeChance;
  private String              sfxName;
  private Integer             trackingSpeedAttributeID;

  public DgmEffect() {}

  public int getEffectID() {
    return this.effectID;
  }

  public String getDescription() {
    return this.description;
  }

  public byte getDisallowAutoRepeat() {
    return this.disallowAutoRepeat;
  }

  public Integer getDischargeAttributeID() {
    return this.dischargeAttributeID;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public Byte getDistribution() {
    return this.distribution;
  }

  public Integer getDurationAttributeID() {
    return this.durationAttributeID;
  }

  public short getEffectCategory() {
    return this.effectCategory;
  }

  public String getEffectName() {
    return this.effectName;
  }

  public byte getElectronicChance() {
    return this.electronicChance;
  }

  public Integer getFalloffAttributeID() {
    return this.falloffAttributeID;
  }

  public Integer getFittingUsageChanceAttributeID() {
    return this.fittingUsageChanceAttributeID;
  }

  public String getGuid() {
    return this.guid;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public byte getIsAssistance() {
    return this.isAssistance;
  }

  public byte getIsOffensive() {
    return this.isOffensive;
  }

  public byte getIsWarpSafe() {
    return this.isWarpSafe;
  }

  public String getModifierInfo() {
    return this.modifierInfo;
  }

  public Integer getNpcActivationChanceAttributeID() {
    return this.npcActivationChanceAttributeID;
  }

  public Integer getNpcUsageChanceAttributeID() {
    return this.npcUsageChanceAttributeID;
  }

  public int getPostExpression() {
    return this.postExpression;
  }

  public int getPreExpression() {
    return this.preExpression;
  }

  public byte getPropulsionChance() {
    return this.propulsionChance;
  }

  public byte getPublished() {
    return this.published;
  }

  public Integer getRangeAttributeID() {
    return this.rangeAttributeID;
  }

  public byte getRangeChance() {
    return this.rangeChance;
  }

  public String getSfxName() {
    return this.sfxName;
  }

  public Integer getTrackingSpeedAttributeID() {
    return this.trackingSpeedAttributeID;
  }

  public static List<DgmEffect> access(
                                       final int contid,
                                       final int maxresults,
                                       final AttributeSelector effectID,
                                       final AttributeSelector description,
                                       final AttributeSelector disallowAutoRepeat,
                                       final AttributeSelector dischargeAttributeID,
                                       final AttributeSelector displayName,
                                       final AttributeSelector distribution,
                                       final AttributeSelector durationAttributeID,
                                       final AttributeSelector effectCategory,
                                       final AttributeSelector effectName,
                                       final AttributeSelector electronicChance,
                                       final AttributeSelector falloffAttributeID,
                                       final AttributeSelector fittingUsageChanceAttributeID,
                                       final AttributeSelector guid,
                                       final AttributeSelector iconID,
                                       final AttributeSelector isAssistance,
                                       final AttributeSelector isOffensive,
                                       final AttributeSelector isWarpSafe,
                                       final AttributeSelector modifierInfo,
                                       final AttributeSelector npcActivationChanceAttributeID,
                                       final AttributeSelector npcUsageChanceAttributeID,
                                       final AttributeSelector postExpression,
                                       final AttributeSelector preExpression,
                                       final AttributeSelector propulsionChance,
                                       final AttributeSelector published,
                                       final AttributeSelector rangeAttributeID,
                                       final AttributeSelector rangeChance,
                                       final AttributeSelector sfxName,
                                       final AttributeSelector trackingSpeedAttributeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmEffect>>() {
        @Override
        public List<DgmEffect> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmEffect c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "effectID", effectID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "disallowAutoRepeat", disallowAutoRepeat);
          AttributeSelector.addIntSelector(qs, "c", "dischargeAttributeID", dischargeAttributeID);
          AttributeSelector.addStringSelector(qs, "c", "displayName", displayName, p);
          AttributeSelector.addIntSelector(qs, "c", "distribution", distribution);
          AttributeSelector.addIntSelector(qs, "c", "durationAttributeID", durationAttributeID);
          AttributeSelector.addIntSelector(qs, "c", "effectCategory", effectCategory);
          AttributeSelector.addStringSelector(qs, "c", "effectName", effectName, p);
          AttributeSelector.addIntSelector(qs, "c", "electronicChance", electronicChance);
          AttributeSelector.addIntSelector(qs, "c", "falloffAttributeID", falloffAttributeID);
          AttributeSelector.addIntSelector(qs, "c", "fittingUsageChanceAttributeID", fittingUsageChanceAttributeID);
          AttributeSelector.addStringSelector(qs, "c", "guid", guid, p);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addIntSelector(qs, "c", "isAssistance", isAssistance);
          AttributeSelector.addIntSelector(qs, "c", "isOffensive", isOffensive);
          AttributeSelector.addIntSelector(qs, "c", "isWarpSafe", isWarpSafe);
          AttributeSelector.addStringSelector(qs, "c", "modifierInfo", modifierInfo, p);
          AttributeSelector.addIntSelector(qs, "c", "npcActivationChanceAttributeID", npcActivationChanceAttributeID);
          AttributeSelector.addIntSelector(qs, "c", "npcUsageChanceAttributeID", npcUsageChanceAttributeID);
          AttributeSelector.addIntSelector(qs, "c", "postExpression", postExpression);
          AttributeSelector.addIntSelector(qs, "c", "preExpression", preExpression);
          AttributeSelector.addIntSelector(qs, "c", "propulsionChance", propulsionChance);
          AttributeSelector.addIntSelector(qs, "c", "published", published);
          AttributeSelector.addIntSelector(qs, "c", "rangeAttributeID", rangeAttributeID);
          AttributeSelector.addIntSelector(qs, "c", "rangeChance", rangeChance);
          AttributeSelector.addStringSelector(qs, "c", "sfxName", sfxName, p);
          AttributeSelector.addIntSelector(qs, "c", "trackingSpeedAttributeID", trackingSpeedAttributeID);
          // Return result
          TypedQuery<DgmEffect> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmEffect.class);
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
    return "DgmEffect [effectID=" + effectID + ", description=" + description + ", disallowAutoRepeat=" + disallowAutoRepeat + ", dischargeAttributeID="
        + dischargeAttributeID + ", displayName=" + displayName + ", distribution=" + distribution + ", durationAttributeID=" + durationAttributeID
        + ", effectCategory=" + effectCategory + ", effectName=" + effectName + ", electronicChance=" + electronicChance + ", falloffAttributeID="
        + falloffAttributeID + ", fittingUsageChanceAttributeID=" + fittingUsageChanceAttributeID + ", guid=" + guid + ", iconID=" + iconID + ", isAssistance="
        + isAssistance + ", isOffensive=" + isOffensive + ", isWarpSafe=" + isWarpSafe + ", modifierInfo=" + modifierInfo + ", npcActivationChanceAttributeID="
        + npcActivationChanceAttributeID + ", npcUsageChanceAttributeID=" + npcUsageChanceAttributeID + ", postExpression=" + postExpression
        + ", preExpression=" + preExpression + ", propulsionChance=" + propulsionChance + ", published=" + published + ", rangeAttributeID=" + rangeAttributeID
        + ", rangeChance=" + rangeChance + ", sfxName=" + sfxName + ", trackingSpeedAttributeID=" + trackingSpeedAttributeID + "]";
  }

}