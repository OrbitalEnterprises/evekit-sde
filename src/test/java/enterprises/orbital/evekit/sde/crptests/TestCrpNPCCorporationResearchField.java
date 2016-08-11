package enterprises.orbital.evekit.sde.crptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporationResearchField;

public class TestCrpNPCCorporationResearchField extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcCorporationResearchField> divisions = CrpNpcCorporationResearchField.access(contid, maxresults, all, all);
    Assert.assertNotNull(divisions);
    Assert.assertTrue(divisions.size() == 48);
  }

  @Test
  public void testRandomElement() {
    List<CrpNpcCorporationResearchField> divisions = CrpNpcCorporationResearchField.access(0, 1000, new AttributeSelector("{values:[11443]}"),
                                                                                           new AttributeSelector("{values:[1000019]}"));
    Assert.assertEquals(1, divisions.size());
    CrpNpcCorporationResearchField random = divisions.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(11443, random.id().getSkillID());
    Assert.assertEquals(1000019, random.id().getCorporationID());
    Assert.assertNotNull(random.toString());
  }
}
