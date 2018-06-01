package enterprises.orbital.evekit.sde.chrtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.chr.ChrFaction;

public class TestChrFaction extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrFaction> factions = ChrFaction.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertNotNull(factions);
    Assert.assertEquals(24, factions.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrFaction> factions = ChrFaction.access(0, 1000, new AttributeSelector("{values:[500004]}"), all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, factions.size());
    ChrFaction random = factions.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(500004, random.getFactionID());
    Assert.assertEquals("Gallente Federation", random.getFactionName());
    Assert.assertEquals(
                        "The Gallente Federation encompasses several races, the Gallenteans the largest by far. The Federation is democratic and very liberal in a world full of dictators and oligarchies. The Caldari State was once part of the Federation, but a severe dispute resulted in their departure and a long war between the Gallente Federation and the Caldari State. The Gallenteans are the masters of pleasure and entertainment and their rich trade empire has given the world many of its most glorious and extravagant sights.  ",
                        random.getDescription());
    Assert.assertEquals(8, random.getRaceIDs());
    Assert.assertEquals(30004993, random.getSolarSystemID());
    Assert.assertEquals(1000120, random.getCorporationID());
    Assert.assertEquals(5.0, random.getSizeFactor(), 0.00001);
    Assert.assertEquals(1051, random.getStationCount());
    Assert.assertEquals(507, random.getStationSystemCount());
    Assert.assertEquals(new Integer(1000181), random.getMilitiaCorporationID());
    Assert.assertEquals(1441, random.getIconID());
    Assert.assertNotNull(random.toString());
  }

}
