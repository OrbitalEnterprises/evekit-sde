package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvMetaType;

public class TestInvMetaType extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvMetaType> next = InvMetaType.access(contid, maxresults, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvMetaType.access(contid, maxresults, all, all, all);
    }
    Assert.assertEquals(4590, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvMetaType> next = InvMetaType.access(0, 1000, new AttributeSelector("{values:[448]}"), all, all);
    Assert.assertEquals(1, next.size());
    InvMetaType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(448, random.getTypeID());
    Assert.assertEquals(447, random.getParentTypeID());
    Assert.assertEquals(2, random.getMetaGroupID());
    Assert.assertNotNull(random.toString());
  }
}
