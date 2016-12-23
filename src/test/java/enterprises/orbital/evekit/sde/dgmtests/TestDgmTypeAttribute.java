package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmTypeAttribute;

public class TestDgmTypeAttribute extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeAttribute> next = DgmTypeAttribute.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmTypeAttribute.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(175286, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmTypeAttribute> next = DgmTypeAttribute.access(0, 1000, new AttributeSelector("{values:[21]}"), new AttributeSelector("{values:[790]}"), all, all);
    Assert.assertEquals(1, next.size());
    DgmTypeAttribute random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(21, random.id().getTypeID());
    Assert.assertEquals(790, random.id().getAttributeID());
    Assert.assertEquals(new Integer(12185), random.getValueInt());
    Assert.assertNull(random.getValueFloat());
    Assert.assertNotNull(random.toString());
  }
}
