package enterprises.orbital.evekit.sde.crptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crp.CrpNpcDivision;

public class TestCrpNPCDivision extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcDivision> divisions = CrpNpcDivision.access(contid, maxresults, all, all, all, all);
    Assert.assertNotNull(divisions);
    Assert.assertEquals(24, divisions.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcDivision> divisions = CrpNpcDivision.access(0, 1000, new AttributeSelector("{values:[14]}"), all, all, all);
    Assert.assertEquals(1, divisions.size());
    CrpNpcDivision random = divisions.get(0);
    Assert.assertEquals(14, random.getDivisionID());
    Assert.assertEquals("Mining", random.getDivisionName());
    Assert.assertEquals("DEPRECATED DIVISION - DO NOT USE", random.getDescription());
    Assert.assertEquals("Mining Coordinator", random.getLeaderType());
    Assert.assertNotNull(random.toString());
  }
}
