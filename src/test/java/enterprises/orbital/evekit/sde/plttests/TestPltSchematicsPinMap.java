package enterprises.orbital.evekit.sde.plttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.plt.PltSchematicsPinMap;

public class TestPltSchematicsPinMap extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<PltSchematicsPinMap> next = PltSchematicsPinMap.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = PltSchematicsPinMap.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(496, contid);
  }

  @Test
  public void testRandomElement() {
    List<PltSchematicsPinMap> next = PltSchematicsPinMap.access(0, 1000, new AttributeSelector("{values:[67]}"), new AttributeSelector("{values:[2480]}"));
    Assert.assertEquals(1, next.size());
    PltSchematicsPinMap random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(67, random.id().getSchematicID());
    Assert.assertEquals(2480, random.id().getPinTypeID());
    Assert.assertNotNull(random.toString());
  }

}
