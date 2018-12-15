package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvType;

public class TestInvType extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvType> next = InvType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(35711, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvType> next = InvType.access(0, 1000, new AttributeSelector("{values:[16]}"), all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                        all);
    Assert.assertEquals(1, next.size());
    InvType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(16, random.getTypeID());
    Assert.assertEquals(10, random.getGroupID());
    Assert.assertEquals("Stargate (Caldari System)", random.getTypeName());
    Assert.assertEquals("", random.getDescription());
    Assert.assertEquals(new Integer(235), random.getGraphicID());
    Assert.assertEquals(1.0e+11, random.getMass(), 0.000001);
    Assert.assertEquals(10000000.0, random.getVolume(), 0.000001);
    Assert.assertEquals(0.0, random.getCapacity(), 0.000001);
    Assert.assertEquals(1, random.getPortionSize());
    Assert.assertEquals(new Integer(1), random.getRaceID());
    Assert.assertNull(random.getBasePrice());
    Assert.assertFalse(random.isPublished());
    Assert.assertNull(random.getMarketGroupID());
    Assert.assertNull(random.getIconID());
    Assert.assertNotNull(random.toString());
  }
}
