package enterprises.orbital.evekit.sde;

import java.io.IOException;

import org.junit.Assert;

import enterprises.orbital.base.OrbitalProperties;

public abstract class TestSetup {

  static {
    try {
      OrbitalProperties.addPropertyFile("Test.properties");
    } catch (IOException e) {
      Assert.assertTrue(false);
    }
  }
}
