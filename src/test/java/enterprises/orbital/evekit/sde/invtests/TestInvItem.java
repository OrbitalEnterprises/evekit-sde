package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvItem;

public class TestInvItem extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvItem> next = InvItem.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvItem.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(531743, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvItem> next = InvItem.access(0, 1000, new AttributeSelector("{values:[9]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvItem random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(9, random.getItemID());
    Assert.assertEquals(0, random.getTypeID());
    Assert.assertEquals(1, random.getOwnerID());
    Assert.assertEquals(0, random.getLocationID());
    Assert.assertEquals(0, random.getFlagID());
    Assert.assertEquals(-1, random.getQuantity());
    Assert.assertNotNull(random.toString());
  }
}
