package enterprises.orbital.evekit.sde.map;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

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
public class MapCelestialStatistic {
  private static final Logger log = Logger.getLogger(MapCelestialStatistic.class.getName());

  @Id
  private int                 celestialID;
  private Double              temperature;
  private String              spectralClass;
  private Double              luminosity;
  private Double              age;
  private Double              life;
  private Double              orbitRadius;
  private Double              eccentricity;
  private Double              massDust;
  private Double              massGas;
  private Boolean                fragmented;
  private Double              density;
  private Double              surfaceGravity;
  private Double              escapeVelocity;
  private Double              orbitPeriod;
  private Double              rotationRate;
  private Boolean                locked;
  private Double              pressure;
  private Double              radius;
  private Double              mass;

  public MapCelestialStatistic() {}

  public MapCelestialStatistic(int celestialID, Double age, Double density, Double eccentricity, Double escapeVelocity, Boolean fragmented, Double life,
                               Boolean locked, Double luminosity, Double mass, Double massDust, Double massGas, Double orbitPeriod, Double orbitRadius,
                               Double pressure, Double radius, Double rotationRate, String spectralClass, Double surfaceGravity, Double temperature) {
    super();
    this.celestialID = celestialID;
    this.age = age;
    this.density = density;
    this.eccentricity = eccentricity;
    this.escapeVelocity = escapeVelocity;
    this.fragmented = fragmented;
    this.life = life;
    this.locked = locked;
    this.luminosity = luminosity;
    this.mass = mass;
    this.massDust = massDust;
    this.massGas = massGas;
    this.orbitPeriod = orbitPeriod;
    this.orbitRadius = orbitRadius;
    this.pressure = pressure;
    this.radius = radius;
    this.rotationRate = rotationRate;
    this.spectralClass = spectralClass;
    this.surfaceGravity = surfaceGravity;
    this.temperature = temperature;
  }

  public int getCelestialID() {
    return this.celestialID;
  }

  public Double getAge() {
    return this.age;
  }

  public Double getDensity() {
    return this.density;
  }

  public Double getEccentricity() {
    return this.eccentricity;
  }

  public Double getEscapeVelocity() {
    return this.escapeVelocity;
  }

  public Boolean isFragmented() {
    return this.fragmented;
  }

  public Double getLife() {
    return this.life;
  }

  public Boolean isLocked() {
    return this.locked;
  }

  public Double getLuminosity() {
    return this.luminosity;
  }

  public Double getMass() {
    return this.mass;
  }

  public Double getMassDust() {
    return this.massDust;
  }

  public Double getMassGas() {
    return this.massGas;
  }

  public Double getOrbitPeriod() {
    return this.orbitPeriod;
  }

  public Double getOrbitRadius() {
    return this.orbitRadius;
  }

  public Double getPressure() {
    return this.pressure;
  }

  public Double getRadius() {
    return this.radius;
  }

  public Double getRotationRate() {
    return this.rotationRate;
  }

  public String getSpectralClass() {
    return this.spectralClass;
  }

  public Double getSurfaceGravity() {
    return this.surfaceGravity;
  }

  public Double getTemperature() {
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
      return SDE.getFactory().runTransaction(() -> {
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
        AttributeSelector.addBooleanSelector(qs, "c", "fragmented", fragmented);
        AttributeSelector.addDoubleSelector(qs, "c", "life", life);
        AttributeSelector.addBooleanSelector(qs, "c", "locked", locked);
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