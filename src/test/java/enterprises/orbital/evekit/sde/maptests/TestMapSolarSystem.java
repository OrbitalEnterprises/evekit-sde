package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapSolarSystem;

public class TestMapSolarSystem extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapSolarSystem> next = MapSolarSystem.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                                      all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapSolarSystem.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                   all, all, all, all, all);
    }
    Assert.assertEquals(8035, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapSolarSystem> next = MapSolarSystem.access(0, 1000, new AttributeSelector("{values:[30000019]}"), all, all, all, all, all, all, all, all, all, all,
                                                      all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapSolarSystem random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000001, random.getRegionID());
    Assert.assertEquals(20000003, random.getConstellationID());
    Assert.assertEquals(30000019, random.getSolarSystemID());
    Assert.assertEquals("Podion", random.getSolarSystemName());
    Assert.assertEquals(-5.87098578016163e16, random.getX(), 0.0001);
    Assert.assertEquals(2.60445093863922e16, random.getY(), 0.0001);
    Assert.assertEquals(-1.01528995159068e17, random.getZ(), 0.0001);
    Assert.assertEquals(-5.87103673819591e16, random.getXMin(), 0.0001);
    Assert.assertEquals(-5.87093807567528e16, random.getXMax(), 0.0001);
    Assert.assertEquals(2.60444170151287e16, random.getYMin(), 0.0001);
    Assert.assertEquals(2.60445958612703e16, random.getYMax(), 0.0001);
    Assert.assertEquals(1.01527134943693e17, random.getZMin(), 0.0001);
    Assert.assertEquals(1.01532375865627e17, random.getZMax(), 0.0001);
    Assert.assertEquals(0.07842, random.getLuminosity(), 0.0001);
    Assert.assertEquals(0, random.getBorder());
    Assert.assertEquals(1, random.getFringe());
    Assert.assertEquals(0, random.getCorridor());
    Assert.assertEquals(0, random.getHub());
    Assert.assertEquals(0, random.getInternational());
    Assert.assertEquals(0, random.getRegional());
    Assert.assertNull(random.getConstellation());
    Assert.assertEquals(0.1097049725986509, random.getSecurity(), 0.0001);
    Assert.assertEquals(500007, (int) random.getFactionID());
    Assert.assertEquals(2620460967184.0, random.getRadius(), 0.0001);
    Assert.assertEquals(3798, random.getSunTypeID());
    Assert.assertEquals("B3", random.getSecurityClass());
    Assert.assertNotNull(random.toString());
  }
}
