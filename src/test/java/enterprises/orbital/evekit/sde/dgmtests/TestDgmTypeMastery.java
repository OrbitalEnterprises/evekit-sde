package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmTypeMastery;

public class TestDgmTypeMastery extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeMastery> next = DgmTypeMastery.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmTypeMastery.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(13969, contid);
  }

  @Test
  public void testRandomElement() {
    List<DgmTypeMastery> next = DgmTypeMastery.access(0, 1000, new AttributeSelector("{values:[582]}"), new AttributeSelector("{values:[7]}"));
    Assert.assertEquals(1, next.size());
    DgmTypeMastery random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(582, random.getId().getTypeID());
    Assert.assertEquals(7, random.getId().getMasteryID());
    Assert.assertNotNull(random.toString());
  }
}
