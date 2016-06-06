package enterprises.orbital.evekit.sde.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import enterprises.orbital.evekit.sde.AttributeSelector;
import enterprises.orbital.evekit.sde.map.MapCelestialStatistic;
import enterprises.orbital.evekit.sde.map.MapConstellation;
import enterprises.orbital.evekit.sde.map.MapConstellationJump;
import enterprises.orbital.evekit.sde.map.MapDenormalize;
import enterprises.orbital.evekit.sde.map.MapJump;
import enterprises.orbital.evekit.sde.map.MapLandmark;
import enterprises.orbital.evekit.sde.map.MapLocationScene;
import enterprises.orbital.evekit.sde.map.MapLocationWormholeClass;
import enterprises.orbital.evekit.sde.map.MapRegion;
import enterprises.orbital.evekit.sde.map.MapRegionJump;
import enterprises.orbital.evekit.sde.map.MapSolarSystem;
import enterprises.orbital.evekit.sde.map.MapSolarSystemJump;
import enterprises.orbital.evekit.sde.map.MapUniverse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/map")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Map"
    },
    produces = "application/json",
    consumes = "application/json")
public class MapWS {

  @Path("/celestial_statistic")
  @GET
  @ApiOperation(
      value = "Get celestial statistics")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested celestial statistics",
              response = MapCelestialStatistic.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getCelestialStatistics(
                                         @Context HttpServletRequest request,
                                         @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                             name = "contid",
                                             required = false,
                                             defaultValue = "-1",
                                             value = "Continuation ID for paged results") int contid,
                                         @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                             name = "maxresults",
                                             required = false,
                                             defaultValue = "1000",
                                             value = "Maximum number of results to retrieve") int maxresults,
                                         @QueryParam("celestialID") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "celestialID",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Celestial ID selector") AttributeSelector celestialID,
                                         @QueryParam("age") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "age",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Age selector") AttributeSelector age,
                                         @QueryParam("density") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "density",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Density selector") AttributeSelector density,
                                         @QueryParam("eccentricity") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "eccentricity",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Eccentricity selector") AttributeSelector eccentricity,
                                         @QueryParam("escapeVelocity") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "escapeVelocity",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Escape velocity selector") AttributeSelector escapeVelocity,
                                         @QueryParam("fragmented") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "fragmented",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Fragmented flag selector") AttributeSelector fragmented,
                                         @QueryParam("life") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "life",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Life flag selector") AttributeSelector life,
                                         @QueryParam("locked") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "locked",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Locked flag selector") AttributeSelector locked,
                                         @QueryParam("luminosity") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "luminosity",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Luminosity selector") AttributeSelector luminosity,
                                         @QueryParam("mass") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "mass",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Mass selector") AttributeSelector mass,
                                         @QueryParam("massDust") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "massDust",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Mass dust selector") AttributeSelector massDust,
                                         @QueryParam("massGas") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "massGas",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Mass gas selector") AttributeSelector massGas,
                                         @QueryParam("orbitPeriod") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "orbitPeriod",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Orbit period selector") AttributeSelector orbitPeriod,
                                         @QueryParam("orbitRadius") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "orbitRadius",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Orbit radius selector") AttributeSelector orbitRadius,
                                         @QueryParam("pressure") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "pressure",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Pressure selector") AttributeSelector pressure,
                                         @QueryParam("radius") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "radius",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Radius selector") AttributeSelector radius,
                                         @QueryParam("rotationRate") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "rotationRate",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Rotation rate selector") AttributeSelector rotationRate,
                                         @QueryParam("spectralClass") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "spectralClass",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Spectral class selector") AttributeSelector spectralClass,
                                         @QueryParam("surfaceGravity") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "surfaceGravity",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Surface gravity selector") AttributeSelector surfaceGravity,
                                         @QueryParam("temperature") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "temperature",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Temperature selector") AttributeSelector temperature) {
    ServiceUtil.sanitizeAttributeSelector(celestialID, age, density, eccentricity, escapeVelocity, fragmented, life, locked, luminosity, mass, massDust,
                                          massGas, orbitPeriod, orbitRadius, pressure, radius, rotationRate, spectralClass, surfaceGravity, temperature);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapCelestialStatistic> result = MapCelestialStatistic.access(contid, maxresults, celestialID, age, density, eccentricity, escapeVelocity, fragmented,
                                                                        life, locked, luminosity, mass, massDust, massGas, orbitPeriod, orbitRadius, pressure,
                                                                        radius, rotationRate, spectralClass, surfaceGravity, temperature);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/constellation")
  @GET
  @ApiOperation(
      value = "Get constellations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested constellations",
              response = MapConstellation.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getConstellations(
                                    @Context HttpServletRequest request,
                                    @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                        name = "contid",
                                        required = false,
                                        defaultValue = "-1",
                                        value = "Continuation ID for paged results") int contid,
                                    @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                        name = "maxresults",
                                        required = false,
                                        defaultValue = "1000",
                                        value = "Maximum number of results to retrieve") int maxresults,
                                    @QueryParam("constellationID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "constellationID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Constellation ID selector") AttributeSelector constellationID,
                                    @QueryParam("constellationName") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "constellationName",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Constellation name selector") AttributeSelector constellationName,
                                    @QueryParam("factionID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "factionID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Faction ID selector") AttributeSelector factionID,
                                    @QueryParam("radius") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "radius",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Radius selector") AttributeSelector radius,
                                    @QueryParam("regionID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "regionID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Region ID selector") AttributeSelector regionID,
                                    @QueryParam("x") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "x",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "X position selector") AttributeSelector x,
                                    @QueryParam("xMax") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "xMax",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Max X extent selector") AttributeSelector xMax,
                                    @QueryParam("xMin") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "xMin",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Min X extent selector") AttributeSelector xMin,
                                    @QueryParam("y") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "y",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Y position selector") AttributeSelector y,
                                    @QueryParam("yMax") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "yMax",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Max Y extent selector") AttributeSelector yMax,
                                    @QueryParam("yMin") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "yMin",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Min Y extent selector") AttributeSelector yMin,
                                    @QueryParam("z") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "z",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Z position selector") AttributeSelector z,
                                    @QueryParam("zMax") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "zMax",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Max Z extent selector") AttributeSelector zMax,
                                    @QueryParam("zMin") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "zMin",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Min Z extent selector") AttributeSelector zMin) {
    ServiceUtil.sanitizeAttributeSelector(constellationID, constellationName, factionID, radius, regionID, x, xMax, xMin, y, yMax, yMin, z, zMax, zMin);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapConstellation> result = MapConstellation.access(contid, maxresults, constellationID, constellationName, factionID, radius, regionID, x, xMax,
                                                              xMin, y, yMax, yMin, z, zMax, zMin);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/constellation_jump")
  @GET
  @ApiOperation(
      value = "Get constellation jumps")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested constellation jumps",
              response = MapConstellationJump.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getConstellationJumps(
                                        @Context HttpServletRequest request,
                                        @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                            name = "contid",
                                            required = false,
                                            defaultValue = "-1",
                                            value = "Continuation ID for paged results") int contid,
                                        @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                            name = "maxresults",
                                            required = false,
                                            defaultValue = "1000",
                                            value = "Maximum number of results to retrieve") int maxresults,
                                        @QueryParam("fromConstellationID") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "fromConstellationID",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "From constellation ID selector") AttributeSelector fromConstellationID,
                                        @QueryParam("toConstellationID") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "toConstellationID",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "To constellation ID selector") AttributeSelector toConstellationID,
                                        @QueryParam("fromRegionID") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "fromRegionID",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "From region ID selector") AttributeSelector fromRegionID,
                                        @QueryParam("toRegionID") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "toRegionID",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "To region ID selector") AttributeSelector toRegionID) {
    ServiceUtil.sanitizeAttributeSelector(fromConstellationID, toConstellationID, fromRegionID, toRegionID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapConstellationJump> result = MapConstellationJump.access(contid, maxresults, fromConstellationID, toConstellationID, fromRegionID, toRegionID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/denormalize")
  @GET
  @ApiOperation(
      value = "Get denormalization")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested denormalization",
              response = MapDenormalize.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getDenormalization(
                                     @Context HttpServletRequest request,
                                     @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                         name = "contid",
                                         required = false,
                                         defaultValue = "-1",
                                         value = "Continuation ID for paged results") int contid,
                                     @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                         name = "maxresults",
                                         required = false,
                                         defaultValue = "1000",
                                         value = "Maximum number of results to retrieve") int maxresults,
                                     @QueryParam("itemID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "itemID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Item ID selector") AttributeSelector itemID,
                                     @QueryParam("celestialIndex") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "celestialIndex",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Celestial index selector") AttributeSelector celestialIndex,
                                     @QueryParam("constellationID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "constellationID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Constellation ID selector") AttributeSelector constellationID,
                                     @QueryParam("groupID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "groupID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Group ID selector") AttributeSelector groupID,
                                     @QueryParam("itemName") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "itemName",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Item name selector") AttributeSelector itemName,
                                     @QueryParam("orbitID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "orbitID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Orbit ID selector") AttributeSelector orbitID,
                                     @QueryParam("orbitIndex") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "orbitIndex",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Orbit index selector") AttributeSelector orbitIndex,
                                     @QueryParam("radius") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "radius",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Radius selector") AttributeSelector radius,
                                     @QueryParam("regionID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "regionID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Region ID selector") AttributeSelector regionID,
                                     @QueryParam("security") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "security",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Security selector") AttributeSelector security,
                                     @QueryParam("solarSystemID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "solarSystemID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Solar system ID selector") AttributeSelector solarSystemID,
                                     @QueryParam("typeID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "typeID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Type ID selector") AttributeSelector typeID,
                                     @QueryParam("x") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "x",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "X position selector") AttributeSelector x,
                                     @QueryParam("y") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "y",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Y position selector") AttributeSelector y,
                                     @QueryParam("z") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "z",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Z position selector") AttributeSelector z) {
    ServiceUtil.sanitizeAttributeSelector(itemID, celestialIndex, constellationID, groupID, itemName, orbitID, orbitIndex, radius, regionID, security,
                                          solarSystemID, typeID, x, y, z);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapDenormalize> result = MapDenormalize.access(contid, maxresults, itemID, celestialIndex, constellationID, groupID, itemName, orbitID, orbitIndex,
                                                          radius, regionID, security, solarSystemID, typeID, x, y, z);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/jump")
  @GET
  @ApiOperation(
      value = "Get jumps")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested jumps",
              response = MapJump.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getJumps(
                           @Context HttpServletRequest request,
                           @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                               name = "contid",
                               required = false,
                               defaultValue = "-1",
                               value = "Continuation ID for paged results") int contid,
                           @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                               name = "maxresults",
                               required = false,
                               defaultValue = "1000",
                               value = "Maximum number of results to retrieve") int maxresults,
                           @QueryParam("stargateID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "stargateID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Stargate ID selector") AttributeSelector stargateID,
                           @QueryParam("celestialID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "celestialID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Celestial ID selector") AttributeSelector celestialID) {
    ServiceUtil.sanitizeAttributeSelector(stargateID, celestialID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapJump> result = MapJump.access(contid, maxresults, stargateID, celestialID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/landmark")
  @GET
  @ApiOperation(
      value = "Get landmarks")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested landmarks",
              response = MapLandmark.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getLandmarks(
                               @Context HttpServletRequest request,
                               @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                   name = "contid",
                                   required = false,
                                   defaultValue = "-1",
                                   value = "Continuation ID for paged results") int contid,
                               @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                   name = "maxresults",
                                   required = false,
                                   defaultValue = "1000",
                                   value = "Maximum number of results to retrieve") int maxresults,
                               @QueryParam("landmarkID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "landmarkID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Landmark ID selector") AttributeSelector landmarkID,
                               @QueryParam("description") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "description",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Description text selector") AttributeSelector description,
                               @QueryParam("iconID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "iconID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Icon ID selector") AttributeSelector iconID,
                               @QueryParam("importance") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "importance",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Importance selector") AttributeSelector importance,
                               @QueryParam("landmarkName") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "landmarkName",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Landmark name selector") AttributeSelector landmarkName,
                               @QueryParam("locationID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "locationID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Location ID selector") AttributeSelector locationID,
                               @QueryParam("radius") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "radius",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Radius selector") AttributeSelector radius,
                               @QueryParam("x") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "x",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "X selector") AttributeSelector x,
                               @QueryParam("y") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "y",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Y selector") AttributeSelector y,
                               @QueryParam("z") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "z",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Z selector") AttributeSelector z) {
    ServiceUtil.sanitizeAttributeSelector(landmarkID, description, iconID, importance, landmarkName, locationID, radius, x, y, z);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapLandmark> result = MapLandmark.access(contid, maxresults, landmarkID, description, iconID, importance, landmarkName, locationID, radius, x, y, z);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/location_scene")
  @GET
  @ApiOperation(
      value = "Get location scenes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested location scenes",
              response = MapLocationScene.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getLocationScenes(
                                    @Context HttpServletRequest request,
                                    @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                        name = "contid",
                                        required = false,
                                        defaultValue = "-1",
                                        value = "Continuation ID for paged results") int contid,
                                    @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                        name = "maxresults",
                                        required = false,
                                        defaultValue = "1000",
                                        value = "Maximum number of results to retrieve") int maxresults,
                                    @QueryParam("locationID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "locationID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Location ID selector") AttributeSelector locationID,
                                    @QueryParam("graphicID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "graphicID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Graphic ID selector") AttributeSelector graphicID) {
    ServiceUtil.sanitizeAttributeSelector(locationID, graphicID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapLocationScene> result = MapLocationScene.access(contid, maxresults, locationID, graphicID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/location_wormhole_class")
  @GET
  @ApiOperation(
      value = "Get location wormhole classes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested location wormhole classes",
              response = MapLocationWormholeClass.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getLocationWormholeClasses(
                                             @Context HttpServletRequest request,
                                             @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                                 name = "contid",
                                                 required = false,
                                                 defaultValue = "-1",
                                                 value = "Continuation ID for paged results") int contid,
                                             @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                                 name = "maxresults",
                                                 required = false,
                                                 defaultValue = "1000",
                                                 value = "Maximum number of results to retrieve") int maxresults,
                                             @QueryParam("locationID") @DefaultValue(
                                                 value = "{ any: true }") @ApiParam(
                                                     name = "locationID",
                                                     required = false,
                                                     defaultValue = "{ any: true }",
                                                     value = "Location ID selector") AttributeSelector locationID,
                                             @QueryParam("wormholeClassID") @DefaultValue(
                                                 value = "{ any: true }") @ApiParam(
                                                     name = "wormholeClassID",
                                                     required = false,
                                                     defaultValue = "{ any: true }",
                                                     value = "Wormhole class ID selector") AttributeSelector wormholeClassID) {
    ServiceUtil.sanitizeAttributeSelector(locationID, wormholeClassID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapLocationWormholeClass> result = MapLocationWormholeClass.access(contid, maxresults, locationID, wormholeClassID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/region")
  @GET
  @ApiOperation(
      value = "Get regions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested regions",
              response = MapRegion.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getRegions(
                             @Context HttpServletRequest request,
                             @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                 name = "contid",
                                 required = false,
                                 defaultValue = "-1",
                                 value = "Continuation ID for paged results") int contid,
                             @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                 name = "maxresults",
                                 required = false,
                                 defaultValue = "1000",
                                 value = "Maximum number of results to retrieve") int maxresults,
                             @QueryParam("regionID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "regionID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Region ID selector") AttributeSelector regionID,
                             @QueryParam("factionID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "factionID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Faction ID selector") AttributeSelector factionID,
                             @QueryParam("radius") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "radius",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Radius selector") AttributeSelector radius,
                             @QueryParam("regionName") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "regionName",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Region name selector") AttributeSelector regionName,
                             @QueryParam("x") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "x",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "X position selector") AttributeSelector x,
                             @QueryParam("xMax") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "xMax",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Max X extent selector") AttributeSelector xMax,
                             @QueryParam("xMin") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "xMin",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Min X extent selector") AttributeSelector xMin,
                             @QueryParam("y") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "y",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Y position selector") AttributeSelector y,
                             @QueryParam("yMax") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "yMax",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Max Y extent selector") AttributeSelector yMax,
                             @QueryParam("yMin") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "yMin",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Min Y extent selector") AttributeSelector yMin,
                             @QueryParam("z") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "z",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Z position selector") AttributeSelector z,
                             @QueryParam("zMax") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "zMax",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Max Z extent selector") AttributeSelector zMax,
                             @QueryParam("zMin") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "zMin",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Min Z extent selector") AttributeSelector zMin) {
    ServiceUtil.sanitizeAttributeSelector(regionID, factionID, radius, regionName, x, xMax, xMin, y, yMax, yMin, z, zMax, zMin);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapRegion> result = MapRegion.access(contid, maxresults, regionID, factionID, radius, regionName, x, xMax, xMin, y, yMax, yMin, z, zMax, zMin);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/region_jump")
  @GET
  @ApiOperation(
      value = "Get region jumps")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested region jumps",
              response = MapRegionJump.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getRegionJumps(
                                 @Context HttpServletRequest request,
                                 @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                     name = "contid",
                                     required = false,
                                     defaultValue = "-1",
                                     value = "Continuation ID for paged results") int contid,
                                 @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                     name = "maxresults",
                                     required = false,
                                     defaultValue = "1000",
                                     value = "Maximum number of results to retrieve") int maxresults,
                                 @QueryParam("fromRegionID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "fromRegionID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "From region ID selector") AttributeSelector fromRegionID,
                                 @QueryParam("toRegionID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "toRegionID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "To region ID selector") AttributeSelector toRegionID) {
    ServiceUtil.sanitizeAttributeSelector(fromRegionID, toRegionID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapRegionJump> result = MapRegionJump.access(contid, maxresults, fromRegionID, toRegionID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/solar_system")
  @GET
  @ApiOperation(
      value = "Get solar systems")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested solar systems",
              response = MapSolarSystem.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getSolarSystems(
                                  @Context HttpServletRequest request,
                                  @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                      name = "contid",
                                      required = false,
                                      defaultValue = "-1",
                                      value = "Continuation ID for paged results") int contid,
                                  @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                      name = "maxresults",
                                      required = false,
                                      defaultValue = "1000",
                                      value = "Maximum number of results to retrieve") int maxresults,
                                  @QueryParam("solarSystemID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "solarSystemID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Solar system ID selector") AttributeSelector solarSystemID,
                                  @QueryParam("border") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "border",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Border flag selector") AttributeSelector border,
                                  @QueryParam("constellation") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "constellation",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Constellation selector") AttributeSelector constellation,
                                  @QueryParam("constellationID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "constellationID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Constellation ID selector") AttributeSelector constellationID,
                                  @QueryParam("corridor") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "corridor",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Corridor flag selector") AttributeSelector corridor,
                                  @QueryParam("factionID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "factionID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Faction ID selector") AttributeSelector factionID,
                                  @QueryParam("fringe") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "fringe",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Fringe flag selector") AttributeSelector fringe,
                                  @QueryParam("hub") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "hub",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Hub flag selector") AttributeSelector hub,
                                  @QueryParam("international") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "international",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "International flag selector") AttributeSelector international,
                                  @QueryParam("luminosity") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "luminosity",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Luminosity selector") AttributeSelector luminosity,
                                  @QueryParam("radius") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "radius",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Radius selector") AttributeSelector radius,
                                  @QueryParam("regional") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "regional",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Regional flag selector") AttributeSelector regional,
                                  @QueryParam("regionID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "regionID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Region ID selector") AttributeSelector regionID,
                                  @QueryParam("security") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "security",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Security selector") AttributeSelector security,
                                  @QueryParam("securityClass") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "securityClass",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Security class selector") AttributeSelector securityClass,
                                  @QueryParam("solarSystemName") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "solarSystemName",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Solar system name selector") AttributeSelector solarSystemName,
                                  @QueryParam("sunTypeID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "sunTypeID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Sun type ID selector") AttributeSelector sunTypeID,
                                  @QueryParam("x") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "x",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "X position selector") AttributeSelector x,
                                  @QueryParam("xMax") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "xMax",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Max X extent selector") AttributeSelector xMax,
                                  @QueryParam("xMin") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "xMin",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Min X extent selector") AttributeSelector xMin,
                                  @QueryParam("y") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "y",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Y position selector") AttributeSelector y,
                                  @QueryParam("yMax") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "yMax",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Max Y extent selector") AttributeSelector yMax,
                                  @QueryParam("yMin") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "yMin",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Min Y extent selector") AttributeSelector yMin,
                                  @QueryParam("z") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "z",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Z position selector") AttributeSelector z,
                                  @QueryParam("zMax") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "zMax",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Max Z extent selector") AttributeSelector zMax,
                                  @QueryParam("zMin") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "zMin",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Min Z extent selector") AttributeSelector zMin) {
    ServiceUtil.sanitizeAttributeSelector(solarSystemID, border, constellation, constellationID, corridor, factionID, fringe, hub, international, luminosity,
                                          radius, regional, regionID, security, securityClass, solarSystemName, sunTypeID, x, xMax, xMin, y, yMax, yMin, z,
                                          zMax, zMin);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapSolarSystem> result = MapSolarSystem.access(contid, maxresults, solarSystemID, border, constellation, constellationID, corridor, factionID,
                                                          fringe, hub, international, luminosity, radius, regional, regionID, security, securityClass,
                                                          solarSystemName, sunTypeID, x, xMax, xMin, y, yMax, yMin, z, zMax, zMin);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/solar_system_jump")
  @GET
  @ApiOperation(
      value = "Get solar system jumps")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested solar system jumps",
              response = MapSolarSystemJump.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getSolarSystemJumps(
                                      @Context HttpServletRequest request,
                                      @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                          name = "contid",
                                          required = false,
                                          defaultValue = "-1",
                                          value = "Continuation ID for paged results") int contid,
                                      @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                          name = "maxresults",
                                          required = false,
                                          defaultValue = "1000",
                                          value = "Maximum number of results to retrieve") int maxresults,
                                      @QueryParam("fromSolarSystemID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "fromSolarSystemID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Source solar system ID selector") AttributeSelector fromSolarSystemID,
                                      @QueryParam("toSolarSystemID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "toSolarSystemID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Destination solar system ID selector") AttributeSelector toSolarSystemID,
                                      @QueryParam("fromConstellationID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "fromConstellationID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Source constellation ID selector") AttributeSelector fromConstellationID,
                                      @QueryParam("fromRegionID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "fromRegionID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Source region ID selector") AttributeSelector fromRegionID,
                                      @QueryParam("toConstellationID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "toConstellationID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Destination constellation ID selector") AttributeSelector toConstellationID,
                                      @QueryParam("toRegionID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "toRegionID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Destination region ID selector") AttributeSelector toRegionID) {
    ServiceUtil.sanitizeAttributeSelector(fromSolarSystemID, toSolarSystemID, fromConstellationID, fromRegionID, toConstellationID, toRegionID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapSolarSystemJump> result = MapSolarSystemJump.access(contid, maxresults, fromSolarSystemID, toSolarSystemID, fromConstellationID, fromRegionID,
                                                                  toConstellationID, toRegionID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/universe")
  @GET
  @ApiOperation(
      value = "Get universes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested universes",
              response = MapUniverse.class,
              responseContainer = "array"),
          @ApiResponse(
              code = 400,
              message = "invalid attribute selector",
              response = ServiceError.class),
          @ApiResponse(
              code = 500,
              message = "internal service error",
              response = ServiceError.class),
      })
  public Response getUniverses(
                               @Context HttpServletRequest request,
                               @QueryParam("contid") @DefaultValue("-1") @ApiParam(
                                   name = "contid",
                                   required = false,
                                   defaultValue = "-1",
                                   value = "Continuation ID for paged results") int contid,
                               @QueryParam("maxresults") @DefaultValue("1000") @ApiParam(
                                   name = "maxresults",
                                   required = false,
                                   defaultValue = "1000",
                                   value = "Maximum number of results to retrieve") int maxresults,
                               @QueryParam("universeID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "universeID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Universe ID selector") AttributeSelector universeID,
                               @QueryParam("radius") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "radius",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Radius selector") AttributeSelector radius,
                               @QueryParam("universeName") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "universeName",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Universe name selector") AttributeSelector universeName,
                               @QueryParam("x") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "x",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "X position selector") AttributeSelector x,
                               @QueryParam("xMax") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "xMax",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Max X extent selector") AttributeSelector xMax,
                               @QueryParam("xMin") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "xMin",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Min X extent selector") AttributeSelector xMin,
                               @QueryParam("y") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "y",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Y position selector") AttributeSelector y,
                               @QueryParam("yMax") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "yMax",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Max Y extent selector") AttributeSelector yMax,
                               @QueryParam("yMin") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "yMin",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Min Y extent selector") AttributeSelector yMin,
                               @QueryParam("z") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "z",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Z position selector") AttributeSelector z,
                               @QueryParam("zMax") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "zMax",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Max Z extent selector") AttributeSelector zMax,
                               @QueryParam("zMin") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "zMin",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Min Z extent selector") AttributeSelector zMin) {
    ServiceUtil.sanitizeAttributeSelector(universeID, radius, universeName, x, xMax, xMin, y, yMax, yMin, z, zMax, zMin);
    maxresults = Math.min(1000, maxresults);
    try {
      List<MapUniverse> result = MapUniverse.access(contid, maxresults, universeID, radius, universeName, x, xMax, xMin, y, yMax, yMin, z, zMax, zMin);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
