package enterprises.orbital.evekit.sde.crptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crp.CrpActivity;

public class TestCrpActivity extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpActivity> activities = CrpActivity.access(contid, maxresults, all, all, all);
    Assert.assertNotNull(activities);
    Assert.assertEquals(20, activities.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpActivity> activities = CrpActivity.access(0, 1000, new AttributeSelector("{values:[7]}"), all, all);
    Assert.assertEquals(1, activities.size());
    CrpActivity random = activities.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(7, random.getActivityID());
    Assert.assertEquals("Hi-Tech", random.getActivityName());
    Assert.assertEquals("", random.getDescription());

    Assert.assertNotNull(random.toString());
  }
}
