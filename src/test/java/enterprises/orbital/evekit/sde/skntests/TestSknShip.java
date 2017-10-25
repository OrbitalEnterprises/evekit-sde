package enterprises.orbital.evekit.sde.skntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.skn.SknShip;

public class TestSknShip extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknShip> next = SknShip.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = SknShip.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(2999, contid);
  }

  @Test
  public void testRandomElement() {
    List<SknShip> next = SknShip.access(0, 1000, new AttributeSelector("{values:[1795]}"), new AttributeSelector("{values:[645]}"));
    Assert.assertEquals(1, next.size());
    SknShip random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1795, random.getSkinID());
    Assert.assertEquals(645, random.getTypeID());
    Assert.assertNotNull(random.toString());
  }

}
