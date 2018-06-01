package enterprises.orbital.evekit.sde.chrtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.chr.ChrBloodline;

public class TestChrBloodline extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrBloodline> factions = ChrBloodline.access(contid, maxresults, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all, all);
    Assert.assertNotNull(factions);
    Assert.assertEquals(18, factions.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrBloodline> factions = ChrBloodline.access(0, 1000, new AttributeSelector("{values:[4]}"), all, all, all, all, all, all, all, all, all, all, all,
                                                      all, all, all, all, all);
    Assert.assertEquals(1, factions.size());
    ChrBloodline random = factions.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(4, random.getBloodlineID());
    Assert.assertEquals("Brutor", random.getBloodlineName());
    Assert.assertEquals(2, random.getRaceID());
    Assert.assertEquals(
                        "A martial, strong-willed people, the Brutor hold their tribal heritage close to their hearts, and are renowned for living regimented, disciplined lives. Despite presenting a tough, no-nonsense exterior, they are deeply introspective, aware of even the smallest detail at all times. Immersed in ancient martial traditions that begin at childhood, they are physically robust individuals and intimidating to face in the flesh.",
                        random.getDescription());
    Assert.assertEquals(
                        "Male Brutors present a stoic front, but are patient, vigilant, and determined. They are keenly aware of their surroundings—physically, and socially—at all times. Despite their gruff demeanor, they are passionate individuals, and are capable of pursuing any task with relentless focus and determination.",
                        random.getMaleDescription());
    Assert.assertEquals(
                        "Brutor females are in many ways the opposite of male Brutors. They are dominant, persistent, and egocentric. When they set their mind on something, they usually succeed. Between their wits, grace, and beauty, they are masters of manipulation.",
                        random.getFemaleDescription());
    Assert.assertEquals(588, random.getShipTypeID());
    Assert.assertEquals(1000049, random.getCorporationID());
    Assert.assertEquals(9, random.getPerception());
    Assert.assertEquals(7, random.getWillpower());
    Assert.assertEquals(6, random.getCharisma());
    Assert.assertEquals(4, random.getMemory());
    Assert.assertEquals(4, random.getIntelligence());
    Assert.assertEquals(1635, random.getIconID().intValue());
    Assert.assertEquals("The Brutor are a martial people who live regimented, disciplined lives.", random.getShortDescription());
    Assert.assertEquals("Male Brutors are vigilant and determined, and possess great situational awareness.", random.getShortMaleDescription());
    Assert.assertEquals("Tenacious and domineering, the Brutor female is accustomed to getting what she wants.", random.getShortFemaleDescription());
    Assert.assertNotNull(random.toString());
  }
}
