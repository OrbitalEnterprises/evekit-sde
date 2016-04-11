package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapCelestialStatistic;

public class TestMapCelestialStatistic extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapCelestialStatistic> next = MapCelestialStatistic.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                                                    all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapCelestialStatistic.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                          all);
    }
    Assert.assertEquals(471577, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapCelestialStatistic> next = MapCelestialStatistic.access(0, 1000, new AttributeSelector("{values:[40000008]}"), all, all, all, all, all, all, all,
                                                                    all, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapCelestialStatistic random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(40000008, random.getCelestialID());
    Assert.assertEquals(288.849561395683, random.getTemperature(), 0.0001);
    Assert.assertEquals("0.0", random.getSpectralClass());
    Assert.assertEquals(0.0, random.getLuminosity(), 0.0001);
    Assert.assertEquals(0.0, random.getAge(), 0.0001);
    Assert.assertEquals(0.0, random.getLife(), 0.0001);
    Assert.assertEquals(674980000000.0, random.getOrbitRadius(), 0.0001);
    Assert.assertEquals(0.0366146, random.getEccentricity(), 0.0001);
    Assert.assertEquals(2.22368008041802e+24, random.getMassDust(), 0.0001);
    Assert.assertEquals(1.84618e+17, random.getMassGas(), 0.0001);
    Assert.assertEquals(0, random.getFragmented());
    Assert.assertEquals(3122.15458534349, random.getDensity(), 0.0001);
    Assert.assertEquals(4.83567604397697, random.getSurfaceGravity(), 0.0001);
    Assert.assertEquals(7319.78760397218, random.getEscapeVelocity(), 0.0001);
    Assert.assertEquals(2548940000.0, random.getOrbitPeriod(), 0.0001);
    Assert.assertEquals(101420.0, random.getRotationRate(), 0.0001);
    Assert.assertEquals(0, random.getLocked());
    Assert.assertEquals(0.0, random.getPressure(), 0.0001);
    Assert.assertEquals(5540000.0, random.getRadius(), 0.0001);
    Assert.assertEquals(0.0, random.getMass(), 0.0001);
    Assert.assertNotNull(random.toString());
  }
}
