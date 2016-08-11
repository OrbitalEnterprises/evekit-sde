package enterprises.orbital.evekit.sde.crt;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the certmasteries database table.
 * 
 */
@Entity
@Table(
    name = "certskills")
public class CrtSkill {
  private static final Logger log = Logger.getLogger(CrtSkill.class.getName());

  @EmbeddedId
  private CrtSkillPK          id;
  private String              certLevelText;

  public CrtSkill() {}

  public CrtSkill(int certID, int skillID, int certLevelInt, int skillLevel, String certLevelText) {
    super();
    this.id = new CrtSkillPK(certID, skillID, certLevelInt, skillLevel);
    this.certLevelText = certLevelText;
  }

  public int getCertID() {
    return id.getCertID();
  }

  public int getSkillID() {
    return id.getSkillID();
  }

  public int getCertLevelInt() {
    return id.getCertLevelInt();
  }

  public int getSkillLevel() {
    return id.getSkillLevel();
  }

  public String getCertLevelText() {
    return certLevelText;
  }

  public static List<CrtSkill> access(
                                      final int contid,
                                      final int maxresults,
                                      final AttributeSelector certID,
                                      final AttributeSelector skillID,
                                      final AttributeSelector certLevelInt,
                                      final AttributeSelector skillLevel,
                                      final AttributeSelector certLevelText) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrtSkill>>() {
        @Override
        public List<CrtSkill> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrtSkill c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "id.certID", certID);
          AttributeSelector.addIntSelector(qs, "c", "id.skillID", skillID);
          AttributeSelector.addIntSelector(qs, "c", "id.certLevelInt", certLevelInt);
          AttributeSelector.addIntSelector(qs, "c", "id.skillLevel", skillLevel);
          AttributeSelector.addStringSelector(qs, "c", "certLevelText", certLevelText, p);
          // Return result
          TypedQuery<CrtSkill> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtSkill.class);
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
    return "CrtMastery [id=" + id + "]";
  }

}