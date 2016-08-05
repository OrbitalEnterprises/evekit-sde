package enterprises.orbital.evekit.sde.inv;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invpositions database table.
 * 
 */
@Entity
@Table(
    name = "invpositions")
public class InvPosition {
  private static final Logger log = Logger.getLogger(InvPosition.class.getName());

  @Id
  private long                itemID;
  private Double              pitch;
  private Double              roll;
  private double              x;
  private double              y;
  private Double              yaw;
  private double              z;

  public InvPosition() {}

  public InvPosition(long itemID, Double pitch, Double roll, double x, double y, Double yaw, double z) {
    super();
    this.itemID = itemID;
    this.pitch = pitch;
    this.roll = roll;
    this.x = x;
    this.y = y;
    this.yaw = yaw;
    this.z = z;
  }

  public long getItemID() {
    return this.itemID;
  }

  public Double getPitch() {
    return this.pitch;
  }

  public Double getRoll() {
    return this.roll;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public Double getYaw() {
    return this.yaw;
  }

  public double getZ() {
    return this.z;
  }

  public static List<InvPosition> access(
                                         final int contid,
                                         final int maxresults,
                                         final AttributeSelector itemID,
                                         final AttributeSelector pitch,
                                         final AttributeSelector roll,
                                         final AttributeSelector x,
                                         final AttributeSelector y,
                                         final AttributeSelector yaw,
                                         final AttributeSelector z) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvPosition>>() {
        @Override
        public List<InvPosition> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvPosition c WHERE 1 = 1");
          AttributeSelector.addLongSelector(qs, "c", "itemID", itemID);
          AttributeSelector.addDoubleSelector(qs, "c", "pitch", pitch);
          AttributeSelector.addDoubleSelector(qs, "c", "roll", roll);
          AttributeSelector.addDoubleSelector(qs, "c", "x", x);
          AttributeSelector.addDoubleSelector(qs, "c", "y", y);
          AttributeSelector.addDoubleSelector(qs, "c", "yaw", yaw);
          AttributeSelector.addDoubleSelector(qs, "c", "z", z);
          // Return result
          TypedQuery<InvPosition> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvPosition.class);
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
    return "InvPosition [itemID=" + itemID + ", pitch=" + pitch + ", roll=" + roll + ", x=" + x + ", y=" + y + ", yaw=" + yaw + ", z=" + z + "]";
  }

}