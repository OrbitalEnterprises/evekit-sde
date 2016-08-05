package enterprises.orbital.evekit.sde.agt;

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
 * The persistent class for the agtresearchagents database table.
 * 
 */
@Entity
@Table(
    name = "agtresearchagents")
public class AgtResearchAgent {
  public static final Logger log = Logger.getLogger(AgtResearchAgent.class.getName());

  @EmbeddedId
  private AgtResearchAgentPK id;

  public AgtResearchAgent() {}

  public AgtResearchAgent(int agentID, int typeID) {
    super();
    this.id = new AgtResearchAgentPK(agentID, typeID);
  }

  public AgtResearchAgentPK id() {
    return this.id;
  }

  public int getAgentID() {
    return id.getAgentID();
  }

  public int getTypeID() {
    return id.getTypeID();
  }

  public static List<AgtResearchAgent> access(
                                              final int contid,
                                              final int maxresults,
                                              final AttributeSelector agentID,
                                              final AttributeSelector typeID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<AgtResearchAgent>>() {
        @Override
        public List<AgtResearchAgent> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          qs.append("SELECT c FROM AgtResearchAgent c WHERE 1 = 1");
          // Constrain attributes
          AttributeSelector.addIntSelector(qs, "c", "id.agentID", agentID);
          AttributeSelector.addIntSelector(qs, "c", "id.typeID", typeID);
          // Return result
          TypedQuery<AgtResearchAgent> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), AgtResearchAgent.class);
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
    return "AgtResearchAgent [id=" + id + "]";
  }

}