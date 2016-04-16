package enterprises.orbital.evekit.sde.inv;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the invmarketgroups database table.
 * 
 */
@Entity
@Table(
    name = "invmarketgroups")
@Immutable
public class InvMarketGroup {
  private static final Logger log = Logger.getLogger(InvMarketGroup.class.getName());

  @Id
  private int                 marketGroupID;
  private String              description;
  private byte                hasTypes;
  private Integer             iconID;
  private String              marketGroupName;
  private Integer             parentGroupID;

  public InvMarketGroup() {}

  public int getMarketGroupID() {
    return this.marketGroupID;
  }

  public String getDescription() {
    return this.description;
  }

  public byte getHasTypes() {
    return this.hasTypes;
  }

  public Integer getIconID() {
    return this.iconID;
  }

  public String getMarketGroupName() {
    return this.marketGroupName;
  }

  public Integer getParentGroupID() {
    return this.parentGroupID;
  }

  public static List<InvMarketGroup> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector marketGroupID,
                                            final AttributeSelector description,
                                            final AttributeSelector hasTypes,
                                            final AttributeSelector iconID,
                                            final AttributeSelector marketGroupName,
                                            final AttributeSelector parentGroupID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<InvMarketGroup>>() {
        @Override
        public List<InvMarketGroup> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM InvMarketGroup c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "marketGroupID", marketGroupID);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "hasTypes", hasTypes);
          AttributeSelector.addIntSelector(qs, "c", "iconID", iconID);
          AttributeSelector.addStringSelector(qs, "c", "marketGroupName", marketGroupName, p);
          AttributeSelector.addIntSelector(qs, "c", "parentGroupID", parentGroupID);
          // Return result
          TypedQuery<InvMarketGroup> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), InvMarketGroup.class);
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
    return "InvMarketGroup [marketGroupID=" + marketGroupID + ", description=" + description + ", hasTypes=" + hasTypes + ", iconID=" + iconID
        + ", marketGroupName=" + marketGroupName + ", parentGroupID=" + parentGroupID + "]";
  }

}