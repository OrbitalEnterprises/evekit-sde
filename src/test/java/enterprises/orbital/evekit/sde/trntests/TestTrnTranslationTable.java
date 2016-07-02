package enterprises.orbital.evekit.sde.trntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.trn.TrnTranslationTable;

public class TestTrnTranslationTable extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslationTable> next = TrnTranslationTable.access(contid, maxresults, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = TrnTranslationTable.access(contid, maxresults, all, all, all, all, all);
    }
    Assert.assertEquals(32, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<TrnTranslationTable> next = TrnTranslationTable.access(0, 1000, new AttributeSelector("{values:['character.factionsTx']}"),
                                                                new AttributeSelector("{values:['description']}"), all, all, all);
    Assert.assertEquals(1, next.size());
    TrnTranslationTable random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals("character.factionsTx", random.getId().getSourceTable());
    Assert.assertEquals("description", random.getId().getTranslatedKey());
    Assert.assertEquals("dbo.chrFactions", random.getDestinationTable());
    Assert.assertEquals(16, random.getTcGroupID());
    Assert.assertEquals(29, random.getTcID());
    Assert.assertNotNull(random.toString());
  }

}
