package enterprises.orbital.evekit.sde.indtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ind.IndActivity;

public class TestIndActivity extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivity> next = IndActivity.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = IndActivity.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(16338, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivity> next = IndActivity.access(0, 1000, new AttributeSelector("{values:[1072]}"), new AttributeSelector("{values:[4]}"), all);
    Assert.assertEquals(1, next.size());
    IndActivity random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1072, random.getTypeID());
    Assert.assertEquals(4, random.getActivityID());
    Assert.assertEquals(315, random.getTime());
    Assert.assertNotNull(random.toString());
  }
}
