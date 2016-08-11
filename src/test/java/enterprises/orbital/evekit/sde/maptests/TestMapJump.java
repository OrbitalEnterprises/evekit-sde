package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapJump;

public class TestMapJump extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapJump> next = MapJump.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapJump.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(13826, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapJump> next = MapJump.access(0, 1000, new AttributeSelector("{values:[50000007]}"), all);
    Assert.assertEquals(1, next.size());
    MapJump random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(50000007, random.getStargateID());
    Assert.assertEquals(50000150, random.getDestinationID());
    Assert.assertNotNull(random.toString());
  }
}
