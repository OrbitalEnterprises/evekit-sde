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
import enterprises.orbital.evekit.sde.war.WarCombatZone;
import enterprises.orbital.evekit.sde.war.WarCombatZoneSystem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/war")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "War"
    },
    produces = "application/json",
    consumes = "application/json")
public class WarWS {

  @Path("/combat_zone")
  @GET
  @ApiOperation(
      value = "Get combat zones")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested combat zones",
              response = WarCombatZone.class,
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
  public Response getCombatZones(
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
                                 @QueryParam("combatZoneID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "combatZoneID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Combat zone ID selector") AttributeSelector combatZoneID,
                                 @QueryParam("centerSystemID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "centerSystemID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Center system ID selector") AttributeSelector centerSystemID,
                                 @QueryParam("combatZoneName") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "combatZoneName",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Combat zone name selector") AttributeSelector combatZoneName,
                                 @QueryParam("description") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "description",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Description text selector") AttributeSelector description,
                                 @QueryParam("factionID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "factionID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Faction ID selector") AttributeSelector factionID) {
    ServiceUtil.sanitizeAttributeSelector(combatZoneID, centerSystemID, combatZoneName, description, factionID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<WarCombatZone> result = WarCombatZone.access(contid, maxresults, combatZoneID, centerSystemID, combatZoneName, description, factionID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/combat_zone_system")
  @GET
  @ApiOperation(
      value = "Get combat zone systems")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested combat zone systems",
              response = WarCombatZoneSystem.class,
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
  public Response getCombatZoneSystems(
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
                                       @QueryParam("combatZoneID") @DefaultValue(
                                           value = "{ any: true }") @ApiParam(
                                               name = "combatZoneID",
                                               required = false,
                                               defaultValue = "{ any: true }",
                                               value = "Combat zone ID selector") AttributeSelector combatZoneID) {
    ServiceUtil.sanitizeAttributeSelector(solarSystemID, combatZoneID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<WarCombatZoneSystem> result = WarCombatZoneSystem.access(contid, maxresults, solarSystemID, combatZoneID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
