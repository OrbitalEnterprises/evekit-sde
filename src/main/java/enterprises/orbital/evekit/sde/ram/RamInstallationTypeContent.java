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
 * The persistent class for the raminstallationtypecontents database table.
 * 
 */
@Entity
@Table(
    name = "raminstallationtypecontents")
public class RamInstallationTypeContent {
  private static final Logger          log = Logger.getLogger(RamInstallationTypeContent.class.getName());

  @EmbeddedId
  private RamInstallationTypeContentPK id;
  private byte                         quantity;

  public RamInstallationTypeContent() {}

  public RamInstallationTypeContent(int installationTypeID, int assemblyLineTypeID, byte quantity) {
    super();
    this.id = new RamInstallationTypeContentPK(installationTypeID, assemblyLineTypeID);
    this.quantity = quantity;
  }

  public RamInstallationTypeContentPK id() {
    return this.id;
  }

  public int getInstallationTypeID() {
    return id.getInstallationTypeID();
  }

  public int getAssemblyLineTypeID() {
    return id.getAssemblyLineTypeID();
  }

  public byte getQuantity() {
    return this.quantity;
  }

  public static List<RamInstallationTypeContent> access(
                                                        final int contid,
                                                        final int maxresults,
                                                        final AttributeSelector installationTypeID,
                                                        final AttributeSelector assemblyLineTypeID,
                                                        final AttributeSelector quantity) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<RamInstallationTypeContent>>() {
        @Override
        public List<RamInstallationTypeContent> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM RamInstallationTypeContent c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "id.installationTypeID", installationTypeID);
          AttributeSelector.addIntSelector(qs, "c", "id.assemblyLineTypeID", assemblyLineTypeID);
          AttributeSelector.addIntSelector(qs, "c", "quantity", quantity);
          // Return result
          TypedQuery<RamInstallationTypeContent> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), RamInstallationTypeContent.class);
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
    return "RamInstallationTypeContent [id=" + id + ", quantity=" + quantity + "]";
  }

}