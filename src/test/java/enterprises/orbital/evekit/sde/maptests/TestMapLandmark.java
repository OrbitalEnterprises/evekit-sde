package enterprises.orbital.evekit.sde.maptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.map.MapLandmark;

public class TestMapLandmark extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapLandmark> next = MapLandmark.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = MapLandmark.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(45, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<MapLandmark> next = MapLandmark.access(0, 1000, new AttributeSelector("{values:[9]}"), all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    MapLandmark random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.getLandmarkID());
    Assert.assertEquals("Ammatar", random.getLandmarkName());
    Assert.assertEquals(
                        "The Ammatars are part of the Amarr Empire, but are of Minmatar origin. During the time the Amarrians occupied the Minmatar home worlds one of the Minmatar tribes, the Nefantars, collaborated heavily with the Amarrians. The Nefantars fled Minmatar space during the Minmatar Rebellion and the Amarr Emperor set them up in Amarr controlled areas close to Minmatar space. Soon everyone had started calling them Ammatars. Today, the Ammatars enjoy a semi-autonomy in their own space and are still embroiled in war with their former Minmatar brethren.",
                        random.getDescription());
    Assert.assertNull(random.getLocationID());
    Assert.assertNull(random.getRadius());
    Assert.assertNull(random.getImportance());
    Assert.assertEquals(-8.79526953125e+16d, random.getX(), 0.0001);
    Assert.assertEquals(550164356827.736d, random.getY(), 0.0001);
    Assert.assertEquals(-6.5326328125e+16d, random.getZ(), 0.0001);
    Assert.assertNull(random.getIconID());
    Assert.assertNotNull(random.toString());
  }
}
