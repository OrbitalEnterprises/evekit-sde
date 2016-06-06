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
import enterprises.orbital.evekit.sde.sta.StaOperation;
import enterprises.orbital.evekit.sde.sta.StaOperationService;
import enterprises.orbital.evekit.sde.sta.StaService;
import enterprises.orbital.evekit.sde.sta.StaStation;
import enterprises.orbital.evekit.sde.sta.StaStationType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/sta")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Station"
    },
    produces = "application/json",
    consumes = "application/json")
public class StaWS {

  @Path("/operation")
  @GET
  @ApiOperation(
      value = "Get operations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested operations",
              response = StaOperation.class,
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
  public Response getOperations(
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
                                @QueryParam("operationID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "operationID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Operation ID selector") AttributeSelector operationID,
                                @QueryParam("activityID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "activityID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Activity ID selector") AttributeSelector activityID,
                                @QueryParam("amarrStationTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "amarrStationTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Amarr station type ID selector") AttributeSelector amarrStationTypeID,
                                @QueryParam("border") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "border",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Border flag selector") AttributeSelector border,
                                @QueryParam("caldariStationTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "caldariStationTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Caldari station type ID selector") AttributeSelector caldariStationTypeID,
                                @QueryParam("corridor") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "corridor",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Corridor flag selector") AttributeSelector corridor,
                                @QueryParam("description") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "description",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Description text selector") AttributeSelector description,
                                @QueryParam("fringe") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "fringe",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Fringe flag selector") AttributeSelector fringe,
                                @QueryParam("gallenteStationTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "gallenteStationTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Gallente station type ID selector") AttributeSelector gallenteStationTypeID,
                                @QueryParam("hub") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "hub",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Hub flag selector") AttributeSelector hub,
                                @QueryParam("joveStationTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "joveStationTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Jove station type ID selector") AttributeSelector joveStationTypeID,
                                @QueryParam("minmatarStationTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "minmatarStationTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Minmatar station type ID selector") AttributeSelector minmatarStationTypeID,
                                @QueryParam("operationName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "operationName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Operation name selector") AttributeSelector operationName,
                                @QueryParam("ratio") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "ratio",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Ratio selector") AttributeSelector ratio) {
    ServiceUtil.sanitizeAttributeSelector(operationID, activityID, amarrStationTypeID, border, caldariStationTypeID, corridor, description, fringe,
                                          gallenteStationTypeID, hub, joveStationTypeID, minmatarStationTypeID, operationName, ratio);
    maxresults = Math.min(1000, maxresults);
    try {
      List<StaOperation> result = StaOperation.access(contid, maxresults, operationID, activityID, amarrStationTypeID, border, caldariStationTypeID, corridor,
                                                      description, fringe, gallenteStationTypeID, hub, joveStationTypeID, minmatarStationTypeID, operationName,
                                                      ratio);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/operation_service")
  @GET
  @ApiOperation(
      value = "Get operation services")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested operation services",
              response = StaOperationService.class,
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
  public Response getOperationServices(
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
                                       @QueryParam("operationID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "operationID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Operation ID selector") AttributeSelector operationID,
                                       @QueryParam("serviceID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "serviceID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Service ID selector") AttributeSelector serviceID) {
    ServiceUtil.sanitizeAttributeSelector(operationID, serviceID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<StaOperationService> result = StaOperationService.access(contid, maxresults, operationID, serviceID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/service")
  @GET
  @ApiOperation(
      value = "Get services")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested services",
              response = StaService.class,
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
  public Response getServices(
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
                              @QueryParam("serviceID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "serviceID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Service ID selector") AttributeSelector serviceID,
                              @QueryParam("description") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "description",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Description text selector") AttributeSelector description,
                              @QueryParam("serviceName") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "serviceName",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Service name selector") AttributeSelector serviceName) {
    ServiceUtil.sanitizeAttributeSelector(serviceID, description, serviceName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<StaService> result = StaService.access(contid, maxresults, serviceID, description, serviceName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/station")
  @GET
  @ApiOperation(
      value = "Get stations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested stations",
              response = StaStation.class,
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
  public Response getStations(
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
                              @QueryParam("stationID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "stationID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Station ID selector") AttributeSelector stationID,
                              @QueryParam("constellationID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "constellationID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Constellation ID selector") AttributeSelector constellationID,
                              @QueryParam("corporationID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "corporationID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Corporation ID selector") AttributeSelector corporationID,
                              @QueryParam("dockingCostPerVolume") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "dockingCostPerVolume",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Docking cost per volume selector") AttributeSelector dockingCostPerVolume,
                              @QueryParam("maxShipVolumeDockable") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "maxShipVolumeDockable",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Max ship volume dockable selector") AttributeSelector maxShipVolumeDockable,
                              @QueryParam("officeRentalCost") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "officeRentalCost",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Office rental cost selector") AttributeSelector officeRentalCost,
                              @QueryParam("operationID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "operationID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Operation ID selector") AttributeSelector operationID,
                              @QueryParam("regionID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "regionID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Region ID selector") AttributeSelector regionID,
                              @QueryParam("reprocessingEfficiency") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "reprocessingEfficiency",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Reprocessing efficiency selector") AttributeSelector reprocessingEfficiency,
                              @QueryParam("reprocessingHangarFlag") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "reprocessingHangarFlag",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Reprocessing hangar flag selector") AttributeSelector reprocessingHangarFlag,
                              @QueryParam("reprocessingStationsTake") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "reprocessingStationsTake",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Reprocessing stations take selector") AttributeSelector reprocessingStationsTake,
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
                              @QueryParam("stationName") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "stationName",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Station name selector") AttributeSelector stationName,
                              @QueryParam("stationTypeID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "stationTypeID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Station type ID selector") AttributeSelector stationTypeID,
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
    ServiceUtil.sanitizeAttributeSelector(stationID, constellationID, corporationID, dockingCostPerVolume, maxShipVolumeDockable, officeRentalCost, operationID,
                                          regionID, reprocessingEfficiency, reprocessingHangarFlag, reprocessingStationsTake, security, solarSystemID,
                                          stationName, stationTypeID, x, y, z);
    maxresults = Math.min(1000, maxresults);
    try {
      List<StaStation> result = StaStation.access(contid, maxresults, stationID, constellationID, corporationID, dockingCostPerVolume, maxShipVolumeDockable,
                                                  officeRentalCost, operationID, regionID, reprocessingEfficiency, reprocessingHangarFlag,
                                                  reprocessingStationsTake, security, solarSystemID, stationName, stationTypeID, x, y, z);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/station_type")
  @GET
  @ApiOperation(
      value = "Get station types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested station types",
              response = StaStationType.class,
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
  public Response getStationTypes(
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
                                  @QueryParam("stationTypeID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "stationTypeID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Station type ID selector") AttributeSelector stationTypeID,
                                  @QueryParam("conquerable") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "conquerable",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Conquerable flag selector") AttributeSelector conquerable,
                                  @QueryParam("dockEntryX") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "dockEntryX",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Dock entry X position selector") AttributeSelector dockEntryX,
                                  @QueryParam("dockEntryY") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "dockEntryY",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Dock entry Y position selector") AttributeSelector dockEntryY,
                                  @QueryParam("dockEntryZ") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "dockEntryZ",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Dock entry Z position selector") AttributeSelector dockEntryZ,
                                  @QueryParam("dockOrientationX") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "dockOrientationX",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Dock orientation X position selector") AttributeSelector dockOrientationX,
                                  @QueryParam("dockOrientationY") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "dockOrientationY",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Dock orientation Y position selector") AttributeSelector dockOrientationY,
                                  @QueryParam("dockOrientationZ") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "dockOrientationZ",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Dock orientation Z position selector") AttributeSelector dockOrientationZ,
                                  @QueryParam("officeSlots") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "officeSlots",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Office slots selector") AttributeSelector officeSlots,
                                  @QueryParam("operationID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "operationID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Operation ID selector") AttributeSelector operationID,
                                  @QueryParam("reprocessingEfficiency") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "reprocessingEfficiency",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Reprocessing efficiency selector") AttributeSelector reprocessingEfficiency) {
    ServiceUtil.sanitizeAttributeSelector(stationTypeID, conquerable, dockEntryX, dockEntryY, dockEntryZ, dockOrientationX, dockOrientationY, dockOrientationZ,
                                          officeSlots, operationID, reprocessingEfficiency);
    maxresults = Math.min(1000, maxresults);
    try {
      List<StaStationType> result = StaStationType.access(contid, maxresults, stationTypeID, conquerable, dockEntryX, dockEntryY, dockEntryZ, dockOrientationX,
                                                          dockOrientationY, dockOrientationZ, officeSlots, operationID, reprocessingEfficiency);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
