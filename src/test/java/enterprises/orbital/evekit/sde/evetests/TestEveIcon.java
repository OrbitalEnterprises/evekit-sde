package enterprises.orbital.evekit.sde.evetests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.eve.EveIcon;

public class TestEveIcon extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<EveIcon> next = EveIcon.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = EveIcon.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(2389, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<EveIcon> next = EveIcon.access(0, 1000, new AttributeSelector("{values:[2207]}"), all, all);
    Assert.assertEquals(1, next.size());
    EveIcon random = next.get(0);
    Assert.assertEquals(2207, random.getIconID());
    Assert.assertEquals("res:/ui/texture/icons/39_64_15.png", random.getIconFile());
    Assert.assertEquals("Kador Banner of Royality - Unknown", random.getDescription());
    Assert.assertNotNull(random.toString());
  }
}
