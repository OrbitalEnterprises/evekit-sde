package enterprises.orbital.evekit.sde.statests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.sta.StaStationType;

public class TestStaStationType extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaStationType> next = StaStationType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = StaStationType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(67, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaStationType> next = StaStationType.access(0, 1000, new AttributeSelector("{values:[1529]}"), all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    StaStationType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1529, random.getStationTypeID());
    Assert.assertEquals(-170.0, random.getDockEntryX(), 0.0001);
    Assert.assertEquals(3217.0, random.getDockEntryY(), 0.0001);
    Assert.assertEquals(12112.1542969, random.getDockEntryZ(), 0.0001);
    Assert.assertEquals(0.0, random.getDockOrientationX(), 0.0001);
    Assert.assertEquals(5.96046447754e-08, random.getDockOrientationY(), 0.0001);
    Assert.assertEquals(0.999999940395, random.getDockOrientationZ(), 0.0001);
    Assert.assertNull(random.getOperationID());
    Assert.assertNull(random.getOfficeSlots());
    Assert.assertNull(random.getReprocessingEfficiency());
    Assert.assertEquals(0, random.getConquerable());
    Assert.assertNotNull(random.toString());
  }

}
