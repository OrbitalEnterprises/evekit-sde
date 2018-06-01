package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapDenormalize;

public class TestMapDenormalize extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapDenormalize> next = MapDenormalize.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapDenormalize.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(503281, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapDenormalize> next = MapDenormalize.access(0, 1000, new AttributeSelector("{values:[11000003]}"), all, all, all, all, all, all, all, all, all, all,
                                                      all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapDenormalize random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(11000003, random.getItemID());
    Assert.assertEquals(3, random.getTypeID());
    Assert.assertEquals(3, random.getGroupID());
    Assert.assertNull(random.getSolarSystemID());
    Assert.assertNull(random.getConstellationID());
    Assert.assertNull(random.getRegionID());
    Assert.assertNull(random.getOrbitID());
    Assert.assertEquals(7.66138628000036e+18, random.getX(), 0.0001);
    Assert.assertEquals(1.53936778897024998E18, random.getY(), 0.0001);
    Assert.assertEquals(-9.33859380181364E18, random.getZ(), 0.0001);
    Assert.assertNull(random.getRadius());
    Assert.assertEquals("A-R00003", random.getItemName());
    Assert.assertNull(random.getSecurity());
    Assert.assertNull(random.getCelestialIndex());
    Assert.assertNull(random.getOrbitIndex());
    Assert.assertNotNull(random.toString());
  }
}
