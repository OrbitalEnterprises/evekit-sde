package enterprises.orbital.evekit.sde.ramtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineType;

public class TestRamAssemblyLineType extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamAssemblyLineType> next = RamAssemblyLineType.access(contid, maxresults, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = RamAssemblyLineType.access(contid, maxresults, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(141, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamAssemblyLineType> next = RamAssemblyLineType.access(0, 1000, new AttributeSelector("{values:[11]}"), all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    RamAssemblyLineType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(11, random.getAssemblyLineTypeID());
    Assert.assertEquals("ME Research", random.getAssemblyLineTypeName());
    Assert.assertEquals("ME Research", random.getDescription());
    Assert.assertEquals(1.0, random.getBaseTimeMultiplier(), 0.0001);
    Assert.assertEquals(1.0, random.getBaseMaterialMultiplier(), 0.0001);
    Assert.assertEquals(1.0, random.getVolume(), 0.0001);
    Assert.assertEquals(4, random.getActivityID());
    Assert.assertNull(random.getMinCostPerHour());
    Assert.assertEquals(1.0, random.getBaseCostMultiplier(), 0.0001);
    Assert.assertNotNull(random.toString());
  }

}
