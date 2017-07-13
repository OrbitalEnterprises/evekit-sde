package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvVolume;

public class TestInvVolume extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvVolume> next = InvVolume.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvVolume.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(489, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvVolume> next = InvVolume.access(0, 1000, new AttributeSelector("{values:[591]}"), all);
    Assert.assertEquals(1, next.size());
    InvVolume random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(591, random.getTypeID());
    Assert.assertEquals(2500, random.getVolume());
    Assert.assertNotNull(random.toString());
  }

}
