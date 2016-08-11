package enterprises.orbital.evekit.sde.statests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.sta.StaStation;

public class TestStaStation extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaStation> next = StaStation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = StaStation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(5189, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaStation> next = StaStation.access(0, 1000, new AttributeSelector("{values:[60000019]}"), all, all, all, all, all, all, all, all, all, all, all, all,
                                              all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    StaStation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(60000019, random.getStationID());
    Assert.assertEquals(1.0, random.getSecurity(), 0.0001);
    Assert.assertEquals(0.0, random.getDockingCostPerVolume(), 0.0001);
    Assert.assertEquals(50000000.0, random.getMaxShipVolumeDockable(), 0.0001);
    Assert.assertEquals(10000, random.getOfficeRentalCost());
    Assert.assertEquals(26, random.getOperationID());
    Assert.assertEquals(1531, random.getStationTypeID());
    Assert.assertEquals(1000002, random.getCorporationID());
    Assert.assertEquals(30002778, random.getSolarSystemID());
    Assert.assertEquals(20000407, random.getConstellationID());
    Assert.assertEquals(10000033, random.getRegionID());
    Assert.assertEquals("Tasabeshi VI - Moon 1 - CBD Corporation Storage", random.getStationName());
    Assert.assertEquals(-701220986880.0, random.getX(), 0.0001);
    Assert.assertEquals(-78208819200.0, random.getY(), 0.0001);
    Assert.assertEquals(1824795402240.0, random.getZ(), 0.0001);
    Assert.assertEquals(0.5, random.getReprocessingEfficiency(), 0.0001);
    Assert.assertEquals(0.05, random.getReprocessingStationsTake(), 0.0001);
    Assert.assertEquals(4, random.getReprocessingHangarFlag());
    Assert.assertNotNull(random.toString());
  }

}
