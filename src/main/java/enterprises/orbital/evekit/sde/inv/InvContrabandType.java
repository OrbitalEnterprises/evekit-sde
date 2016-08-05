package enterprises.orbital.evekit.sde.inv;

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
 * The persistent class for the invcontrabandtypes database table.
 * 
 */
@Entity
@Table(
    name = "invcontrabandtypes")
public class InvContrabandType {
  private static final Logger log = Logger.getLogger(InvContrabandType.class.getName());

  @EmbeddedId
  private InvContrabandTypePK id;
  private double              attackMinSec;
  private double              confiscateMinSec;
  private double              fineByValue;
  private double              standingLoss;

  public InvContrabandType() {}

  public InvContrabandType(int factionID, int typeID, double attackMinSec, double confiscateMinSec, double fineByValue, double standingLoss) {
    super();
    this.id = new InvContrabandTypePK(factionID, typeID);
    this.attackMinSec = attackMinSec;
    this.confiscateMinSec = confiscateMinSec;
    this.fineByValue = fineByValue;
    this.standingLoss = standingLoss;
  }

  public InvContrabandTypePK id() {
    return this.id;
  }

  public int getFactionID() {
    return id.getFactionID();
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public double getAttackMinSec() {
    return this.attackMinSec;
  }

  public double getConfiscateMinSec() {
    return this.confiscateMinSec;
  }

  public double getFineByValue() {
    return this.fineByValue;
  }

  public double getStandingLoss() {
    return this.standingLoss;
  }

  public static List<InvContrabandType> access(
                                               final int contid,
                                               final int maxresults,
                                               final AttributeSelector factionID,
                                               final AttributeSelector typeID,
                                               final AttributeSelector attackMinSec,
                                               final AttributeSelector confiscateMinSec,
                                               final AttributeSelector fineByValue,
                                               final AttributeSelector standingLoss) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvContrabandType>>() {
        @Override
        public List<InvContrabandType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvContrabandType c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.factionID", factionID);
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          AttributeSelector.addDoubleSelector(qs, "c", "attackMinSec", attackMinSec);
          AttributeSelector.addDoubleSelector(qs, "c", "confiscateMinSec", confiscateMinSec);
          AttributeSelector.addDoubleSelector(qs, "c", "fineByValue", fineByValue);
          AttributeSelector.addDoubleSelector(qs, "c", "standingLoss", standingLoss);
          // Return result
          TypedQuery<InvContrabandType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvContrabandType.class);
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
    return "InvContrabandType [id=" + id + ", attackMinSec=" + attackMinSec + ", confiscateMinSec=" + confiscateMinSec + ", fineByValue=" + fineByValue
        + ", standingLoss=" + standingLoss + "]";
  }

}