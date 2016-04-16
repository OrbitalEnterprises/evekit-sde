package enterprises.orbital.evekit.sde.wartests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.war.WarCombatZoneSystem;

public class TestWarCombatZoneSystem extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<WarCombatZoneSystem> next = WarCombatZoneSystem.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = WarCombatZoneSystem.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(171, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<WarCombatZoneSystem> next = WarCombatZoneSystem.access(0, 1000, new AttributeSelector("{values:[30002065]}"), all);
    Assert.assertEquals(1, next.size());
    WarCombatZoneSystem random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(30002065, random.getSolarSystemID());
    Assert.assertEquals(6, random.getCombatZoneID());
    Assert.assertNotNull(random.toString());
  }

}
