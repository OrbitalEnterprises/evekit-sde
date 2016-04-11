package enterprises.orbital.evekit.sde.map;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.Immutable;

import enterprises.orbital.db.ConnectionFactory.RunInTransaction;
import enterprises.orbital.evekit.sde.AttributeParameters;
import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.SDE;

/**
 * The persistent class for the mapcelestialstatistics database table.
 * 
 */
@Entity
@Table(
    name = "mapcelestialstatistics")
@Immutable
public class MapCelestialStatistic {
  private static final Logger log = Logger.getLogger(MapCelestialStatistic.class.getName());

  @Id
  private int                 celestialID;
  private double              age;
  private double              density;
  private double              eccentricity;
  private double              escapeVelocity;
  private byte                fragmented;
  private double              life;
  private byte                locked;
  private double              luminosity;
  private double              mass;
  private double              massDust;
  private double              massGas;
  private double              orbitPeriod;
  private double              orbitRadius;
  private double              pressure;
  private double              radius;
  private double              rotationRate;
  private String              spectralClass;
  private double              surfaceGravity;
  private double              temperature;

  public MapCelestialStatistic() {}

  public int getCelestialID() {
    return this.celestialID;
  }

  public double getAge() {
    return this.age;
  }

  public double getDensity() {
    return this.density;
  }

  public double getEccentricity() {
    return this.eccentricity;
  }

  public double getEscapeVelocity() {
    return this.escapeVelocity;
  }

  public byte getFragmented() {
    return this.fragmented;
  }

  public double getLife() {
    return this.life;
  }

  public byte getLocked() {
    return this.locked;
  }

  public double getLuminosity() {
    return this.luminosity;
  }

  public double getMass() {
    return this.mass;
  }

  public double getMassDust() {
    return this.massDust;
  }

  public double getMassGas() {
    return this.massGas;
  }

  public double getOrbitPeriod() {
    return this.orbitPeriod;
  }

  public double getOrbitRadius() {
    return this.orbitRadius;
  }

  public double getPressure() {
    return this.pressure;
  }

  public double getRadius() {
    return this.radius;
  }

  public double getRotationRate() {
    return this.rotationRate;
  }

  public String getSpectralClass() {
    return this.spectralClass;
  }

  public double getSurfaceGravity() {
    return this.surfaceGravity;
  }

  public double getTemperature() {
    return this.temperature;
  }

  public static List<MapCelestialStatistic> access(
                                                   final int contid,
                                                   final int maxresults,
                                                   final AttributeSelector celestialID,
                                                   final AttributeSelector age,
                                                   final AttributeSelector density,
                                                   final AttributeSelector eccentricity,
                                                   final AttributeSelector escapeVelocity,
                                                   final AttributeSelector fragmented,
                                                   final AttributeSelector life,
                                                   final AttributeSelector locked,
                                                   final AttributeSelector luminosity,
                                                   final AttributeSelector mass,
                                                   final AttributeSelector massDust,
                                                   final AttributeSelector massGas,
                                                   final AttributeSelector orbitPeriod,
                                                   final AttributeSelector orbitRadius,
                                                   final AttributeSelector pressure,
                                                   final AttributeSelector radius,
                                                   final AttributeSelector rotationRate,
                                                   final AttributeSelector spectralClass,
                                                   final AttributeSelector surfaceGravity,
                                                   final AttributeSelector temperature) {
    try {
      return SDE.getFactory().runTransaction(new RunInTransaction<List<MapCelestialStatistic>>() {
        @Override
        public List<MapCelestialStatistic> run() throws Exception {
          int maxcount = Math.max(Math.min(maxresults, SDE.DEFAULT_MAX_RESULTS), 1);
          int offset = Math.max(0, contid);
          StringBuilder qs = new StringBuilder();
          // Constrain attributes
          qs.append("SELECT c FROM MapCelestialStatistic c WHERE 1 = 1");
          AttributeParameters p = new AttributeParameters("att");
          AttributeSelector.addIntSelector(qs, "c", "celestialID", celestialID);
          AttributeSelector.addDoubleSelector(qs, "c", "age", age);
          AttributeSelector.addDoubleSelector(qs, "c", "density", density);
          AttributeSelector.addDoubleSelector(qs, "c", "eccentricity", eccentricity);
          AttributeSelector.addDoubleSelector(qs, "c", "escapeVelocity", escapeVelocity);
          AttributeSelector.addIntSelector(qs, "c", "fragmented", fragmented);
          AttributeSelector.addDoubleSelector(qs, "c", "life", life);
          AttributeSelector.addIntSelector(qs, "c", "locked", locked);
          AttributeSelector.addDoubleSelector(qs, "c", "luminosity", luminosity);
          AttributeSelector.addDoubleSelector(qs, "c", "mass", mass);
          AttributeSelector.addDoubleSelector(qs, "c", "massDust", massDust);
          AttributeSelector.addDoubleSelector(qs, "c", "massGas", massGas);
          AttributeSelector.addDoubleSelector(qs, "c", "orbitPeriod", orbitPeriod);
          AttributeSelector.addDoubleSelector(qs, "c", "orbitRadius", orbitRadius);
          AttributeSelector.addDoubleSelector(qs, "c", "pressure", pressure);
          AttributeSelector.addDoubleSelector(qs, "c", "radius", radius);
          AttributeSelector.addDoubleSelector(qs, "c", "rotationRate", rotationRate);
          AttributeSelector.addStringSelector(qs, "c", "spectralClass", spectralClass, p);
          AttributeSelector.addDoubleSelector(qs, "c", "surfaceGravity", surfaceGravity);
          AttributeSelector.addDoubleSelector(qs, "c", "temperature", temperature);
          // Return result
          TypedQuery<MapCelestialStatistic> query = SDE.getFactory().getEntityManager().createQuery(qs.toString(), MapCelestialStatistic.class);
          p.fillParams(query);
          query.setMaxResults(maxcount);
          query.setFirstResult(offset);
          return query.getResultList();
        }
      });
    } catch (Exception e) {
      log.log(Level.SEVERE, "query error", e);
    }
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    return "MapCelestialStatistic [celestialID=" + celestialID + ", age=" + age + ", density=" + density + ", eccentricity=" + eccentricity
        + ", escapeVelocity=" + escapeVelocity + ", fragmented=" + fragmented + ", life=" + life + ", locked=" + locked + ", luminosity=" + luminosity
        + ", mass=" + mass + ", massDust=" + massDust + ", massGas=" + massGas + ", orbitPeriod=" + orbitPeriod + ", orbitRadius=" + orbitRadius + ", pressure="
        + pressure + ", radius=" + radius + ", rotationRate=" + rotationRate + ", spectralClass=" + spectralClass + ", surfaceGravity=" + surfaceGravity
        + ", temperature=" + temperature + "]";
  }

}