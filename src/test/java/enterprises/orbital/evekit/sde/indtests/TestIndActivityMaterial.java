package enterprises.orbital.evekit.sde.indtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.ind.IndActivityMaterial;

public class TestIndActivityMaterial extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivityMaterial> next = IndActivityMaterial.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = IndActivityMaterial.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(31953, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<IndActivityMaterial> next = IndActivityMaterial.access(0, 1000, new AttributeSelector("{values:[41228]}"), new AttributeSelector("{values:[1]}"),
                                                                new AttributeSelector("{values:[11486]}"), all);
    Assert.assertEquals(1, next.size());
    IndActivityMaterial random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(41228, random.getTypeID());
    Assert.assertEquals(1, random.getActivityID());
    Assert.assertEquals(11486, random.getMaterialTypeID());
    Assert.assertEquals(3, random.getQuantity());
    Assert.assertNotNull(random.toString());
  }
}
