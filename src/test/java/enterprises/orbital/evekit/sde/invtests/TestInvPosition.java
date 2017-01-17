package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvPosition;

public class TestInvPosition extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvPosition> next = InvPosition.access(contid, maxresults, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvPosition.access(contid, maxresults, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(508384, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvPosition> next = InvPosition.access(0, 1000, new AttributeSelector("{values:[9]}"), all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvPosition random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.getItemID());
    Assert.assertEquals(0, random.getX(), 0.001);
    Assert.assertEquals(0, random.getY(), 0.001);
    Assert.assertEquals(0, random.getZ(), 0.001);
    Assert.assertNull(random.getYaw());
    Assert.assertNull(random.getPitch());
    Assert.assertNull(random.getRoll());
    Assert.assertNotNull(random.toString());
  }

}
