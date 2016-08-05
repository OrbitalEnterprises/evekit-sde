package enterprises.orbital.evekit.sde;

import java.util.logging.Level;
import java.util.logging.Logger;

import enterprises.orbital.base.OrbitalProperties;
import enterprises.orbital.db.ConnectionFactory;
import enterprises.orbital.db.ConnectionFactory.RunInTransaction;

public class SDE {
  private static final Logger log                 = Logger.getLogger(SDE.class.getName());
  public static final String  SDE_PU_PROP         = "enterprises.orbital.evekit.sde.persistence_unit";
  public static final String  DEF_SDE_PU_PROP     = "evekit-sde";
  public static final int     DEFAULT_MAX_RESULTS = 1000;

  public static ConnectionFactory getFactory() {
    return ConnectionFactory.getFactory(OrbitalProperties.getGlobalProperty(SDE_PU_PROP, DEF_SDE_PU_PROP));
  }

  public static <A> A update(
                             final A data) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<A>() {
        @Override
        public A run() throws Exception {
          return SDE.getFactory().getEntityManager().merge(data);
        }
      });
    } catch (Exception e) {
      log.log(Level.SEVERE, "query error", e);
    }
    return null;
  }

}
