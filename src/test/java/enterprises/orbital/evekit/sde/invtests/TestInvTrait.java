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
    Assert.assertEquals(2608, contid);
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
    Assert.assertEquals(50, random.getBonus(), 0.001);
    Assert.assertEquals("reduction in <a href=showinfo:3454>Microwarpdrive</a> signature radius penalty", random.getBonusText());
    Assert.assertEquals(105, random.getUnitID().intValue());
    Assert.assertNotNull(random.toString());
  }

}
