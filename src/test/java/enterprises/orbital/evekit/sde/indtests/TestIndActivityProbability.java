package enterprises.orbital.evekit.sde.indtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ind.IndActivityProbability;

public class TestIndActivityProbability extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivityProbability> next = IndActivityProbability.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = IndActivityProbability.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(1273, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivityProbability> next = IndActivityProbability.access(0, 1000, new AttributeSelector("{values:[30586]}"), new AttributeSelector("{values:[8]}"),
                                                                      new AttributeSelector("{values:[45717]}"), all);
    Assert.assertEquals(1, next.size());
    IndActivityProbability random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(30586, random.getTypeID());
    Assert.assertEquals(8, random.getActivityID());
    Assert.assertEquals(45717, random.getProductTypeID());
    Assert.assertEquals(0.21, random.getProbability(), 0.0001);
    Assert.assertNotNull(random.toString());
  }
}
