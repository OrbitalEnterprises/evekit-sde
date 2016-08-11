package enterprises.orbital.evekit.sde.chrtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.chr.ChrAttribute;

public class TestChrAttribute extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrAttribute> attributes = ChrAttribute.access(contid, maxresults, all, all, all, all, all, all);
    Assert.assertNotNull(attributes);
    Assert.assertEquals(5, attributes.size());
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<ChrAttribute> attributes = ChrAttribute.access(0, 1000, new AttributeSelector("{values:[4]}"), all, all, all, all, all);
    Assert.assertEquals(1, attributes.size());
    ChrAttribute random = attributes.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(4, random.getAttributeID());
    Assert.assertEquals("Memory", random.getAttributeName());
    Assert.assertEquals(
                        "<b>Memory is the mental capacity to retain and recall facts derived from prior learning experiences and apply them during situational circumstances</b>. The speed with which a pilot can recall and apply data while subject to duress is a critical component</b> of effective reflex development in combat training. In EVE, the memory attribute is a composite measure of both long and short-term memory variants, both of which apply to a broad set of abilities ranging from industrial and scientific disciplines to battlefield tactical awareness.\r\n\r\n"
                            + "<b>Memory is a primary attribute for skills such as Corporation Management and Drones</b>.\r\n\r\n"
                            + "It is also a <b>secondary attribute for</b> numerous others, including <b>Engineering, Mechanic, Electronics, and Trade.</b>",
                        random.getDescription());
    Assert.assertEquals(1381, random.getIconID());
    Assert.assertEquals("The mental capacity to retain and recall facts derived from prior learning experiences and apply them in situational circumstances.",
                        random.getShortDescription());
    Assert.assertEquals(
                        "Memory does not increase the chances of achieving success during the application of skills, only how quickly the skills that depend on it are trained.",
                        random.getNotes());
    Assert.assertNotNull(random.toString());
  }

}
