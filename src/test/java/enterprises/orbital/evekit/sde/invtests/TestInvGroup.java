package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvGroup;

public class TestInvGroup extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvGroup> next = InvGroup.access(contid, maxresults, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvGroup.access(contid, maxresults, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(1293, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvGroup> next = InvGroup.access(0, 1000, new AttributeSelector("{values:[9]}"), all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvGroup random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.getGroupID());
    Assert.assertEquals(2, random.getCategoryID());
    Assert.assertEquals("Asteroid Belt", random.getGroupName());
    Assert.assertEquals(new Integer(15), random.getIconID());
    Assert.assertEquals(0, random.getUseBasePrice());
    Assert.assertEquals(0, random.getAnchored());
    Assert.assertEquals(0, random.getAnchorable());
    Assert.assertEquals(0, random.getFittableNonSingleton());
    Assert.assertEquals(0, random.getPublished());
    Assert.assertNotNull(random.toString());
  }

}
