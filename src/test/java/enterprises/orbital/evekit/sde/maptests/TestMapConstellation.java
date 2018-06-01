package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapConstellation;

public class TestMapConstellation extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapConstellation> next = MapConstellation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapConstellation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(1145, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapConstellation> next = MapConstellation.access(0, 1000, new AttributeSelector("{values:[20000016]}"), all, all, all, all, all, all, all, all, all,
                                                          all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapConstellation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000001, random.getRegionID());
    Assert.assertEquals(20000016, random.getConstellationID());
    Assert.assertEquals("Joas", random.getConstellationName());
    Assert.assertEquals(-4.91739162817057e16, random.getX(), 0.0001);
    Assert.assertEquals(3.38362650128478e16, random.getY(), 0.0001);
    Assert.assertEquals(-4.20570637094093e16, random.getZ(), 0.0001);
    Assert.assertEquals(-5.34522376780293e16, random.getXMin(), 0.0001);
    Assert.assertEquals(-4.48955948853822e16, random.getXMax(), 0.0001);
    Assert.assertEquals(2.55876598481696e16, random.getYMin(), 0.0001);
    Assert.assertEquals(4.20848701775261e16, random.getYMax(), 0.0001);
    Assert.assertEquals(3.59922799350598e16, random.getZMin(), 0.0001);
    Assert.assertEquals(4.81218474837588e16, random.getZMax(), 0.0001);
    Assert.assertEquals(new Integer(500007), random.getFactionID());
    Assert.assertEquals(8.2486e15, random.getRadius(), 0.0001);
    Assert.assertNotNull(random.toString());
  }
}
