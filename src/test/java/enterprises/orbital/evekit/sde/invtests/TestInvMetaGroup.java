package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvMetaGroup;

public class TestInvMetaGroup extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvMetaGroup> next = InvMetaGroup.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvMetaGroup.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(17, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvMetaGroup> next = InvMetaGroup.access(0, 1000, new AttributeSelector("{values:[6]}"), all, all, all);
    Assert.assertEquals(1, next.size());
    InvMetaGroup random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(6, random.getMetaGroupID());
    Assert.assertEquals("Deadspace", random.getMetaGroupName());
    Assert.assertEquals("Modules found in deadspace.", random.getDescription());
    Assert.assertNull(random.getIconID());
    Assert.assertNotNull(random.toString());
  }
}
