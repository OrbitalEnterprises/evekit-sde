package enterprises.orbital.evekit.sde.evetests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.eve.EveUnit;

public class TestEveUnit extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<EveUnit> next = EveUnit.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = EveUnit.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(57, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<EveUnit> next = EveUnit.access(0, 1000, new AttributeSelector("{values:[6]}"), all, all, all);
    Assert.assertEquals(1, next.size());
    EveUnit random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(6, random.getUnitID());
    Assert.assertEquals("Amount Of Substance", random.getUnitName());
    Assert.assertEquals("mol", random.getDisplayName());
    Assert.assertEquals("Mole", random.getDescription());
    Assert.assertNotNull(random.toString());
  }
}
