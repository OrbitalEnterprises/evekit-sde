package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvCategory;

public class TestInvCategory extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvCategory> next = InvCategory.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvCategory.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(43, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvCategory> next = InvCategory.access(0, 1000, new AttributeSelector("{values:[4]}"), all, all, all);
    Assert.assertEquals(1, next.size());
    InvCategory random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(4, random.getCategoryID());
    Assert.assertEquals("Material", random.getCategoryName());
    Assert.assertEquals(new Integer(22), random.getIconID());
    Assert.assertEquals(1, random.getPublished());
    Assert.assertNotNull(random.toString());
  }

}
