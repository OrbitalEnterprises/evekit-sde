package enterprises.orbital.evekit.sde.skntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.skn.SknMaterial;

public class TestSknMaterial extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknMaterial> next = SknMaterial.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = SknMaterial.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(301, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknMaterial> next = SknMaterial.access(0, 1000, new AttributeSelector("{values:[7]}"), all, all);
    Assert.assertEquals(1, next.size());
    SknMaterial random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(7, random.getSkinMaterialID());
    Assert.assertEquals(505351, random.getDisplayNameID());
    Assert.assertEquals(7, random.getMaterialSetID());
    Assert.assertNotNull(random.toString());
  }

}
