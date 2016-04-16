package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvControlTowerResourcePurpose;

public class TestInvControlTowerResourcePurpose extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvControlTowerResourcePurpose> next = InvControlTowerResourcePurpose.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvControlTowerResourcePurpose.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(4, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvControlTowerResourcePurpose> next = InvControlTowerResourcePurpose.access(0, 1000, new AttributeSelector("{values:[2]}"), all);
    Assert.assertEquals(1, next.size());
    InvControlTowerResourcePurpose random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(2, random.getPurpose());
    Assert.assertEquals("Power", random.getPurposeText());
    Assert.assertNotNull(random.toString());
  }

}
