package enterprises.orbital.evekit.sde.crptests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporation;

public class TestCrpNPCCorporation extends TestSetup {
  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcCorporation> next = CrpNpcCorporation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                                            all, all, all, all, all, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrpNpcCorporation.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all,
                                      all, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(234, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrpNpcCorporation> next = CrpNpcCorporation.access(0, 1000, new AttributeSelector("{values:[1000020]}"), all, all, all, all, all, all, all, all, all,
                                                            all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    CrpNpcCorporation random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(1000020, random.getCorporationID());
    Assert.assertEquals("L", random.getSize());
    Assert.assertEquals("N", random.getExtent());
    Assert.assertEquals(30001404, random.getSolarSystemID());
    Assert.assertEquals(null, random.getInvestorID1());
    Assert.assertEquals(35, random.getInvestorShares1());
    Assert.assertEquals(null, random.getInvestorID2());
    Assert.assertEquals(33, random.getInvestorShares2());
    Assert.assertEquals(new Integer(1000020), random.getInvestorID3());
    Assert.assertEquals(17, random.getInvestorShares3());
    Assert.assertEquals(new Integer(1000028), random.getInvestorID4());
    Assert.assertEquals(15, random.getInvestorShares4());
    Assert.assertEquals(new Integer(1000037), random.getFriendID());
    Assert.assertEquals(new Integer(1000019), random.getEnemyID());
    Assert.assertEquals(0L, random.getPublicShares());
    Assert.assertEquals(59, random.getInitialPrice());
    Assert.assertEquals(0.0, random.getMinSecurity(), 0.0001);
    Assert.assertEquals(0, random.getScattered());
    Assert.assertEquals(4, random.getFringe());
    Assert.assertEquals(3, random.getCorridor());
    Assert.assertEquals(2, random.getHub());
    Assert.assertEquals(3, random.getBorder());
    Assert.assertEquals(500001, random.getFactionID());
    Assert.assertEquals(1.75, random.getSizeFactor(), 0.0001);
    Assert.assertEquals(new Short((short) 41), random.getStationCount());
    Assert.assertEquals(new Short((short) 33), random.getStationSystemCount());
    Assert.assertEquals(
                        "Lai Dai has always advocated quality over quantity and their products have always been of the highest standard. Lai Dai has an energetic research program and rivals the great Ishukone in technological achievements.",
                        random.getDescription());
    Assert.assertEquals(new Integer(1598), random.getIconID());
    Assert.assertNotNull(random.toString());
  }
}
