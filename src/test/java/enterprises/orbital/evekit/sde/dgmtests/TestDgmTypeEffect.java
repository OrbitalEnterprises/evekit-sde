package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmTypeEffect;

public class TestDgmTypeEffect extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeEffect> next = DgmTypeEffect.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmTypeEffect.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(31624, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeEffect> next = DgmTypeEffect.access(0, 1000, new AttributeSelector("{values:[180]}"), new AttributeSelector("{values:[600]}"), all);
    Assert.assertEquals(1, next.size());
    DgmTypeEffect random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(180, random.id().getTypeID());
    Assert.assertEquals(600, random.id().getEffectID());
    Assert.assertFalse(random.isDefault());
    Assert.assertNotNull(random.toString());
  }
}
