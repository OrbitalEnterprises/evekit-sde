package enterprises.orbital.evekit.sde.skntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.skn.SknSkin;

public class TestSknSkin extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknSkin> next = SknSkin.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = SknSkin.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(3716, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknSkin> next = SknSkin.access(0, 1000, new AttributeSelector("{values:[12]}"), all, all);
    Assert.assertEquals(1, next.size());
    SknSkin random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(12, random.getSkinID());
    Assert.assertEquals("Prophecy Kador", random.getInternalName());
    Assert.assertEquals(2, random.getSkinMaterialID());
    Assert.assertNotNull(random.toString());
  }

}
