package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvTypeMaterial;

public class TestInvTypeMaterial extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvTypeMaterial> next = InvTypeMaterial.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvTypeMaterial.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(40708, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvTypeMaterial> next = InvTypeMaterial.access(0, 1000, new AttributeSelector("{values:[20]}"), new AttributeSelector("{values:[36]}"), all);
    Assert.assertEquals(1, next.size());
    InvTypeMaterial random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(20, random.id().getTypeID());
    Assert.assertEquals(36, random.id().getMaterialTypeID());
    Assert.assertEquals(267, random.getQuantity());
    Assert.assertNotNull(random.toString());
  }
}
