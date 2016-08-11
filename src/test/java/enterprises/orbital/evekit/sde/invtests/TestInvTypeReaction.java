package enterprises.orbital.evekit.sde.invtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.inv.InvTypeReaction;

public class TestInvTypeReaction extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvTypeReaction> next = InvTypeReaction.access(contid, maxresults, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = InvTypeReaction.access(contid, maxresults, all, all, all, all);
    }
    Assert.assertEquals(372, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<InvTypeReaction> next = InvTypeReaction.access(0, 1000, new AttributeSelector("{values:[17941]}"), new AttributeSelector("{values:[1]}"),
                                                        new AttributeSelector("{values:[16647]}"), all);
    Assert.assertEquals(1, next.size());
    InvTypeReaction random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(17941, random.id().getReactionTypeID());
    Assert.assertEquals(1, random.id().getInput());
    Assert.assertEquals(16647, random.id().getTypeID());
    Assert.assertEquals(1, random.getQuantity());
    Assert.assertNotNull(random.toString());
  }
}
