package enterprises.orbital.evekit.sde.crttests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.TestSetup;
import enterprises.orbital.evekit.sde.crt.CrtCertificate;

public class TestCrtCertificate extends TestSetup {

  @Test
  public void testTableCount() {
    int maxresults = 1000;
    int contid = 0;
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtCertificate> next = CrtCertificate.access(contid, maxresults, all, all, all, all, all, all, all);
    while (!next.isEmpty()) {
      contid += next.size();
      next = CrtCertificate.access(contid, maxresults, all, all, all, all, all, all, all);
    }
    Assert.assertEquals(124, contid);
  }

  @Test
  public void testRandomElement() {
    AttributeSelector all = new AttributeSelector("{any:true}");
    List<CrtCertificate> next = CrtCertificate.access(0, 1000, new AttributeSelector("{values:[69]}"), all, all, all, all, all, all);
    Assert.assertEquals(1, next.size());
    CrtCertificate random = next.get(0);
    Assert.assertNotNull(random);
    Assert.assertEquals(69, random.getCertificateID());
    Assert.assertEquals(7, random.getClassID());
    Assert.assertEquals(null, random.getCorpID());
    Assert.assertEquals(
                        "This certificate represents a level of competence in handling large hybrid turrets. The holder has learned that blasters are extreme close range weapons, while railguns are their counterpart at very long range, and that both use hybrid charges as ammunition. This is a good skillset for capsuleers specializing in medium to large Caldari or Gallente vessels based on the Battlecruiser and Battleship hulls.",
                        random.getDescription());
    Assert.assertEquals(null, random.getGrade());
    Assert.assertEquals(255, random.getGroupID());
    Assert.assertEquals(null, random.getIconID());
    Assert.assertNotNull(random.toString());
  }
}
