package enterprises.orbital.evekit.sde.trn;

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
 * The persistent class for the trntranslationcolumns database table.
 * 
 */
@Entity
@Table(
    name = "trntranslationcolumns")
public class TrnTranslationColumn {
  private static final Logger log = Logger.getLogger(TrnTranslationColumn.class.getName());

  @Id
  private int                 tcID;
  private String              columnName;
  private String              masterID;
  private String              tableName;
  private int                 tcGroupID;

  public TrnTranslationColumn() {}

  public TrnTranslationColumn(int tcID, String columnName, String masterID, String tableName, int tcGroupID) {
    super();
    this.tcID = tcID;
    this.columnName = columnName;
    this.masterID = masterID;
    this.tableName = tableName;
    this.tcGroupID = tcGroupID;
  }

  public int getTcID() {
    return this.tcID;
  }

  public String getColumnName() {
    return this.columnName;
  }

  public String getMasterID() {
    return this.masterID;
  }

  public String getTableName() {
    return this.tableName;
  }

  public int getTcGroupID() {
    return this.tcGroupID;
  }

  public static List<TrnTranslationColumn> access(
                                                  final int contid,
                                                  final int maxresults,
                                                  final AttributeSelector tcID,
                                                  final AttributeSelector columnName,
                                                  final AttributeSelector masterID,
                                                  final AttributeSelector tableName,
                                                  final AttributeSelector tcGroupID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<TrnTranslationColumn>>() {
        @Override
        public List<TrnTranslationColumn> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM TrnTranslationColumn c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "tcID", tcID);
          AttributeSelector.addIntSelector(qs, "c", "columnName", columnName);
          AttributeSelector.addIntSelector(qs, "c", "masterID", masterID);
          AttributeSelector.addIntSelector(qs, "c", "tableName", tableName);
          AttributeSelector.addIntSelector(qs, "c", "tcGroupID", tcGroupID);
          // Return result
          TypedQuery<TrnTranslationColumn> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), TrnTranslationColumn.class);
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
    return "TrnTranslationColumn [tcID=" + tcID + ", columnName=" + columnName + ", masterID=" + masterID + ", tableName=" + tableName + ", tcGroupID="
        + tcGroupID + "]";
  }

}