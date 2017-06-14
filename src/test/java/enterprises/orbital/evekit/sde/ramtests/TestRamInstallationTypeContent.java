package enterprises.orbital.evekit.sde.ramtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ram.RamInstallationTypeContent;

public class TestRamInstallationTypeContent extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamInstallationTypeContent> next = RamInstallationTypeContent.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = RamInstallationTypeContent.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(376, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<RamInstallationTypeContent> next = RamInstallationTypeContent.access(0, 1000, new AttributeSelector("{values:[21642]}"),
                                                                              new AttributeSelector("{values:[3]}"), all);
    Assert.assertEquals(1, next.size());
    RamInstallationTypeContent random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(21642, random.id().getInstallationTypeID());
    Assert.assertEquals(3, random.id().getAssemblyLineTypeID());
    Assert.assertEquals(30, random.getQuantity());
    Assert.assertNotNull(random.toString());
  }

}
