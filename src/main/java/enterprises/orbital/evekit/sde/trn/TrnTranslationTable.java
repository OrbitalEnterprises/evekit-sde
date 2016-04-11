package enterprises.orbital.evekit.sde.trn;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the translationtables database table.
 * 
 */
@Entity
@Table(
    name = "translationtables")
@Immutable
public class TrnTranslationTable {
  private static final Logger   log = Logger.getLogger(TrnTranslationTable.class.getName());

  @EmbeddedId
  private TrnTranslationTablePK id;
  private String                destinationTable;
  private int                   tcGroupID;
  private int                   tcID;

  public TrnTranslationTable() {}

  public TrnTranslationTablePK getId() {
    return this.id;
  }

  public String getDestinationTable() {
    return this.destinationTable;
  }

  public int getTcGroupID() {
    return this.tcGroupID;
  }

  public int getTcID() {
    return this.tcID;
  }

  public static List<TrnTranslationTable> access(
                                                 final int contid,
                                                 final int maxresults,
                                                 final AttributeSelector sourceTable,
                                                 final AttributeSelector translatedKey,
                                                 final AttributeSelector destinationTable,
                                                 final AttributeSelector tcGroupID,
                                                 final AttributeSelector tcID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<TrnTranslationTable>>() {
        @Override
        public List<TrnTranslationTable> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM TrnTranslationTable c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addStringSelector(qs, "c", "id.sourceTable", sourceTable, p);
          AttributeSelector.addStringSelector(qs, "c", "id.translatedKey", translatedKey, p);
          AttributeSelector.addStringSelector(qs, "c", "destinationTable", destinationTable, p);
          AttributeSelector.addIntSelector(qs, "c", "tcGroupID", tcGroupID);
          AttributeSelector.addIntSelector(qs, "c", "tcID", tcID);
          // Return result
          TypedQuery<TrnTranslationTable> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), TrnTranslationTable.class);
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
    return "TranslationTable [id=" + id + ", destinationTable=" + destinationTable + ", tcGroupID=" + tcGroupID + ", tcID=" + tcID + "]";
  }

}