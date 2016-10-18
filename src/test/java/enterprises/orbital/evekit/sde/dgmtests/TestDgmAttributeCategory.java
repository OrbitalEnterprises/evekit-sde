package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmAttributeCategory;

public class TestDgmAttributeCategory extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmAttributeCategory> next = DgmAttributeCategory.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmAttributeCategory.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(36, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmAttributeCategory> next = DgmAttributeCategory.access(0, 1000, new AttributeSelector("{values:[6]}"), all, all);
    Assert.assertEquals(1, next.size());
    DgmAttributeCategory random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(6, random.getCategoryID());
    Assert.assertEquals("Targeting", random.getCategoryName());
    Assert.assertEquals("Targeting Attributes for ships", random.getCategoryDescription());
    Assert.assertNotNull(random.toString());
  }
}
