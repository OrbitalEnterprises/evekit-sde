package enterprises.orbital.evekit.sde.chrtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.chr.ChrAncestry;

public class TestChrAncestry extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrAncestry> factions = ChrAncestry.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertNotNull(factions);
    Assert.assertEquals(42, factions.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrAncestry> results = ChrAncestry.access(0, 1000, new AttributeSelector("{values:[8]}"), all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, results.size());
    ChrAncestry random = results.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(8, random.getAncestryID());
    Assert.assertEquals("Mercs", random.getAncestryName());
    Assert.assertEquals(2, random.getBloodlineID());
    Assert.assertEquals(
                        "Many Civire have a deep fascination with the brutality of battle. For these brazen individuals, the promise of steady combat is what drives the eager sale of their own services to the highest bidder. Some even hail from families who have followed the mercenary tradition for generations, albeit within the shadows of society.",
                        random.getDescription());
    Assert.assertEquals(0, random.getPerception());
    Assert.assertEquals(4, random.getWillpower());
    Assert.assertEquals(0, random.getCharisma());
    Assert.assertEquals(0, random.getMemory());
    Assert.assertEquals(0, random.getIntelligence());
    Assert.assertEquals(new Integer(1648), random.getIconID());
    Assert.assertEquals("Guns for hire that are always available to the highest bidder.", random.getShortDescription());
    Assert.assertNotNull(random.toString());
  }

}
