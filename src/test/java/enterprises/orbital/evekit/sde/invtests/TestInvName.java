package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvName;

public class TestInvName extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvName> next = InvName.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvName.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(519945, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvName> next = InvName.access(0, 1000, new AttributeSelector("{values:[9]}"), all);
    Assert.assertEquals(1, next.size());
    InvName random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.getItemID());
    Assert.assertEquals("EVE Universe", random.getItemName());
    Assert.assertNotNull(random.toString());
  }

}
