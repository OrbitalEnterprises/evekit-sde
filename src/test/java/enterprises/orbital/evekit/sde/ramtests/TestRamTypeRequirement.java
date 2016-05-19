package enterprises.orbital.evekit.sde.ramtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ram.RamTypeRequirement;

public class TestRamTypeRequirement extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamTypeRequirement> next = RamTypeRequirement.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = RamTypeRequirement.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(48110, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamTypeRequirement> next = RamTypeRequirement.access(0, 1000, new AttributeSelector("{values:[683]}"), new AttributeSelector("{values:[1]}"),
                                                              new AttributeSelector("{values:[38]}"), all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    RamTypeRequirement random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(683, random.getId().getTypeID());
    Assert.assertEquals(1, random.getId().getActivityID());
    Assert.assertEquals(38, random.getId().getRequiredTypeID());
    Assert.assertEquals(new Integer(2), random.getQuantity());
    Assert.assertNull(random.getConsume());
    Assert.assertNull(random.getDamagePerJob());
    Assert.assertNull(random.getLevel());
    Assert.assertNull(random.getProbability());
    Assert.assertNull(random.getRaceID());
    Assert.assertNull(random.getRecycle());
    Assert.assertNotNull(random.toString());
  }

}
