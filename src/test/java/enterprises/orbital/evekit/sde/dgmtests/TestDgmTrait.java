package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmTrait;

public class TestDgmTrait extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTrait> next = DgmTrait.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmTrait.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(360, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTrait> next = DgmTrait.access(0, 1000, new AttributeSelector("{values:[9]}"), all, all);
    Assert.assertEquals(1, next.size());
    DgmTrait random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.getTraitID());
    Assert.assertEquals("bonus to <a href=showinfo:3302>Small Projectile Turret</a> damage", random.getBonusText());
    Assert.assertEquals(new Integer(105), random.getUnitID());
    Assert.assertNotNull(random.toString());
  }
}
