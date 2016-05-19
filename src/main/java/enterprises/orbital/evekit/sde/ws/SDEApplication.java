package enterprises.orbital.evekit.sde.ws;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import enterprises.orbital.base.OrbitalProperties;
import enterprises.orbital.base.PersistentProperty;
import enterprises.orbital.db.DBPropertyProvider;

public class SDEApplication extends Application {
  // Property which holds the name of the persistence unit for properties
  public static final String PROP_PROPERTIES_PU = "enterprises.orbital.evekit.sde.persistence_unit";
  public static final String PROP_APP_PATH      = "enterprises.orbital.evekit.sde.apppath";
  public static final String DEF_APP_PATH       = "http://localhost/sde";

  public SDEApplication() throws IOException {
    // Populate properties
    OrbitalProperties.addPropertyFile("SDE.properties");
    // Sent persistence unit for properties
    PersistentProperty.setProvider(new DBPropertyProvider(OrbitalProperties.getGlobalProperty(PROP_PROPERTIES_PU)));
  }

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<Class<?>>();
    // SDE API resources
    resources.add(AgtWS.class);
    resources.add(ChrWS.class);
    resources.add(CrpWS.class);
    resources.add(CrtWS.class);
    resources.add(DgmWS.class);
    resources.add(EveWS.class);
    resources.add(InvWS.class);
    resources.add(MapWS.class);
    resources.add(PltWS.class);
    resources.add(RamWS.class);
    resources.add(SknWS.class);
    resources.add(StaWS.class);
    resources.add(TrnWS.class);
    resources.add(WarWS.class);
    // Swagger additions
    resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
    resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    // Return resource set
    return resources;
  }

}
