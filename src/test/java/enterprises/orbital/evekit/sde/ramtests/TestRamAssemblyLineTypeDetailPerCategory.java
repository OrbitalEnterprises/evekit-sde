package enterprises.orbital.evekit.sde.ramtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineTypeDetailPerCategory;

public class TestRamAssemblyLineTypeDetailPerCategory extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamAssemblyLineTypeDetailPerCategory> next = RamAssemblyLineTypeDetailPerCategory.access(contid, maxresults, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = RamAssemblyLineTypeDetailPerCategory.access(contid, maxresults, all, all, all, all, all);
    }
    Assert.assertEquals(891, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamAssemblyLineTypeDetailPerCategory> next = RamAssemblyLineTypeDetailPerCategory.access(0, 1000, new AttributeSelector("{values:[6]}"),
                                                                                                  new AttributeSelector("{values:[4]}"), all, all, all);
    Assert.assertEquals(1, next.size());
    RamAssemblyLineTypeDetailPerCategory random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(6, random.id().getAssemblyLineTypeID());
    Assert.assertEquals(4, random.id().getCategoryID());
    Assert.assertEquals(1.0, random.getTimeMultiplier(), 0.0001);
    Assert.assertEquals(1.0, random.getMaterialMultiplier(), 0.0001);
    Assert.assertEquals(1.0, random.getCostMultiplier(), 0.0001);
    Assert.assertNotNull(random.toString());
  }
}
