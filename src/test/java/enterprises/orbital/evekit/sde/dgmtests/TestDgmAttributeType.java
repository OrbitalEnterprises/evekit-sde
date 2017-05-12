package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmAttributeType;

public class TestDgmAttributeType extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmAttributeType> next = DgmAttributeType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmAttributeType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(2316, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmAttributeType> next = DgmAttributeType.access(0, 1000, new AttributeSelector("{values:[12]}"), all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    DgmAttributeType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(12, random.getAttributeID());
    Assert.assertEquals("lowSlots", random.getAttributeName());
    Assert.assertEquals("The number of low power slots on the ship.", random.getDescription());
    Assert.assertEquals(new Integer(295), random.getIconID());
    Assert.assertEquals(0.0, random.getDefaultValue(), 0.0001);
    Assert.assertEquals(1, random.getPublished());
    Assert.assertEquals("Low Slots", random.getDisplayName());
    Assert.assertEquals(new Integer(122), random.getUnitID());
    Assert.assertEquals(1, random.getStackable());
    Assert.assertEquals(1, random.getHighIsGood());
    Assert.assertEquals(new Byte((byte) 1), random.getCategoryID());
    Assert.assertNotNull(random.toString());
  }
}
