package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapRegion;

public class TestMapRegion extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapRegion> next = MapRegion.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapRegion.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(100, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapRegion> next = MapRegion.access(0, 1000, new AttributeSelector("{values:[10000016]}"), all, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapRegion random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000016, random.getRegionID());
    Assert.assertEquals("Lonetrek", random.getRegionName());
    Assert.assertEquals(-1.89171222177239e17, random.getX(), 0.0001);
    Assert.assertEquals(9.45524633509496e16, random.getY(), 0.0001);
    Assert.assertEquals(1.5569612898227e17, random.getZ(), 0.0001);
    Assert.assertEquals(-2.33466109933311e17, random.getXMin(), 0.0001);
    Assert.assertEquals(-1.44876334421167e17, random.getXMax(), 0.0001);
    Assert.assertEquals(6.99908433405749e16, random.getYMin(), 0.0001);
    Assert.assertEquals(1.19114083361324e17, random.getYMax(), 0.0001);
    Assert.assertEquals(-1.93878176320107e17, random.getZMin(), 0.0001);
    Assert.assertEquals(-1.17514081644434e17, random.getZMax(), 0.0001);
    Assert.assertEquals(new Integer(500001), random.getFactionID());
    Assert.assertNull(random.getRadius());
    Assert.assertNotNull(random.toString());
  }
}
