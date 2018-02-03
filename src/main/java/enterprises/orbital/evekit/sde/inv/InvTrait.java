package enterprises.orbital.evekit.sde.inv;

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
 * The persistent class for the invtraits database table.
 * 
 */
@Entity
@Table(
    name = "invtraits")
public class InvTrait {
  private static final Logger log = Logger.getLogger(InvTrait.class.getName());

  @Id
  private int                 traitID;
  private int                 typeID;
  private int                 skillID;
  private Double              bonus;
  private String              bonusText;
  private Integer             unitID;

  public InvTrait() {}

  public InvTrait(int traitID, int typeID, int skillID, Double bonus, String bonusText, Integer unitID) {
    super();
    this.traitID = traitID;
    this.typeID = typeID;
    this.skillID = skillID;
    this.bonus = bonus;
    this.bonusText = bonusText;
    this.unitID = unitID;
  }

  public int getTraitID() {
    return traitID;
  }

  public int getTypeID() {
    return typeID;
  }

  public int getSkillID() {
    return skillID;
  }

  public Double getBonus() {
    return bonus;
  }

  public String getBonusText() {
    return bonusText;
  }

  public Integer getUnitID() {
    return unitID;
  }

  public static List<InvTrait> access(
                                      final int contid,
                                      final int maxresults,
                                      final AttributeSelector traitID,
                                      final AttributeSelector typeID,
                                      final AttributeSelector skillID,
                                      final AttributeSelector bonus,
                                      final AttributeSelector bonusText,
                                      final AttributeSelector unitID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM InvTrait c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "traitID", traitID);
        AttributeSelector.addIntSelector(qs, "c", "typeID", typeID);
        AttributeSelector.addIntSelector(qs, "c", "skillID", skillID);
        AttributeSelector.addDoubleSelector(qs, "c", "bonus", bonus);
        AttributeSelector.addStringSelector(qs, "c", "bonusText", bonusText, p);
        AttributeSelector.addIntSelector(qs, "c", "unitID", unitID);
        // Return result
        TypedQuery<InvTrait> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvTrait.class);
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
    return "InvTrait [traitID=" + traitID + ", typeID=" + typeID + ", skillID=" + skillID + ", bonus=" + bonus + ", bonusText=" + bonusText + ", unitID="
        + unitID + "]";
  }

}