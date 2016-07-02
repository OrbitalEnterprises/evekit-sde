package enterprises.orbital.evekit.sde.trntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.trn.TrnTranslationColumn;

public class TestTrnTranslationColumn extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslationColumn> next = TrnTranslationColumn.access(contid, maxresults, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = TrnTranslationColumn.access(contid, maxresults, all, all, all, all, all);
    }
    Assert.assertEquals(32, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslationColumn> next = TrnTranslationColumn.access(0, 1000, new AttributeSelector("{values:[12]}"), all, all, all, all);
    Assert.assertEquals(1, next.size());
    TrnTranslationColumn random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(8, random.getTcGroupID());
    Assert.assertEquals(12, random.getTcID());
    Assert.assertEquals("dbo.chrBloodlines", random.getTableName());
    Assert.assertEquals("description", random.getColumnName());
    Assert.assertEquals("bloodlineID", random.getMasterID());
    Assert.assertNotNull(random.toString());
  }

}
