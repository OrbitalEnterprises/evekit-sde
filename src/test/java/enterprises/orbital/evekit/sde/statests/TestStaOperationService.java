package enterprises.orbital.evekit.sde.statests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.sta.StaOperationService;

public class TestStaOperationService extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<StaOperationService> next = StaOperationService.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = StaOperationService.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(795, contid);
  }

  @Test
  public void testRandomElement() {
    List<StaOperationService> next = StaOperationService.access(0, 1000, new AttributeSelector("{values:[1]}"), new AttributeSelector("{values:[16777216]}"));
    Assert.assertEquals(1, next.size());
    StaOperationService random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1, random.getId().getOperationID());
    Assert.assertEquals(16777216, random.getId().getServiceID());
    Assert.assertNotNull(random.toString());
  }

}
