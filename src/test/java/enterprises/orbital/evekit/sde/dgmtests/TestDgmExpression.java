package enterprises.orbital.evekit.sde.dgmtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.dgm.DgmExpression;

public class TestDgmExpression extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmExpression> next = DgmExpression.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = DgmExpression.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(18136, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<DgmExpression> next = DgmExpression.access(0, 1000, new AttributeSelector("{values:[3552]}"), all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    DgmExpression random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(3552, random.getExpressionID());
    Assert.assertEquals(58, random.getOperandID());
    Assert.assertEquals(new Integer(1709), random.getArg1());
    Assert.assertEquals(new Integer(2806), random.getArg2());
    Assert.assertNull(random.getExpressionValue());
    Assert.assertEquals("Autogenerated RIM, Ship.capacity.RIM(shipBonusMI,PostPercent)", random.getDescription());
    Assert.assertEquals("((CurrentShip->capacity).(PostPercent)).RemoveItemModifier (shipBonusMI)", random.getExpressionName());
    Assert.assertNull(random.getExpressionTypeID());
    Assert.assertNull(random.getExpressionGroupID());
    Assert.assertNull(random.getExpressionAttributeID());
    Assert.assertNotNull(random.toString());
  }
}
