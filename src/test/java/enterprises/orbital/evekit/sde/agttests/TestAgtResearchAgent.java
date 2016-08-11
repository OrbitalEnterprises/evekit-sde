package enterprises.orbital.evekit.sde.agttests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.agt.AgtResearchAgent;

public class TestAgtResearchAgent extends TestSetup {
  @Test
  public void testTableCount() {
    List<AgtResearchAgent> fullList = new ArrayList<AgtResearchAgent>();
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<AgtResearchAgent> next = AgtResearchAgent.access(contid, maxresults, all, all);
    while (!next.isEmpty()) {
      fullList.addAll(next);
      contid += next.size();
      next = AgtResearchAgent.access(contid, maxresults, all, all);
    }
    Assert.assertEquals(797, fullList.size());
  }

  @Test
  public void testRandomElement() {
    List<AgtResearchAgent> next = AgtResearchAgent.access(0, 1000, new AttributeSelector("{values:[3009317]}"), new AttributeSelector("{values:[11453]}"));
    Assert.assertEquals(1, next.size());
    AgtResearchAgent random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(3009317, random.getAgentID());
    Assert.assertEquals(11453, random.getTypeID());
    Assert.assertNotNull(random.toString());
  }
}
