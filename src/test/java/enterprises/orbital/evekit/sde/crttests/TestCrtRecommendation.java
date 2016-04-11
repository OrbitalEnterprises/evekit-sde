package enterprises.orbital.evekit.sde.crttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crt.CrtRecommendation;

public class TestCrtRecommendation extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtRecommendation> next = CrtRecommendation.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrtRecommendation.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(3176, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtRecommendation> next = CrtRecommendation.access(0, 1000, new AttributeSelector("{values:[7]}"), all, all, all);
    Assert.assertEquals(1, next.size());
    CrtRecommendation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(7, random.getRecommendationID());
    Assert.assertEquals(11184, random.getShipTypeID());
    Assert.assertEquals(50, random.getCertificateID());
    Assert.assertEquals(0, random.getRecommendationLevel());
    Assert.assertNotNull(random.toString());
  }
}
