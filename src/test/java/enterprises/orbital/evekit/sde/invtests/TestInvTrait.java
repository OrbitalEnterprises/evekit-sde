package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvTrait;

public class TestInvTrait extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvTrait> next = InvTrait.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvTrait.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(2043, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    // '560', '12011', '16591', '5', 'bonus to <a href=showinfo:3304>Medium Hybrid Turret</a> damage', '105'
    List<InvTrait> next = InvTrait.access(0, 1000, new AttributeSelector("{values:[560]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvTrait random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(560, random.getTraitID());
    Assert.assertEquals(12013, random.getTypeID());
    Assert.assertEquals(28609, random.getSkillID());
    Assert.assertEquals(5, random.getBonus(), 0.001);
    Assert.assertEquals("bonus to <a href=showinfo:28654>Warp Disruption Field Generator</a> scramble range", random.getBonusText());
    Assert.assertEquals(105, random.getUnitID().intValue());
    Assert.assertNotNull(random.toString());
  }

}
