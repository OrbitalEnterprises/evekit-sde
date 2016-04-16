package enterprises.orbital.evekit.sde.agttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.agt.AgtAgentType;

public class TestAgtAgentType extends TestSetup {
  @Test
  public void testTableCount() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<AgtAgentType> agenttypes = AgtAgentType.access(0, 1000, all, all);
    Assert.assertNotNull(agenttypes);
    Assert.assertEquals(12, agenttypes.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<AgtAgentType> agenttypes = AgtAgentType.access(0, 1000, new AttributeSelector("{values:[7]}"), all);
    Assert.assertEquals(1, agenttypes.size());
    AgtAgentType random = agenttypes.get(0);
    Assert.assertEquals(7, random.getAgentTypeID());
    Assert.assertEquals("StorylineMissionAgent", random.getAgentType());
    Assert.assertNotNull(random.toString());
  }
}
