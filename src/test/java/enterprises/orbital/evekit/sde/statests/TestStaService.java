package enterprises.orbital.evekit.sde.statests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.sta.StaService;

public class TestStaService extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaService> next = StaService.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = StaService.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(26, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaService> next = StaService.access(0, 1000, new AttributeSelector("{values:[64]}"), all, all);
    Assert.assertEquals(1, next.size());
    StaService random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(64, random.getServiceID());
    Assert.assertEquals("Market", random.getServiceName());
    Assert.assertNull(random.getDescription());
    Assert.assertNotNull(random.toString());
  }

}
