package enterprises.orbital.evekit.sde.crttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crt.CrtSkill;

public class TestCrtSkill extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtSkill> next = CrtSkill.access(contid, maxresults, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrtSkill.access(contid, maxresults, all, all, all, all, all);
    }
    Assert.assertEquals(4190, contid);
  }

  @Test
  public void testRandomElement() {
    List<CrtSkill> next = CrtSkill.access(0, 1000, new AttributeSelector("{values:[50]}"), new AttributeSelector("{values:[3300]}"),
                                          new AttributeSelector("{values:[3]}"), new AttributeSelector("{values:[5]}"), new AttributeSelector("{any:true}"));
    Assert.assertEquals(1, next.size());
    CrtSkill random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(50, random.getCertID());
    Assert.assertEquals(3300, random.getSkillID());
    Assert.assertEquals(3, random.getCertLevelInt());
    Assert.assertEquals(5, random.getSkillLevel());
    Assert.assertEquals("advanced", random.getCertLevelText());
    Assert.assertNotNull(random.toString());
  }
}
