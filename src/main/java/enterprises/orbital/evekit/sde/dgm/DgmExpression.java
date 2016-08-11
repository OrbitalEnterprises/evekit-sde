package enterprises.orbital.evekit.sde.dgm;

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
 * The persistent class for the dgmexpressions database table.
 * 
 */
@Entity
@Table(
    name = "dgmexpressions")
public class DgmExpression {
  private static final Logger log = Logger.getLogger(DgmExpression.class.getName());

  @Id
  private int                 expressionID;
  private int                 operandID;
  private Integer             arg1;
  private Integer             arg2;
  private String              expressionValue;
  @Lob
  @Column(
      length = 102400)
  private String              description;
  private String              expressionName;
  private Integer             expressionTypeID;
  private Integer             expressionGroupID;
  private Integer             expressionAttributeID;

  public DgmExpression() {}

  public DgmExpression(int expressionID, Integer arg1, Integer arg2, String description, Integer expressionAttributeID, Integer expressionGroupID,
                       String expressionName, Integer expressionTypeID, String expressionValue, int operandID) {
    super();
    this.expressionID = expressionID;
    this.arg1 = arg1;
    this.arg2 = arg2;
    this.description = description;
    this.expressionAttributeID = expressionAttributeID;
    this.expressionGroupID = expressionGroupID;
    this.expressionName = expressionName;
    this.expressionTypeID = expressionTypeID;
    this.expressionValue = expressionValue;
    this.operandID = operandID;
  }

  public int getExpressionID() {
    return this.expressionID;
  }

  public Integer getArg1() {
    return this.arg1;
  }

  public Integer getArg2() {
    return this.arg2;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getExpressionAttributeID() {
    return this.expressionAttributeID;
  }

  public Integer getExpressionGroupID() {
    return this.expressionGroupID;
  }

  public String getExpressionName() {
    return this.expressionName;
  }

  public Integer getExpressionTypeID() {
    return this.expressionTypeID;
  }

  public String getExpressionValue() {
    return this.expressionValue;
  }

  public int getOperandID() {
    return this.operandID;
  }

  public static List<DgmExpression> access(
                                           final int contid,
                                           final int maxresults,
                                           final AttributeSelector expressionID,
                                           final AttributeSelector arg1,
                                           final AttributeSelector arg2,
                                           final AttributeSelector description,
                                           final AttributeSelector expressionAttributeID,
                                           final AttributeSelector expressionGroupID,
                                           final AttributeSelector expressionName,
                                           final AttributeSelector expressionTypeID,
                                           final AttributeSelector expressionValue,
                                           final AttributeSelector operandID) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<DgmExpression>>() {
        @Override
        public List<DgmExpression> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM DgmExpression c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "expressionID", expressionID);
          AttributeSelector.addIntSelector(qs, "c", "arg1", arg1);
          AttributeSelector.addIntSelector(qs, "c", "arg2", arg2);
          AttributeSelector.addStringSelector(qs, "c", "description", description, p);
          AttributeSelector.addIntSelector(qs, "c", "expressionAttributeID", expressionAttributeID);
          AttributeSelector.addIntSelector(qs, "c", "expressionGroupID", expressionGroupID);
          AttributeSelector.addStringSelector(qs, "c", "expressionName", expressionName, p);
          AttributeSelector.addIntSelector(qs, "c", "expressionTypeID", expressionTypeID);
          AttributeSelector.addStringSelector(qs, "c", "expressionValue", expressionValue, p);
          AttributeSelector.addIntSelector(qs, "c", "operandID", operandID);
          // Return result
          TypedQuery<DgmExpression> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), DgmExpression.class);
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
    return "DgmExpression [expressionID=" + expressionID + ", arg1=" + arg1 + ", arg2=" + arg2 + ", description=" + description + ", expressionAttributeID="
        + expressionAttributeID + ", expressionGroupID=" + expressionGroupID + ", expressionName=" + expressionName + ", expressionTypeID=" + expressionTypeID
        + ", expressionValue=" + expressionValue + ", operandID=" + operandID + "]";
  }

}