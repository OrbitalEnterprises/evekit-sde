package enterprises.orbital.evekit.sde.chrtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.chr.ChrRace;

public class TestChrRace extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrRace> races = ChrRace.access(contid, maxresults, all, all, all, all, all);
    Assert.assertNotNull(races);
    Assert.assertEquals(10, races.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrRace> races = ChrRace.access(0, 1000, new AttributeSelector("{values:[8]}"), all, all, all, all);
    Assert.assertEquals(1, races.size());
    ChrRace random = races.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(8, random.getRaceID());
    Assert.assertEquals("Gallente", random.getRaceName());
    Assert.assertEquals(
                        "Champions of liberty and defenders of the downtrodden, the Gallente play host to the only true democracy in New Eden. Some of the most progressive leaders, scientists, and businessmen of the era have emerged from its diverse peoples. A pioneer of artificial intelligence, the Federation relies heavily on drones and other automated systems. This is not to detract from the skill of their pilots, though: the Gallente Federation is known for producing some of the best and bravest the universe has to offer.",
                        random.getDescription());
    Assert.assertEquals(new Integer(1441), random.getIconID());
    Assert.assertEquals("Championing freedom and liberty across the universe, the Gallente Federation is the only true democracy of New Eden.",
                        random.getShortDescription());
  }

}
