package enterprises.orbital.evekit.sde.agttests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.agt.AgtAgent;

public class TestAgtAgent extends TestSetup {
  @Test
  public void testTableCount() {
    List<AgtAgent> fullList = new ArrayList<AgtAgent>();
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<AgtAgent> next = AgtAgent.access(contid, maxresults, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      fullList.addAll(next);
      contid += next.size();
      next = AgtAgent.access(contid, maxresults, all, all, all, all, all, all, all, all);
    }
    Assert.assertTrue(!fullList.isEmpty());
    Assert.assertEquals(10975, fullList.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    AttributeSelector id = new AttributeSelector("{values: [3008423]}");
    List<AgtAgent> next = AgtAgent.access(0, 1000, id, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    AgtAgent random = next.get(0);
    Assert.assertEquals(3008423, random.getAgentID());
    Assert.assertEquals(22, random.getDivisionID());
    Assert.assertEquals(1000002, random.getCorporationID());
    Assert.assertEquals(60000187, random.getLocationID());
    Assert.assertEquals(3, random.getLevel());
    Assert.assertEquals(20, random.getQuality());
    Assert.assertEquals(2, random.getAgentTypeID());
    Assert.assertNotNull(random.toString());
  }
}
