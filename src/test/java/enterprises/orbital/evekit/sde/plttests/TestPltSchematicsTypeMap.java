package enterprises.orbital.evekit.sde.plttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.plt.PltSchematicsTypeMap;

public class TestPltSchematicsTypeMap extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<PltSchematicsTypeMap> next = PltSchematicsTypeMap.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = PltSchematicsTypeMap.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(203, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<PltSchematicsTypeMap> next = PltSchematicsTypeMap.access(0, 1000, new AttributeSelector("{values:[71]}"), new AttributeSelector("{values:[2389]}"),
                                                                  all, all);
    Assert.assertEquals(1, next.size());
    PltSchematicsTypeMap random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(71, random.id().getSchematicID());
    Assert.assertEquals(2389, random.id().getTypeID());
    Assert.assertEquals(40, random.getQuantity());
    Assert.assertTrue(random.isInput());
    Assert.assertNotNull(random.toString());
  }

}
