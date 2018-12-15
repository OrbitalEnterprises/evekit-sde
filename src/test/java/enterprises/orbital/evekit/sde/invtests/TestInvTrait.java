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
    Assert.assertEquals(2738, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvTrait> next = InvTrait.access(0, 1000, new AttributeSelector("{values:[560]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvTrait random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(560, random.getTraitID());
    Assert.assertEquals(12003, random.getTypeID());
    Assert.assertEquals(-1, random.getSkillID());
    Assert.assertNull(random.getBonus());
    Assert.assertEquals("Can fit <a href=showinfo:47254>Assault Damage Controls</a>", random.getBonusText());
    Assert.assertNull(random.getUnitID());
    Assert.assertNotNull(random.toString());
  }

}
