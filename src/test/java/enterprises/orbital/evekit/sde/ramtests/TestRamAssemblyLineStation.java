package enterprises.orbital.evekit.sde.ramtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineStation;

public class TestRamAssemblyLineStation extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamAssemblyLineStation> next = RamAssemblyLineStation.access(contid, maxresults, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = RamAssemblyLineStation.access(contid, maxresults, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(4383, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamAssemblyLineStation> next = RamAssemblyLineStation.access(0, 1000, new AttributeSelector("{values:[60000988]}"),
                                                                      new AttributeSelector("{values:[8]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    RamAssemblyLineStation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(60000988, random.getId().getStationID());
    Assert.assertEquals(8, random.getId().getAssemblyLineTypeID());
    Assert.assertEquals(20, random.getQuantity());
    Assert.assertEquals(1529, random.getStationTypeID());
    Assert.assertEquals(1000010, random.getOwnerID());
    Assert.assertEquals(30001401, random.getSolarSystemID());
    Assert.assertEquals(10000016, random.getRegionID());
    Assert.assertNotNull(random.toString());
  }

}
