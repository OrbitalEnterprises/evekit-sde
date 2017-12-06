package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvUniqueName;

public class TestInvUniqueName extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvUniqueName> next = InvUniqueName.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvUniqueName.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(365440, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvUniqueName> next = InvUniqueName.access(0, 1000, new AttributeSelector("{values:[3011690]}"), all, all);
    Assert.assertEquals(1, next.size());
    InvUniqueName random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(3011690, random.getItemID());
    Assert.assertEquals("Aakonoshin Piertalen", random.getItemName());
    Assert.assertEquals(1, random.getGroupID());
    Assert.assertNotNull(random.toString());
  }

}
