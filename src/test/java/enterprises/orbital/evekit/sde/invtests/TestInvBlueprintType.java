package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvBlueprintType;

public class TestInvBlueprintType extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvBlueprintType> next = InvBlueprintType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvBlueprintType.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(3890, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvBlueprintType> next = InvBlueprintType.access(0, 1000, new AttributeSelector("{values:[687]}"), all, all, all, all, all, all, all, all, all, all,
                                                          all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    InvBlueprintType random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(687, random.getBlueprintTypeID());
    Assert.assertNull(random.getDuplicatingTime());
    Assert.assertEquals(new Integer(128100), random.getInventionTime());
    Assert.assertNull(random.getMaterialModifier());
    Assert.assertEquals(new Integer(10), random.getMaxProductionLimit());
    Assert.assertNull(random.getParentBlueprintTypeID());
    Assert.assertEquals(new Integer(12000), random.getProductionTime());
    Assert.assertNull(random.getProductivityModifier());
    Assert.assertEquals(new Integer(621), random.getProductTypeID());
    Assert.assertEquals(new Integer(9600), random.getResearchCopyTime());
    Assert.assertEquals(new Integer(4200), random.getResearchMaterialTime());
    Assert.assertEquals(new Integer(4200), random.getResearchProductivityTime());
    Assert.assertNull(random.getResearchTechTime());
    Assert.assertNull(random.getReverseEngineeringTime());
    Assert.assertNull(random.getTechLevel());
    Assert.assertNull(random.getWasteFactor());
  }

}
