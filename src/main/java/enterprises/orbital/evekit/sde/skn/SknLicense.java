package enterprises.orbital.evekit.sde.skn;

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
 * The persistent class for the sknlicenses database table.
 * 
 */
@Entity
@Table(
    name = "skinlicense")
public class SknLicense {
  private static final Logger log = Logger.getLogger(SknLicense.class.getName());

  @Id
  private int                 licenseTypeID;
  private int                 duration;
  private int                 skinID;

  public SknLicense() {}

  public SknLicense(int licenseTypeID, int duration, int skinID) {
    super();
    this.licenseTypeID = licenseTypeID;
    this.duration = duration;
    this.skinID = skinID;
  }

  public int getLicenseTypeID() {
    return this.licenseTypeID;
  }

  public int getDuration() {
    return this.duration;
  }

  public int getSkinID() {
    return this.skinID;
  }

  public static List<SknLicense> access(
                                        final int contid,
                                        final int maxresults,
                                        final AttributeSelector licenseTypeID,
                                        final AttributeSelector duration,
                                        final AttributeSelector skinID) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM SknLicense c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "licenseTypeID", licenseTypeID);
        AttributeSelector.addIntSelector(qs, "c", "duration", duration);
        AttributeSelector.addIntSelector(qs, "c", "skinID", skinID);
        // Return result
        TypedQuery<SknLicense> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), SknLicense.class);
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
    return "SknLicense [licenseTypeID=" + licenseTypeID + ", duration=" + duration + ", skinID=" + skinID + "]";
  }

}