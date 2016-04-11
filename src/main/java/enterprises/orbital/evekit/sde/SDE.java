package enterprises.orbital.evekit.sde;

import enterprises.orbital.base.OrbitalProperties;
import enterprises.orbital.db.ConnectionFactory;

public class SDE {
  public static final String SDE_PU_PROP         = "enterprises.orbital.evekit.sde.persistence_unit";
  public static final String DEF_SDE_PU_PROP     = "evekit-sde";
  public static final int    DEFAULT_MAX_RESULTS = 1000;

  public static ConnectionFactory getFactory() {
    return ConnectionFactory.getFactory(OrbitalProperties.getGlobalProperty(SDE_PU_PROP, DEF_SDE_PU_PROP));
  }

}
