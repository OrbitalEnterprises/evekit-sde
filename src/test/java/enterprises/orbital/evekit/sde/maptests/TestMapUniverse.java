package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapUniverse;

public class TestMapUniverse extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapUniverse> next = MapUniverse.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapUniverse.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(2, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapUniverse> next = MapUniverse.access(0, 1000, new AttributeSelector("{values:[9000001]}"), all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapUniverse random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9000001, random.getUniverseID());
    Assert.assertEquals("EVE Wormhole Universe", random.getUniverseName());
    Assert.assertEquals(7.70416391716947e+18, random.getX(), 0.0001);
    Assert.assertEquals(1.53937198079579e+18, random.getY(), 0.0001);
    Assert.assertEquals(-9.51905586204134e+18, random.getZ(), 0.0001);
    Assert.assertEquals(7.25177035770814e+18, random.getXMin(), 0.0001);
    Assert.assertEquals(8.1565574766308e+18, random.getXMax(), 0.0001);
    Assert.assertEquals(1.08697842133446e+18, random.getYMin(), 0.0001);
    Assert.assertEquals(1.99176554025711e+18, random.getYMax(), 0.0001);
    Assert.assertEquals(9.06666230258001e+18, random.getZMin(), 0.0001);
    Assert.assertEquals(9.97144942150266e+18, random.getZMax(), 0.0001);
    Assert.assertEquals(4.52393559461327e+17, random.getRadius(), 0.0001);
    Assert.assertNotNull(random.toString());
  }
}
