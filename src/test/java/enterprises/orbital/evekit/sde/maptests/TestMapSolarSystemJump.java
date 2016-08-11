package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapSolarSystemJump;

public class TestMapSolarSystemJump extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapSolarSystemJump> next = MapSolarSystemJump.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapSolarSystemJump.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(13826, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapSolarSystemJump> next = MapSolarSystemJump.access(0, 1000, new AttributeSelector("{values:[30000003]}"),
                                                              new AttributeSelector("{values:[30000007]}"), all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapSolarSystemJump random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000001, random.getFromRegionID());
    Assert.assertEquals(20000001, random.getFromConstellationID());
    Assert.assertEquals(30000003, random.id().getFromSolarSystemID());
    Assert.assertEquals(30000007, random.id().getToSolarSystemID());
    Assert.assertEquals(20000001, random.getToConstellationID());
    Assert.assertEquals(10000001, random.getToRegionID());
    Assert.assertNotNull(random.toString());
  }
}
