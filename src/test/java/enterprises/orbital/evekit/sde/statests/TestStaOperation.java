package enterprises.orbital.evekit.sde.statests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.sta.StaOperation;

public class TestStaOperation extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaOperation> next = StaOperation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = StaOperation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(55, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaOperation> next = StaOperation.access(0, 1000, new AttributeSelector("{values:[8]}"), all, all, all, all, all, all, all, all, all, all, all, all,
                                                  all);
    Assert.assertEquals(1, next.size());
    StaOperation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(3, random.getActivityID());
    Assert.assertEquals(8, random.getOperationID());
    Assert.assertEquals("Refinery", random.getOperationName());
    Assert.assertEquals("Refines the minerals, making purified ingots and rock materials.", random.getDescription());
    Assert.assertEquals(0, random.getFringe());
    Assert.assertEquals(10, random.getCorridor());
    Assert.assertEquals(45, random.getHub());
    Assert.assertEquals(45, random.getBorder());
    Assert.assertEquals(15, random.getRatio());
    Assert.assertEquals(new Integer(4024), random.getCaldariStationTypeID());
    Assert.assertEquals(new Integer(2497), random.getMinmatarStationTypeID());
    Assert.assertEquals(new Integer(1928), random.getAmarrStationTypeID());
    Assert.assertEquals(new Integer(3867), random.getGallenteStationTypeID());
    Assert.assertEquals(new Integer(3865), random.getJoveStationTypeID());
    Assert.assertNotNull(random.toString());
  }

}
