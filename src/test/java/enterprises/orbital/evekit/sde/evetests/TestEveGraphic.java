package enterprises.orbital.evekit.sde.evetests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.eve.EveGraphic;

public class TestEveGraphic extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<EveGraphic> next = EveGraphic.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = EveGraphic.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(3619, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<EveGraphic> next = EveGraphic.access(0, 1000, new AttributeSelector("{values:[43]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    EveGraphic random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(43, random.getGraphicID());
    Assert.assertEquals("caldaribase", random.getSofFactionName());
    Assert.assertEquals("", random.getGraphicFile());
    Assert.assertEquals("cb1_t1", random.getSofHullName());
    Assert.assertEquals("caldari", random.getSofRaceName());
    Assert.assertEquals("Caldari Battleship Raven", random.getDescription());
    Assert.assertNotNull(random.toString());
  }

}
