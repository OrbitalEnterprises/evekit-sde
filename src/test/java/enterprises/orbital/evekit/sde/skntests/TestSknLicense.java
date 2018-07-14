package enterprises.orbital.evekit.sde.skntests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.skn.SknLicense;

public class TestSknLicense extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknLicense> next = SknLicense.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = SknLicense.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(7943, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<SknLicense> next = SknLicense.access(0, 1000, new AttributeSelector("{values:[34606]}"), all, all);
    Assert.assertEquals(1, next.size());
    SknLicense random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(34606, random.getLicenseTypeID());
    Assert.assertEquals(-1, random.getDuration());
    Assert.assertEquals(12, random.getSkinID());
  }

}
