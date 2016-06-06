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
import enterprises.orbital.evekit.sde.agt.AgtAgent;
import enterprises.orbital.evekit.sde.agt.AgtAgentType;
import enterprises.orbital.evekit.sde.agt.AgtResearchAgent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/agt")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Agent"
    },
    produces = "application/json",
    consumes = "application/json")
public class AgtWS {

  @Path("/agent")
  @GET
  @ApiOperation(
      value = "Get agents")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested agents",
              response = AgtAgent.class,
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
  public Response getAgents(
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
                            @QueryParam("agentID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "agentID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Agent ID selector") AttributeSelector agentID,
                            @QueryParam("agentTypeID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "agentTypeID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Agent type ID selector") AttributeSelector agentTypeID,
                            @QueryParam("corporationID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "corporationID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Corporation ID selector") AttributeSelector corporationID,
                            @QueryParam("divisionID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "divisionID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Division ID selector") AttributeSelector divisionID,
                            @QueryParam("isLocator") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "isLocator",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Locator indicator selector") AttributeSelector isLocator,
                            @QueryParam("level") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "level",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Level selector") AttributeSelector level,
                            @QueryParam("locationID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "locationID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Location ID selector") AttributeSelector locationID,
                            @QueryParam("quality") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "quality",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Quality selector") AttributeSelector quality) {
    ServiceUtil.sanitizeAttributeSelector(agentID, agentTypeID, corporationID, divisionID, isLocator, level, locationID, quality);
    maxresults = Math.min(1000, maxresults);
    try {
      List<AgtAgent> result = AgtAgent.access(contid, maxresults, agentID, agentTypeID, corporationID, divisionID, isLocator, level, locationID, quality);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/agentType")
  @GET
  @ApiOperation(
      value = "Get agent types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested agent types",
              response = AgtAgentType.class,
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
  public Response getAgentTypes(
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
                                @QueryParam("agentTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "agentTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Agent type ID selector") AttributeSelector agentTypeID,
                                @QueryParam("agentType") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "agentType",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Agent type name selector") AttributeSelector agentType) {
    ServiceUtil.sanitizeAttributeSelector(agentTypeID, agentType);
    maxresults = Math.min(1000, maxresults);
    try {
      List<AgtAgentType> result = AgtAgentType.access(contid, maxresults, agentTypeID, agentType);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/researchAgent")
  @GET
  @ApiOperation(
      value = "Get research agents")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested research agents",
              response = AgtResearchAgent.class,
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
  public Response getResearchAgents(
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
                                    @QueryParam("agentID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "agentID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Agent ID selector") AttributeSelector agentID,
                                    @QueryParam("typeID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "typeID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Research type ID selector") AttributeSelector typeID) {
    ServiceUtil.sanitizeAttributeSelector(agentID, typeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<AgtResearchAgent> result = AgtResearchAgent.access(contid, maxresults, agentID, typeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
