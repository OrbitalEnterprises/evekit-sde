package enterprises.orbital.evekit.sde.trntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.trn.TrnTranslationLanguage;

public class TestTrnTranslationLanguage extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslationLanguage> next = TrnTranslationLanguage.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = TrnTranslationLanguage.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(8, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslationLanguage> next = TrnTranslationLanguage.access(0, 1000, new AttributeSelector("{values:[1050]}"), all, all);
    Assert.assertEquals(1, next.size());
    TrnTranslationLanguage random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1050, random.getNumericLanguageID());
    Assert.assertEquals("de", random.getLanguageID());
    Assert.assertEquals("GERMAN", random.getLanguageName());
    Assert.assertNotNull(random.toString());
  }

}
