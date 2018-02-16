package enterprises.orbital.evekit.sde.crptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporationTrade;

public class TestCrpNPCCorporationTrade extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcCorporationTrade> next = CrpNpcCorporationTrade.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrpNpcCorporationTrade.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(13304, contid);
  }

  @Test
  public void testRandomElement() {
    List<CrpNpcCorporationTrade> next = CrpNpcCorporationTrade.access(0, 1000, new AttributeSelector("{values:[1000002]}"),
                                                                      new AttributeSelector("{values:[1032]}"));
    Assert.assertEquals(1, next.size());
    CrpNpcCorporationTrade random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1000002, random.id().getCorporationID());
    Assert.assertEquals(1032, random.id().getTypeID());
    Assert.assertNotNull(random.toString());
  }
}
