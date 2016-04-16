package enterprises.orbital.evekit.sde.plttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.plt.PltSchematic;

public class TestPltSchematic extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<PltSchematic> next = PltSchematic.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = PltSchematic.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(68, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<PltSchematic> next = PltSchematic.access(0, 1000, new AttributeSelector("{values:[71]}"), all, all);
    Assert.assertEquals(1, next.size());
    PltSchematic random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(71, random.getSchematicID());
    Assert.assertEquals("Transmitter", random.getSchematicName());
    Assert.assertEquals(3600, random.getCycleTime());
    Assert.assertNotNull(random.toString());
  }

}
