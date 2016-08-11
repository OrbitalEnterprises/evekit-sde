package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapRegionJump;

public class TestMapRegionJump extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapRegionJump> next = MapRegionJump.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapRegionJump.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(306, contid);
  }

  @Test
  public void testRandomElement() {
    List<MapRegionJump> next = MapRegionJump.access(0, 1000, new AttributeSelector("{values:[10000002]}"), new AttributeSelector("{values:[10000016]}"));
    Assert.assertEquals(1, next.size());
    MapRegionJump random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000002, random.id().getFromRegionID());
    Assert.assertEquals(10000016, random.id().getToRegionID());
    Assert.assertNotNull(random.toString());
  }
}
