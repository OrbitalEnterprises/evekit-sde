package enterprises.orbital.evekit.sde.indtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ind.IndBlueprint;

public class TestIndBlueprint extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndBlueprint> next = IndBlueprint.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = IndBlueprint.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(4319, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndBlueprint> next = IndBlueprint.access(0, 1000, new AttributeSelector("{values:[687]}"), all);
    Assert.assertEquals(1, next.size());
    IndBlueprint random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(687, random.getTypeID());
    Assert.assertEquals(new Integer(10), random.getMaxProductionLimit());
  }

}
