package enterprises.orbital.evekit.sde.ram;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the ramtyperequirements database table.
 * 
 */
@Entity
@Table(
    name = "ramtyperequirements")
public class RamTypeRequirement {
  private static final Logger  log = Logger.getLogger(RamTypeRequirement.class.getName());

  @EmbeddedId
  private RamTypeRequirementPK id;
  private Byte                 consume;
  private Double               damagePerJob;
  private Integer              level;
  private Double               probability;
  private Integer              quantity;
  private Integer              raceID;
  private Byte                 recycle;

  public RamTypeRequirement() {}

  public RamTypeRequirement(int typeID, byte activityID, int requiredTypeID, Byte consume, Double damagePerJob, Integer level, Double probability,
                            Integer quantity, Integer raceID, Byte recycle) {
    super();
    this.id = new RamTypeRequirementPK(typeID, activityID, requiredTypeID);
    this.consume = consume;
    this.damagePerJob = damagePerJob;
    this.level = level;
    this.probability = probability;
    this.quantity = quantity;
    this.raceID = raceID;
    this.recycle = recycle;
  }

  public RamTypeRequirementPK id() {
    return this.id;
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public byte getActivityID() {
    return id.getActivityID();
  }

  public int getRequiredTypeID() {
    return id.getRequiredTypeID();
  }

  public Byte getConsume() {
    return this.consume;
  }

  public Double getDamagePerJob() {
    return this.damagePerJob;
  }

  public Integer getLevel() {
    return this.level;
  }

  public Double getProbability() {
    return this.probability;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public Integer getRaceID() {
    return this.raceID;
  }

  public Byte getRecycle() {
    return this.recycle;
  }

  public static List<RamTypeRequirement> access(
                                                final int contid,
                                                final int maxresults,
                                                final AttributeSelector typeID,
                                                final AttributeSelector activityID,
                                                final AttributeSelector requiredTypeID,
                                                final AttributeSelector consume,
                                                final AttributeSelector damagePerJob,
                                                final AttributeSelector level,
                                                final AttributeSelector probability,
                                                final AttributeSelector quantity,
                                                final AttributeSelector raceID,
                                                final AttributeSelector recycle) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<RamTypeRequirement>>() {
        @Override
        public List<RamTypeRequirement> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM RamTypeRequirement c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addIntSelector(qs, "c", "id.activityID", activityID);
          AttributeSelector.addIntSelector(qs, "c", "id.requiredTypeID", requiredTypeID);
          AttributeSelector.addIntSelector(qs, "c", "consume", consume);
          AttributeSelector.addDoubleSelector(qs, "c", "damagePerJob", damagePerJob);
          AttributeSelector.addIntSelector(qs, "c", "level", level);
          AttributeSelector.addDoubleSelector(qs, "c", "probability", probability);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          AttributeSelector.addIntSelector(qs, "c", "raceID", raceID);
          AttributeSelector.addIntSelector(qs, "c", "recycle", recycle);
          // Return result
          TypedQuery<RamTypeRequirement> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), RamTypeRequirement.class);
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
    return "RamTypeRequirement [id=" + id + ", consume=" + consume + ", damagePerJob=" + damagePerJob + ", level=" + level + ", probability=" + probability
        + ", quantity=" + quantity + ", raceID=" + raceID + ", recycle=" + recycle + "]";
  }

}