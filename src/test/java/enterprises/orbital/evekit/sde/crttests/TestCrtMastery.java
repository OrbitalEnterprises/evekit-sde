package enterprises.orbital.evekit.sde.crttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crt.CrtMastery;

public class TestCrtMastery extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtMastery> next = CrtMastery.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrtMastery.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(14183, contid);
  }

  @Test
  public void testRandomElement() {
    List<CrtMastery> next = CrtMastery.access(0, 1000, new AttributeSelector("{values:[590]}"), new AttributeSelector("{values:[1]}"),
                                              new AttributeSelector("{values:[89]}"));
    Assert.assertEquals(1, next.size());
    CrtMastery random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(590, random.getTypeID());
    Assert.assertEquals(1, random.getMasteryLevel());
    Assert.assertEquals(89, random.getCertID());
    Assert.assertNotNull(random.toString());
  }
}
