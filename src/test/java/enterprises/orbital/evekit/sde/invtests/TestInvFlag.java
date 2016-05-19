package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvFlag;

public class TestInvFlag extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvFlag> next = InvFlag.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvFlag.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(152, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvFlag> next = InvFlag.access(0, 1000, new AttributeSelector("{values:[11]}"), all, all, all);
    Assert.assertEquals(1, next.size());
    InvFlag random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(11, random.getFlagID());
    Assert.assertEquals("LoSlot0", random.getFlagName());
    Assert.assertEquals("Low power slot 1", random.getFlagText());
    Assert.assertEquals(0, random.getOrderID());
    Assert.assertNotNull(random.toString());
  }

}
