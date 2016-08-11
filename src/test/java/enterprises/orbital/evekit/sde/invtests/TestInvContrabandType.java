package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvContrabandType;

public class TestInvContrabandType extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvContrabandType> next = InvContrabandType.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvContrabandType.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(50, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvContrabandType> next = InvContrabandType.access(0, 1000, new AttributeSelector("{values:[500001]}"), new AttributeSelector("{values:[3721]}"), all,
                                                            all, all, all);
    Assert.assertEquals(1, next.size());
    InvContrabandType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(500001, random.id().getFactionID());
    Assert.assertEquals(3721, random.id().getTypeID());
    Assert.assertEquals(0.2, random.getStandingLoss(), 0.0001);
    Assert.assertEquals(-1, random.getConfiscateMinSec(), 0.0001);
    Assert.assertEquals(5, random.getFineByValue(), 0.0001);
    Assert.assertEquals(1.1, random.getAttackMinSec(), 0.0001);
    Assert.assertNotNull(random.toString());
  }

}
