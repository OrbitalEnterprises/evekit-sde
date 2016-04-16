package enterprises.orbital.evekit.sde.crptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporationDivision;

public class TestCrpNPCCorporationDivision extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcCorporationDivision> next = CrpNpcCorporationDivision.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrpNpcCorporationDivision.access(contid, maxresults, all, all, all);
    }
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcCorporationDivision> next = CrpNpcCorporationDivision.access(0, 1000, new AttributeSelector("{values:[1000003]}"),
                                                                            new AttributeSelector("{values:[22]}"), all);
    Assert.assertEquals(1, next.size());
    CrpNpcCorporationDivision random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1000003, random.getId().getCorporationID());
    Assert.assertEquals(22, random.getId().getDivisionID());
    Assert.assertEquals(100, random.getSize());
    Assert.assertNotNull(random.toString());
  }
}
