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
import enterprises.orbital.evekit.sde.plt.PltSchematic;
import enterprises.orbital.evekit.sde.plt.PltSchematicsPinMap;
import enterprises.orbital.evekit.sde.plt.PltSchematicsTypeMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/plt")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Planet"
    },
    produces = "application/json",
    consumes = "application/json")
public class PltWS {

  @Path("/schematic")
  @GET
  @ApiOperation(
      value = "Get schematics")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested schematics",
              response = PltSchematic.class,
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
  public Response getSchematics(
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
                                @QueryParam("schematicID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "schematicID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Schematic ID selector") AttributeSelector schematicID,
                                @QueryParam("cycleTime") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "cycleTime",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Cycle time selector") AttributeSelector cycleTime,
                                @QueryParam("schematicName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "schematicName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Schematic name selector") AttributeSelector schematicName) {
    ServiceUtil.sanitizeAttributeSelector(schematicID, cycleTime, schematicName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<PltSchematic> result = PltSchematic.access(contid, maxresults, schematicID, cycleTime, schematicName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/schematic_pin_map")
  @GET
  @ApiOperation(
      value = "Get schematic pin maps")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested schematic pin maps",
              response = PltSchematicsPinMap.class,
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
  public Response getSchematicPinMaps(
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
                                      @QueryParam("schematicID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "schematicID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Schematic ID selector") AttributeSelector schematicID,
                                      @QueryParam("pinTypeID") @DefaultValue(
                                          value = "{ any: true }") @ApiParam(
                                              name = "pinTypeID",
                                              required = false,
                                              defaultValue = "{ any: true }",
                                              value = "Pin type ID selector") AttributeSelector pinTypeID) {
    ServiceUtil.sanitizeAttributeSelector(schematicID, pinTypeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<PltSchematicsPinMap> result = PltSchematicsPinMap.access(contid, maxresults, schematicID, pinTypeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/schematic_type_map")
  @GET
  @ApiOperation(
      value = "Get schematic type maps")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested schematic type maps",
              response = PltSchematicsTypeMap.class,
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
  public Response getSchematicTypeMaps(
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
                                       @QueryParam("schematicID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "schematicID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Schematic ID selector") AttributeSelector schematicID,
                                       @QueryParam("typeID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "typeID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Type ID selector") AttributeSelector typeID,
                                       @QueryParam("isInput") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "isInput",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Is input flag selector") AttributeSelector isInput,
                                       @QueryParam("quantity") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "quantity",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Quantity selector") AttributeSelector quantity) {
    ServiceUtil.sanitizeAttributeSelector(schematicID, typeID, isInput, quantity);
    maxresults = Math.min(1000, maxresults);
    try {
      List<PltSchematicsTypeMap> result = PltSchematicsTypeMap.access(contid, maxresults, schematicID, typeID, isInput, quantity);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
