package enterprises.orbital.evekit.sde.trntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.trn.TrnTranslation;

public class TestTrnTranslation extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslation> next = TrnTranslation.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = TrnTranslation.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(415310, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslation> next = TrnTranslation.access(0, 1000, new AttributeSelector("{values:[9]}"), new AttributeSelector("{values:[1]}"),
                                                      new AttributeSelector("{values:['FR']}"), all);
    Assert.assertEquals(1, next.size());
    TrnTranslation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.id().getTcID());
    Assert.assertEquals(1, random.id().getKeyID());
    Assert.assertEquals("FR", random.id().getLanguageID());
    Assert.assertEquals("Caldari", random.getText());
    Assert.assertNotNull(random.toString());
  }

}
