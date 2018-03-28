package enterprises.orbital.evekit.sde.indtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ind.IndActivityProduct;

public class TestIndActivityProduct extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivityProduct> next = IndActivityProduct.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = IndActivityProduct.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(5485, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivityProduct> next = IndActivityProduct.access(0, 1000, new AttributeSelector("{values:[41376]}"), new AttributeSelector("{values:[8]}"),
                                                              new AttributeSelector("{values:[41377]}"), all);
    Assert.assertEquals(1, next.size());
    IndActivityProduct random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(41376, random.getTypeID());
    Assert.assertEquals(8, random.getActivityID());
    Assert.assertEquals(41377, random.getProductTypeID());
    Assert.assertEquals(10, random.getQuantity());
    Assert.assertNotNull(random.toString());
  }
}
