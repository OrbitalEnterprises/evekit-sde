package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmTypeTrait;

public class TestDgmTypeTrait extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeTrait> next = DgmTypeTrait.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmTypeTrait.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(1886, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeTrait> next = DgmTypeTrait.access(0, 1000, new AttributeSelector("{values:[585]}"), new AttributeSelector("{values:[-1]}"),
                                                  new AttributeSelector("{values:[4]}"), all);
    Assert.assertEquals(1, next.size());
    DgmTypeTrait random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(585, random.getId().getTypeID());
    Assert.assertEquals(-1, random.getId().getParentTypeID());
    Assert.assertEquals(4, random.getId().getTraitID());
    Assert.assertEquals(80, random.getBonus(), 0.01);
    Assert.assertNotNull(random.toString());
  }
}
