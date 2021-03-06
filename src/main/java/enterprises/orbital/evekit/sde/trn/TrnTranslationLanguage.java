package enterprises.orbital.evekit.sde.trn;

import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The persistent class for the trntranslationlanguages database table.
 * 
 */
@Entity
@Table(
    name = "trntranslationlanguages")
public class TrnTranslationLanguage {
  private static final Logger log = Logger.getLogger(TrnTranslationLanguage.class.getName());

  @Id
  private int                 numericLanguageID;
  private String              languageID;
  private String              languageName;

  public TrnTranslationLanguage() {}

  public TrnTranslationLanguage(int numericLanguageID, String languageID, String languageName) {
    super();
    this.numericLanguageID = numericLanguageID;
    this.languageID = languageID;
    this.languageName = languageName;
  }

  public int getNumericLanguageID() {
    return this.numericLanguageID;
  }

  public String getLanguageID() {
    return this.languageID;
  }

  public String getLanguageName() {
    return this.languageName;
  }

  public static List<TrnTranslationLanguage> access(
                                                    final int contid,
                                                    final int maxresults,
                                                    final AttributeSelector numericLanguageID,
                                                    final AttributeSelector languageID,
                                                    final AttributeSelector languageName) {
    try {
      return SDE.getFactory().runTransaction(() -> {
        int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
        int offset = Math.max(0, contid);
        StringBuilder qs = new StringBuilder();
        // Constrain attributes
        qs.append("SELECT c FROM TrnTranslationLanguage c WHERE 1 = 1");
        AttributeParameters p = new AttributeParameters("att");
        AttributeSelector.addIntSelector(qs, "c", "numericLanguageID", numericLanguageID);
        AttributeSelector.addStringSelector(qs, "c", "languageID", languageID, p);
        AttributeSelector.addStringSelector(qs, "c", "languageName", languageName, p);
        // Return result
        TypedQuery<TrnTranslationLanguage> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), TrnTranslationLanguage.class);
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
    return "TrnTranslationLanguage [numericLanguageID=" + numericLanguageID + ", languageID=" + languageID + ", languageName=" + languageName + "]";
  }

}