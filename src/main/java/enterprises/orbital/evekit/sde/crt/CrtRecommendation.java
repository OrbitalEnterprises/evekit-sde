package enterprises.orbital.evekit.sde.crt;

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
 * The persistent class for the crtrecommendations database table.
 * 
 */
@Entity
@Table(
    name = "crtrecommendations")
public class CrtRecommendation {
  private static final Logger log = Logger.getLogger(CrtRecommendation.class.getName());

  @Id
  private int                 recommendationID;
  private int                 certificateID;
  private byte                recommendationLevel;
  private int                 shipTypeID;

  public CrtRecommendation() {}

  public CrtRecommendation(int recommendationID, int certificateID, byte recommendationLevel, int shipTypeID) {
    super();
    this.recommendationID = recommendationID;
    this.certificateID = certificateID;
    this.recommendationLevel = recommendationLevel;
    this.shipTypeID = shipTypeID;
  }

  public int getRecommendationID() {
    return this.recommendationID;
  }

  public int getCertificateID() {
    return this.certificateID;
  }

  public byte getRecommendationLevel() {
    return this.recommendationLevel;
  }

  public int getShipTypeID() {
    return this.shipTypeID;
  }

  public static List<CrtRecommendation> access(
                                               final int contid,
                                               final int maxresults,
                                               final AttributeSelector recommendationID,
                                               final AttributeSelector certificateID,
                                               final AttributeSelector recommendationLevel,
                                               final AttributeSelector shipTypeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<CrtRecommendation>>() {
        @Override
        public List<CrtRecommendation> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM CrtRecommendation c WHERE 1 = 1");
          AttributeSelector.addIntSelector(qs, "c", "recommendationID", recommendationID);
          AttributeSelector.addIntSelector(qs, "c", "certificateID", certificateID);
          AttributeSelector.addIntSelector(qs, "c", "recommendationLevel", recommendationLevel);
          AttributeSelector.addIntSelector(qs, "c", "shipTypeID", shipTypeID);
          // Return result
          TypedQuery<CrtRecommendation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), CrtRecommendation.class);
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
    return "CrtRecommendation [recommendationID=" + recommendationID + ", certificateID=" + certificateID + ", recommendationLevel=" + recommendationLevel
        + ", shipTypeID=" + shipTypeID + "]";
  }

}