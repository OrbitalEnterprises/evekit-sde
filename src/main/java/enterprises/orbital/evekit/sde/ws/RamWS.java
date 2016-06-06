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
import enterprises.orbital.evekit.sde.ram.RamActivity;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineStation;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineType;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineTypeDetailPerCategory;
import enterprises.orbital.evekit.sde.ram.RamAssemblyLineTypeDetailPerGroup;
import enterprises.orbital.evekit.sde.ram.RamInstallationTypeContent;
import enterprises.orbital.evekit.sde.ram.RamTypeRequirement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/ram")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "RAM"
    },
    produces = "application/json",
    consumes = "application/json")
public class RamWS {

  @Path("/activity")
  @GET
  @ApiOperation(
      value = "Get activities")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested activities",
              response = RamActivity.class,
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
  public Response getRAMActivities(
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
                                   @QueryParam("activityID") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "activityID",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Activity ID selector") AttributeSelector activityID,
                                   @QueryParam("activityName") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "activityName",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Activity name selector") AttributeSelector activityName,
                                   @QueryParam("description") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "description",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Description text selector") AttributeSelector description,
                                   @QueryParam("iconNo") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "iconNo",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Icon number selector") AttributeSelector iconNo,
                                   @QueryParam("published") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "published",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Published flag selector") AttributeSelector published) {
    ServiceUtil.sanitizeAttributeSelector(activityID, activityName, description, iconNo, published);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamActivity> result = RamActivity.access(contid, maxresults, activityID, activityName, description, iconNo, published);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/assembly_line_station")
  @GET
  @ApiOperation(
      value = "Get assembly line stations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested assembly line stations",
              response = RamAssemblyLineStation.class,
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
  public Response getAssemblyLineStations(
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
                                          @QueryParam("assemblyLineTypeID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "assemblyLineTypeID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Assembly line type ID selector") AttributeSelector assemblyLineTypeID,
                                          @QueryParam("ownerID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "ownerID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Owner ID selector") AttributeSelector ownerID,
                                          @QueryParam("quantity") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "quantity",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Quantity selector") AttributeSelector quantity,
                                          @QueryParam("regionID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "regionID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Region ID selector") AttributeSelector regionID,
                                          @QueryParam("solarSystemID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "solarSystemID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Solary system ID selector") AttributeSelector solarSystemID,
                                          @QueryParam("stationTypeID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "stationTypeID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Station type ID selector") AttributeSelector stationTypeID) {
    ServiceUtil.sanitizeAttributeSelector(stationID, assemblyLineTypeID, ownerID, quantity, regionID, solarSystemID, stationTypeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamAssemblyLineStation> result = RamAssemblyLineStation.access(contid, maxresults, stationID, assemblyLineTypeID, ownerID, quantity, regionID,
                                                                          solarSystemID, stationTypeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/assembly_line_type")
  @GET
  @ApiOperation(
      value = "Get assembly line types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested assembly line types",
              response = RamAssemblyLineType.class,
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
  public Response getAssemblyLineTypes(
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
                                       @QueryParam("assemblyLineTypeID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "assemblyLineTypeID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Assembly line type ID selector") AttributeSelector assemblyLineTypeID,
                                       @QueryParam("activityID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "activityID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Activity ID selector") AttributeSelector activityID,
                                       @QueryParam("assemblyLineTypeName") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "assemblyLineTypeName",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Assembly line type name selector") AttributeSelector assemblyLineTypeName,
                                       @QueryParam("baseCostMultiplier") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "baseCostMultiplier",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Base cost multiplier selector") AttributeSelector baseCostMultiplier,
                                       @QueryParam("baseMaterialMultiplier") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "baseMaterialMultiplier",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Base material multiplier selector") AttributeSelector baseMaterialMultiplier,
                                       @QueryParam("baseTimeMultiplier") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "baseTimeMultiplier",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Base time multiplier selector") AttributeSelector baseTimeMultiplier,
                                       @QueryParam("description") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "description",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Description text selector") AttributeSelector description,
                                       @QueryParam("minCostPerHour") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "minCostPerHour",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Minimum cost per hour selector") AttributeSelector minCostPerHour,
                                       @QueryParam("volume") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "volume",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Volume selector") AttributeSelector volume) {
    ServiceUtil.sanitizeAttributeSelector(assemblyLineTypeID, activityID, assemblyLineTypeName, baseCostMultiplier, baseMaterialMultiplier, baseTimeMultiplier,
                                          description, minCostPerHour, volume);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamAssemblyLineType> result = RamAssemblyLineType.access(contid, maxresults, assemblyLineTypeID, activityID, assemblyLineTypeName,
                                                                    baseCostMultiplier, baseMaterialMultiplier, baseTimeMultiplier, description, minCostPerHour,
                                                                    volume);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/assembly_line_type_detail_per_category")
  @GET
  @ApiOperation(
      value = "Get assembly line type details per category")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested assembly line type details per category",
              response = RamAssemblyLineTypeDetailPerCategory.class,
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
  public Response getAssemblyLineTypeDetailsPerCategory(
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
                                                        @QueryParam("assemblyLineTypeID") @DefaultValue(
                                                            value = "{ any: true }") @ApiParam(
                                                                name = "assemblyLineTypeID",
                                                                required = false,
                                                                defaultValue = "{ any: true }",
                                                                value = "Assembly line type ID selector") AttributeSelector assemblyLineTypeID,
                                                        @QueryParam("categoryID") @DefaultValue(
                                                            value = "{ any: true }") @ApiParam(
                                                                name = "categoryID",
                                                                required = false,
                                                                defaultValue = "{ any: true }",
                                                                value = "Category ID selector") AttributeSelector categoryID,
                                                        @QueryParam("costMultiplier") @DefaultValue(
                                                            value = "{ any: true }") @ApiParam(
                                                                name = "costMultiplier",
                                                                required = false,
                                                                defaultValue = "{ any: true }",
                                                                value = "Cost multiplier selector") AttributeSelector costMultiplier,
                                                        @QueryParam("materialMultiplier") @DefaultValue(
                                                            value = "{ any: true }") @ApiParam(
                                                                name = "materialMultiplier",
                                                                required = false,
                                                                defaultValue = "{ any: true }",
                                                                value = "Material multiplier selector") AttributeSelector materialMultiplier,
                                                        @QueryParam("timeMultiplier") @DefaultValue(
                                                            value = "{ any: true }") @ApiParam(
                                                                name = "timeMultiplier",
                                                                required = false,
                                                                defaultValue = "{ any: true }",
                                                                value = "Time multiplier selector") AttributeSelector timeMultiplier) {
    ServiceUtil.sanitizeAttributeSelector(assemblyLineTypeID, categoryID, costMultiplier, materialMultiplier, timeMultiplier);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamAssemblyLineTypeDetailPerCategory> result = RamAssemblyLineTypeDetailPerCategory.access(contid, maxresults, assemblyLineTypeID, categoryID,
                                                                                                      costMultiplier, materialMultiplier, timeMultiplier);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/assembly_line_type_detail_per_group")
  @GET
  @ApiOperation(
      value = "Get assembly line type details per group")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested assembly line type details per group",
              response = RamAssemblyLineTypeDetailPerGroup.class,
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
  public Response getAssemblyLineTypeDetailsPerGroup(
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
                                                     @QueryParam("assemblyLineTypeID") @DefaultValue(
                                                         value = "{ any: true }") @ApiParam(
                                                             name = "assemblyLineTypeID",
                                                             required = false,
                                                             defaultValue = "{ any: true }",
                                                             value = "Assembly line type ID selector") AttributeSelector assemblyLineTypeID,
                                                     @QueryParam("groupID") @DefaultValue(
                                                         value = "{ any: true }") @ApiParam(
                                                             name = "groupID",
                                                             required = false,
                                                             defaultValue = "{ any: true }",
                                                             value = "Group ID selector") AttributeSelector groupID,
                                                     @QueryParam("costMultiplier") @DefaultValue(
                                                         value = "{ any: true }") @ApiParam(
                                                             name = "costMultiplier",
                                                             required = false,
                                                             defaultValue = "{ any: true }",
                                                             value = "Cost multiplier selector") AttributeSelector costMultiplier,
                                                     @QueryParam("materialMultiplier") @DefaultValue(
                                                         value = "{ any: true }") @ApiParam(
                                                             name = "materialMultiplier",
                                                             required = false,
                                                             defaultValue = "{ any: true }",
                                                             value = "Material multiplier selector") AttributeSelector materialMultiplier,
                                                     @QueryParam("timeMultiplier") @DefaultValue(
                                                         value = "{ any: true }") @ApiParam(
                                                             name = "timeMultiplier",
                                                             required = false,
                                                             defaultValue = "{ any: true }",
                                                             value = "Time multiplier selector") AttributeSelector timeMultiplier) {
    ServiceUtil.sanitizeAttributeSelector(assemblyLineTypeID, groupID, costMultiplier, materialMultiplier, timeMultiplier);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamAssemblyLineTypeDetailPerGroup> result = RamAssemblyLineTypeDetailPerGroup.access(contid, maxresults, assemblyLineTypeID, groupID, costMultiplier,
                                                                                                materialMultiplier, timeMultiplier);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/installation_type_content")
  @GET
  @ApiOperation(
      value = "Get installation type contents")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested installation type contents",
              response = RamInstallationTypeContent.class,
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
  public Response getInstallationTypeContents(
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
                                              @QueryParam("installationTypeID") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "installationTypeID",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Installation type ID selector") AttributeSelector installationTypeID,
                                              @QueryParam("assemblyLineTypeID") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "assemblyLineTypeID",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Assembly line type ID selector") AttributeSelector assemblyLineTypeID,
                                              @QueryParam("quantity") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "quantity",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Quantity selector") AttributeSelector quantity) {
    ServiceUtil.sanitizeAttributeSelector(installationTypeID, assemblyLineTypeID, quantity);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamInstallationTypeContent> result = RamInstallationTypeContent.access(contid, maxresults, installationTypeID, assemblyLineTypeID, quantity);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_requirement")
  @GET
  @ApiOperation(
      value = "Get type requirements")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type requirements",
              response = RamTypeRequirement.class,
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
  public Response getTypeRequirements(
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
                                      @QueryParam("typeID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "typeID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Type ID selector") AttributeSelector typeID,
                                      @QueryParam("activityID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "activityID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Activity ID selector") AttributeSelector activityID,
                                      @QueryParam("requiredTypeID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "requiredTypeID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Required type ID selector") AttributeSelector requiredTypeID,
                                      @QueryParam("consume") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "consume",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Consume selector") AttributeSelector consume,
                                      @QueryParam("damagePerJob") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "damagePerJob",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Damage per job selector") AttributeSelector damagePerJob,
                                      @QueryParam("level") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "level",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Level selector") AttributeSelector level,
                                      @QueryParam("probability") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "probability",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Probability selector") AttributeSelector probability,
                                      @QueryParam("quantity") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "quantity",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Quantity selector") AttributeSelector quantity,
                                      @QueryParam("raceID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "raceID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Race ID selector") AttributeSelector raceID,
                                      @QueryParam("recycle") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "recycle",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Recycle selector") AttributeSelector recycle) {
    ServiceUtil.sanitizeAttributeSelector(typeID, activityID, requiredTypeID, consume, damagePerJob, level, probability, quantity, raceID, recycle);
    maxresults = Math.min(1000, maxresults);
    try {
      List<RamTypeRequirement> result = RamTypeRequirement.access(contid, maxresults, typeID, activityID, requiredTypeID, consume, damagePerJob, level,
                                                                  probability, quantity, raceID, recycle);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
