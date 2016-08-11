package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvControlTowerResource;

public class TestInvControlTowerResource extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvControlTowerResource> next = InvControlTowerResource.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvControlTowerResource.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(339, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvControlTowerResource> next = InvControlTowerResource.access(0, 1000, new AttributeSelector("{values:[12235]}"),
                                                                        new AttributeSelector("{values:[16275]}"), all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvControlTowerResource random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(12235, random.id().getControlTowerTypeID());
    Assert.assertEquals(16275, random.id().getResourceTypeID());
    Assert.assertEquals(4, random.getPurpose());
    Assert.assertEquals(400, random.getQuantity());
    Assert.assertNull(random.getMinSecurityLevel());
    Assert.assertNull(random.getFactionID());
    Assert.assertNotNull(random.toString());
  }

}
