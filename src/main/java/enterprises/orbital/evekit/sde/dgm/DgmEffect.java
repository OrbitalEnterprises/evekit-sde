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
 * The persistent class for the dgmeffects database table.
 * 
 */
@Entity
@Table(
    name = "dgmeffects")
public class DgmEffect {
  private static final Logger log = Logger.getLogger(DgmEffect.class.getName());

  @Id
  private int                 effectID;
  private String              effectName;
  private short               effectCategory;
  private int                 preExpression;
  private Integer                 postExpression;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private String              guid;
  private Integer             iconID;
  private boolean                isOffensive;
  private boolean                isAssistance;
  private Integer             durationAttributeID;
  private Integer             trackingSpeedAttributeID;
  private Integer             dischargeAttributeID;
  private Integer             rangeAttributeID;
  private Integer             falloffAttributeID;
  private boolean                disallowAutoRepeat;
  private boolean                published;
  private String              displayName;
  private boolean                isWarpSafe;
  private boolean                rangeChance;
  private boolean                electronicChance;
  private boolean                propulsionChance;
  private Boolean                distribution;
  private String              sfxName;
  private Integer             npcUsageChanceAttributeID;
  private Integer             npcActivationChanceAttributeID;
  private Integer             fittingUsageChanceAttributeID;
  @Lob
  @Column(
      length = 102400)
  private String              modifierInfo;

  public DgmEffect() {}

  public DgmEffect(int effectID, String description, boolean disallowAutoRepeat, Integer dischargeAttributeID, String displayName, Boolean distribution,
                   Integer durationAttributeID, short effectCategory, String effectName, boolean electronicChance, Integer falloffAttributeID,
                   Integer fittingUsageChanceAttributeID, String guid, Integer iconID, boolean isAssistance, boolean isOffensive, boolean isWarpSafe,
                   String modifierInfo, Integer npcActivationChanceAttributeID, Integer npcUsageChanceAttributeID, Integer postExpression, int preExpression,
                   boolean propulsionChance, boolean published, Integer rangeAttributeID, boolean rangeChance, String sfxName, Integer trackingSpeedAttributeID) {
    super();
    this.effectID = effectID;
    this.description = description;
    this.disallowAutoRepeat = disallowAutoRepeat;
    this.dischargeAttributeID = dischargeAttributeID;
    this.displayName = displayName;
    this.distribution = distribution;
    this.durationAttributeID = durationAttributeID;
    this.effectCategory = effectCategory;
    this.effectName = effectName;
    this.electronicChance = electronicChance;
    this.falloffAttributeID = falloffAttributeID;
    this.fittingUsageChanceAttributeID = fittingUsageChanceAttributeID;
    this.guid = guid;
    this.iconID = iconID;
    this.isAssistance = isAssistance;
    this.isOffensive = isOffensive;
    this.isWarpSafe = isWarpSafe;
    this.modifierInfo = modifierInfo;
    this.npcActivationChanceAttributeID = npcActivationChanceAttributeID;
    this.npcUsageChanceAttributeID = npcUsageChanceAttributeID;
    this.postExpression = postExpression;
    this.preExpression = preExpression;
    this.propulsionChance = propulsionChance;
    this.published = published;
    this.rangeAttributeID = rangeAttributeID;
    this.rangeChance = rangeChance;
    this.sfxName = sfxName;
    this.trackingSpeedAttributeID = trackingSpeedAttributeID;
  }

  public int getEffectID() {
    return this.effectID;
  }

  public String getDescription() {
    return this.description;
  }

  public boolean isDisallowAutoRepeat() {
    return this.disallowAutoRepeat;
  }

  public Integer getDischargeAttributeID() {
    return this.dischargeAttributeID;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public Boolean isDistribution() {
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

  public boolean isElectronicChance() {
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

  public boolean isAssistance() {
    return this.isAssistance;
  }

  public boolean isOffensive() {
    return this.isOffensive;
  }

  public boolean isWarpSafe() {
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

  public Integer getPostExpression() {
    return this.postExpression;
  }

  public int getPreExpression() {
    return this.preExpression;
  }

  public boolean isPropulsionChance() {
    return this.propulsionChance;
  }

  public boolean isPublished() {
    return this.published;
  }

  public Integer getRangeAttributeID() {
    return this.rangeAttributeID;
  }

  public boolean isRangeChance() {
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
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM DgmEffect c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "effectID", effectID);
        AttributeSelector.addStringSelector(qs, "c", "description", description, p);
        AttributeSelector.addBooleanSelector(qs, "c", "disallowAutoRepeat", disallowAutoRepeat);
        AttributeSelector.addIntSelector(qs, "c", "dischargeAttributeID", dischargeAttributeID);
        AttributeSelector.addStringSelector(qs, "c", "displayName", displayName, p);
        AttributeSelector.addBooleanSelector(qs, "c", "distribution", distribution);
        AttributeSelector.addIntSelector(qs, "c", "durationAttributeID", durationAttributeID);
        AttributeSelector.addIntSelector(qs, "c", "effectCategory", effectCategory);
        AttributeSelector.addStringSelector(qs, "c", "effectName", effectName, p);
        AttributeSelector.addBooleanSelector(qs, "c", "electronicChance", electronicChance);
        AttributeSelector.addIntSelector(qs, "c", "falloffAttributeID", falloffAttributeID);
        AttributeSelector.addIntSelector(qs, "c", "fittingUsageChanceAttributeID", fittingUsageChanceAttributeID);
        AttributeSelector.addStringSelector(qs, "c", "guid", guid, p);
        AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
        AttributeSelector.addBooleanSelector(qs, "c", "isAssistance", isAssistance);
        AttributeSelector.addBooleanSelector(qs, "c", "isOffensive", isOffensive);
        AttributeSelector.addBooleanSelector(qs, "c", "isWarpSafe", isWarpSafe);
        AttributeSelector.addStringSelector(qs, "c", "modifierInfo", modifierInfo, p);
        AttributeSelector.addIntSelector(qs, "c", "npcActivationChanceAttributeID", npcActivationChanceAttributeID);
        AttributeSelector.addIntSelector(qs, "c", "npcUsageChanceAttributeID", npcUsageChanceAttributeID);
        AttributeSelector.addIntSelector(qs, "c", "postExpression", postExpression);
        AttributeSelector.addIntSelector(qs, "c", "preExpression", preExpression);
        AttributeSelector.addBooleanSelector(qs, "c", "propulsionChance", propulsionChance);
        AttributeSelector.addBooleanSelector(qs, "c", "published", published);
        AttributeSelector.addIntSelector(qs, "c", "rangeAttributeID", rangeAttributeID);
        AttributeSelector.addBooleanSelector(qs, "c", "rangeChance", rangeChance);
        AttributeSelector.addStringSelector(qs, "c", "sfxName", sfxName, p);
        AttributeSelector.addIntSelector(qs, "c", "trackingSpeedAttributeID", trackingSpeedAttributeID);
        // Return result
        TypedQuery<DgmEffect> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmEffect.class);
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