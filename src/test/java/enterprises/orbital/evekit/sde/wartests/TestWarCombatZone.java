package enterprises.orbital.evekit.sde.wartests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.war.WarCombatZone;

public class TestWarCombatZone extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<WarCombatZone> next = WarCombatZone.access(contid, maxresults, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = WarCombatZone.access(contid, maxresults, all, all, all, all, all);
    }
    Assert.assertEquals(4, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<WarCombatZone> next = WarCombatZone.access(0, 1000, new AttributeSelector("{values:[5]}"), all, all, all, all);
    Assert.assertEquals(1, next.size());
    WarCombatZone random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(5, random.getCombatZoneID());
    Assert.assertEquals("FED", random.getCombatZoneName());
    Assert.assertEquals(500004, random.getFactionID());
    Assert.assertEquals(30003837, random.getCenterSystemID());
    Assert.assertEquals("FED description", random.getDescription());
    Assert.assertNotNull(random.toString());
  }

}
