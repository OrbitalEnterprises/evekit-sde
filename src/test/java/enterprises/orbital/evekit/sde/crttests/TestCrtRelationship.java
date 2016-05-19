package enterprises.orbital.evekit.sde.crttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crt.CrtRelationship;

public class TestCrtRelationship extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtRelationship> next = CrtRelationship.access(contid, maxresults, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrtRelationship.access(contid, maxresults, all, all, all, all, all, all);
    }
    Assert.assertEquals(4050, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtRelationship> next = CrtRelationship.access(0, 1000, new AttributeSelector("{values:[8]}"), all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    CrtRelationship random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(8, random.getRelationshipID());
    Assert.assertEquals(50, random.getChildID());
    Assert.assertEquals(5, random.getGrade());
    Assert.assertEquals(null, random.getParentID());
    Assert.assertEquals(5, random.getParentLevel());
    Assert.assertEquals(3303, random.getParentTypeID());
    Assert.assertNotNull(random.toString());
  }
}
