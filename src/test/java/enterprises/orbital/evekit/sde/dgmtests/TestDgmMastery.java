package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmMastery;

public class TestDgmMastery extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmMastery> next = DgmMastery.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmMastery.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(342, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmMastery> next = DgmMastery.access(0, 1000, new AttributeSelector("{values:[1082]}"), all, all);
    Assert.assertEquals(1, next.size());
    DgmMastery random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1082, random.getMasteryID());
    Assert.assertEquals(64, random.getCertificateID());
    Assert.assertEquals(1, random.getGrade());
    Assert.assertNotNull(random.toString());
  }
}
