package enterprises.orbital.evekit.sde.ramtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ram.RamActivity;

public class TestRamActivity extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamActivity> next = RamActivity.access(contid, maxresults, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = RamActivity.access(contid, maxresults, all, all, all, all, all);
    }
    Assert.assertEquals(10, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamActivity> next = RamActivity.access(0, 1000, new AttributeSelector("{values:[5]}"), all, all, all, all);
    Assert.assertEquals(1, next.size());
    RamActivity random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(5, random.getActivityID());
    Assert.assertEquals("Copying", random.getActivityName());
    Assert.assertEquals("33_02", random.getIconNo());
    Assert.assertEquals("Copying", random.getDescription());
    Assert.assertTrue(random.isPublished());
    Assert.assertNotNull(random.toString());
  }

}
