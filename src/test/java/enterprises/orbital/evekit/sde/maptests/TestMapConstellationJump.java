package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapConstellationJump;

public class TestMapConstellationJump extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapConstellationJump> next = MapConstellationJump.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapConstellationJump.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(2292, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapConstellationJump> next = MapConstellationJump.access(0, 1000, new AttributeSelector("{values:[20000001]}"),
                                                                  new AttributeSelector("{values:[20000367]}"), all, all);
    Assert.assertEquals(1, next.size());
    MapConstellationJump random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000001, random.getFromRegionID());
    Assert.assertEquals(20000001, random.id().getFromConstellationID());
    Assert.assertEquals(20000367, random.id().getToConstellationID());
    Assert.assertEquals(10000030, random.getToRegionID());
    Assert.assertNotNull(random.toString());
  }
}
