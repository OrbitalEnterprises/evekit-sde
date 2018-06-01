package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapLocationScene;

public class TestMapLocationScene extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapLocationScene> next = MapLocationScene.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapLocationScene.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(105, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapLocationScene> next = MapLocationScene.access(0, 1000, new AttributeSelector("{values:[10000009]}"), all);
    Assert.assertEquals(1, next.size());
    MapLocationScene random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(10000009, random.getLocationID());
    Assert.assertEquals(11844, random.getGraphicID());
    Assert.assertNotNull(random.toString());
  }
}
