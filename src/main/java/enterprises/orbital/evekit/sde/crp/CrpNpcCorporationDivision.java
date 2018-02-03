package enterprises.orbital.evekit.sde.crp;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the crpnpccorporationdivisions database table.
 * 
 */
@Entity
@Table(
    name = "crpnpccorporationdivisions")
public class CrpNpcCorporationDivision {
  private static final Logger         log = Logger.getLogger(CrpNpcCorporationDivision.class.getName());

  @EmbeddedId
  private CrpNpcCorporationDivisionPK id;
  private int                        size;

  public CrpNpcCorporationDivision() {}

  public CrpNpcCorporationDivision(int corporationID, int divisionID, int size) {
    super();
    this.id = new CrpNpcCorporationDivisionPK(corporationID, divisionID);
    this.size = size;
  }

  public CrpNpcCorporationDivisionPK id() {
    return this.id;
  }

  public int getCorporationID() {
    return id.getCorporationID();
  }

  public int getDivisionID() {
    return id.getDivisionID();
  }

  public int getSize() {
    return this.size;
  }

  public static List<CrpNpcCorporationDivision> access(
                                                       final int contid,
                                                       final int maxresults,
                                                       final AttributeSelector corporationID,
                                                       final AttributeSelector divisionID,
                                                       final AttributeSelector size) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM CrpNpcCorporationDivision c WHERE 1 = 1");
        AttributeSelector.addIntSelector(qs, "c", "id.corporationID", corporationID);
        AttributeSelector.addIntSelector(qs, "c", "id.divisionID", divisionID);
        AttributeSelector.addIntSelector(qs, "c", "size", size);
        // Return result
        TypedQuery<CrpNpcCorporationDivision> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrpNpcCorporationDivision.class);
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
    return "CrpNpcCorporationDivision [id=" + id + ", size=" + size + "]";
  }

}