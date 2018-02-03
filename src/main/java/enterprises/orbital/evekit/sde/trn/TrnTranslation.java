package enterprises.orbital.evekit.sde.trn;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the trntranslations database table.
 * 
 */
@Entity
@Table(
    name = "trntranslations")
public class TrnTranslation {
  private static final Logger log = Logger.getLogger(TrnTranslation.class.getName());

  @EmbeddedId
  private TrnTranslationPK    id;
  @Lob
  @Column(
      length = 102400)
  private String              text;

  public TrnTranslation() {}

  public TrnTranslation(int tcID, int keyID, String languageID, String text) {
    super();
    this.id = new TrnTranslationPK(tcID, keyID, languageID);
    this.text = text;
  }

  public TrnTranslationPK id() {
    return this.id;
  }

  public int getTcID() {
    return id.getTcID();
  }

  public int getKeyID() {
    return id.getKeyID();
  }

  public String getLanguageID() {
    return id.getLanguageID();
  }

  public String getText() {
    return this.text;
  }

  public static List<TrnTranslation> access(
                                            final int contid,
                                            final int maxresults,
                                            final AttributeSelector tcID,
                                            final AttributeSelector keyID,
                                            final AttributeSelector languageID,
                                            final AttributeSelector text) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM TrnTranslation c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "id.tcID", tcID);
        AttributeSelector.addIntSelector(qs, "c", "id.keyID", keyID);
        AttributeSelector.addStringSelector(qs, "c", "id.languageID", languageID, p);
        AttributeSelector.addStringSelector(qs, "c", "text", text, p);
        // Return result
        TypedQuery<TrnTranslation> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), TrnTranslation.class);
        p.fillParams(query);
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
    return "TrnTranslation [id=" + id + ", text=" + text + "]";
  }

}