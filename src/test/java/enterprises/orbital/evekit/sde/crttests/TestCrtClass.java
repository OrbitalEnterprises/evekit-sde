package enterprises.orbital.evekit.sde.crttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crt.CrtClass;

public class TestCrtClass extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtClass> next = CrtClass.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrtClass.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(124, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtClass> next = CrtClass.access(0, 1000, new AttributeSelector("{values:[7]}"), all, all);
    Assert.assertEquals(1, next.size());
    CrtClass random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(7, random.getClassID());
    Assert.assertEquals("Large Hybrid Turret", random.getClassName());
    Assert.assertEquals("Large Hybrid Turret", random.getDescription());
    Assert.assertNotNull(random.toString());
  }
}
