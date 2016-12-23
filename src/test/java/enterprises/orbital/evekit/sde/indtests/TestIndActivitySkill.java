package enterprises.orbital.evekit.sde.indtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ind.IndActivitySkill;

public class TestIndActivitySkill extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivitySkill> next = IndActivitySkill.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = IndActivitySkill.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(19495, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivitySkill> next = IndActivitySkill.access(0, 1000, new AttributeSelector("{values:[33273]}"), new AttributeSelector("{values:[5]}"),
                                                          new AttributeSelector("{values:[11449]}"), all);
    Assert.assertEquals(1, next.size());
    IndActivitySkill random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(33273, random.getTypeID());
    Assert.assertEquals(5, random.getActivityID());
    Assert.assertEquals(11449, random.getSkillID());
    Assert.assertEquals(1, random.getLevel());
    Assert.assertNotNull(random.toString());
  }
}
