package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapLocationWormholeClass;

public class TestMapLocationWormholeClass extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapLocationWormholeClass> next = MapLocationWormholeClass.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapLocationWormholeClass.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(799, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapLocationWormholeClass> next = MapLocationWormholeClass.access(0, 1000, new AttributeSelector("{values:[10000008]}"), all);
    Assert.assertEquals(1, next.size());
    MapLocationWormholeClass random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000008, random.getLocationID());
    Assert.assertEquals(9, random.getWormholeClassID());
    Assert.assertNotNull(random.toString());
  }
}
