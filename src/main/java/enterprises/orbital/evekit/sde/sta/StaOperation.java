package enterprises.orbital.evekit.sde.sta;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the staoperations database table.
 * 
 */
@Entity
@Table(
    name = "staoperations")
public class StaOperation {
  private static final Logger log = Logger.getLogger(StaOperation.class.getName());

  @Id
  private byte                operationID;
  private byte                activityID;
  private Integer             amarrStationTypeID;
  private byte                border;
  private Integer             caldariStationTypeID;
  private byte                corridor;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private byte                fringe;
  private Integer             gallenteStationTypeID;
  private byte                hub;
  private Integer             joveStationTypeID;
  private Integer             minmatarStationTypeID;
  private String              operationName;
  private byte                ratio;

  public StaOperation() {}

  public StaOperation(byte operationID, byte activityID, Integer amarrStationTypeID, byte border, Integer caldariStationTypeID, byte corridor,
                      String description, byte fringe, Integer gallenteStationTypeID, byte hub, Integer joveStationTypeID, Integer minmatarStationTypeID,
                      String operationName, byte ratio) {
    super();
    this.operationID = operationID;
    this.activityID = activityID;
    this.amarrStationTypeID = amarrStationTypeID;
    this.border = border;
    this.caldariStationTypeID = caldariStationTypeID;
    this.corridor = corridor;
    this.description = description;
    this.fringe = fringe;
    this.gallenteStationTypeID = gallenteStationTypeID;
    this.hub = hub;
    this.joveStationTypeID = joveStationTypeID;
    this.minmatarStationTypeID = minmatarStationTypeID;
    this.operationName = operationName;
    this.ratio = ratio;
  }

  public byte getOperationID() {
    return this.operationID;
  }

  public byte getActivityID() {
    return this.activityID;
  }

  public Integer getAmarrStationTypeID() {
    return this.amarrStationTypeID;
  }

  public byte getBorder() {
    return this.border;
  }

  public Integer getCaldariStationTypeID() {
    return this.caldariStationTypeID;
  }

  public byte getCorridor() {
    return this.corridor;
  }

  public String getDescription() {
    return this.description;
  }

  public byte getFringe() {
    return this.fringe;
  }

  public Integer getGallenteStationTypeID() {
    return this.gallenteStationTypeID;
  }

  public byte getHub() {
    return this.hub;
  }

  public Integer getJoveStationTypeID() {
    return this.joveStationTypeID;
  }

  public Integer getMinmatarStationTypeID() {
    return this.minmatarStationTypeID;
  }

  public String getOperationName() {
    return this.operationName;
  }

  public byte getRatio() {
    return this.ratio;
  }

  public static List<StaOperation> access(
                                          final int contid,
                                          final int maxresults,
                                          final AttributeSelector operationID,
                                          final AttributeSelector activityID,
                                          final AttributeSelector amarrStationTypeID,
                                          final AttributeSelector border,
                                          final AttributeSelector caldariStationTypeID,
                                          final AttributeSelector corridor,
                                          final AttributeSelector description,
                                          final AttributeSelector fringe,
                                          final AttributeSelector gallenteStationTypeID,
                                          final AttributeSelector hub,
                                          final AttributeSelector joveStationTypeID,
                                          final AttributeSelector minmatarStationTypeID,
                                          final AttributeSelector operationName,
                                          final AttributeSelector ratio) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<StaOperation>>() {
        @Override
        public List<StaOperation> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM StaOperation c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "operationID", operationID);
          AttributeSelector.addIntSelector(qs, "c", "activityID", activityID);
          AttributeSelector.addIntSelector(qs, "c", "amarrStationTypeID", amarrStationTypeID);
          AttributeSelector.addIntSelector(qs, "c", "border", border);
          AttributeSelector.addIntSelector(qs, "c", "caldariStationTypeID", caldariStationTypeID);
          AttributeSelector.addIntSelector(qs, "c", "corridor", corridor);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "fringe", fringe);
          AttributeSelector.addIntSelector(qs, "c", "gallenteStationTypeID", gallenteStationTypeID);
          AttributeSelector.addIntSelector(qs, "c", "hub", hub);
          AttributeSelector.addIntSelector(qs, "c", "joveStationTypeID", joveStationTypeID);
          AttributeSelector.addIntSelector(qs, "c", "minmatarStationTypeID", minmatarStationTypeID);
          AttributeSelector.addStringSelector(qs, "c", "operationName", operationName, p);
          AttributeSelector.addIntSelector(qs, "c", "ratio", ratio);
          // Return result
          TypedQuery<StaOperation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), StaOperation.class);
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
    return "StaOperation [operationID=" + operationID + ", activityID=" + activityID + ", amarrStationTypeID=" + amarrStationTypeID + ", border=" + border
        + ", caldariStationTypeID=" + caldariStationTypeID + ", corridor=" + corridor + ", description=" + description + ", fringe=" + fringe
        + ", gallenteStationTypeID=" + gallenteStationTypeID + ", hub=" + hub + ", joveStationTypeID=" + joveStationTypeID + ", minmatarStationTypeID="
        + minmatarStationTypeID + ", operationName=" + operationName + ", ratio=" + ratio + "]";
  }

}