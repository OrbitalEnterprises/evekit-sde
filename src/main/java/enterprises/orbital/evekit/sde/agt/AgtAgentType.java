package enterprises.orbital.evekit.sde.agt;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the agtagenttypes database table.
 * 
 */
@Entity
@Table(
    name = "agtagenttypes")
public class AgtAgentType {
  public static final Logger log = Logger.getLogger(AgtAgentType.class.getName());

  @Id
  private int                agentTypeID;
  private String             agentType;

  public AgtAgentType() {}

  public AgtAgentType(int agentTypeID, String agentType) {
    super();
    this.agentTypeID = agentTypeID;
    this.agentType = agentType;
  }

  public int getAgentTypeID() {
    return this.agentTypeID;
  }

  public String getAgentType() {
    return this.agentType;
  }

  public static List<AgtAgentType> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector agentTypeID,
                                          final AttributeSelector agentType) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<AgtAgentType>>() {
        @Override
        public List<AgtAgentType> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          qs.append("SELECT c FROM AgtAgentType c WHERE 1 = 1");
          // Constrain attributes
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "agentTypeID", agentTypeID);
          AttributeSelector.addStringSelector(qs, "c", "agentType", agentType, p);
          // Return result
          TypedQuery<AgtAgentType> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), AgtAgentType.class);
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
    return "AgtAgentType [agentTypeID=" + agentTypeID + ", agentType=" + agentType + "]";
  }

}