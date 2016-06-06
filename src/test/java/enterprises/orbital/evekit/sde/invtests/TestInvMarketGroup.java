package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvMarketGroup;

public class TestInvMarketGroup extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvMarketGroup> next = InvMarketGroup.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvMarketGroup.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(2101, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvMarketGroup> next = InvMarketGroup.access(0, 1000, new AttributeSelector("{values:[19]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvMarketGroup random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(19, random.getMarketGroupID());
    Assert.assertNull(random.getParentGroupID());
    Assert.assertEquals("Trade Goods", random.getMarketGroupName());
    Assert.assertEquals(
                        "Many unusual goods are traded by capsuleers, including various tags, chips, tokens, charters and tools that are used in special activities or have exchange value with non-capsuleers",
                        random.getDescription());
    Assert.assertEquals(new Integer(2340), random.getIconID());
    Assert.assertEquals(0, random.getHasTypes());
  }

}
