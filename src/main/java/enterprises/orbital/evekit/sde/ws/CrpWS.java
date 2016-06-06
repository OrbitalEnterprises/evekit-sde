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
import enterprises.orbital.evekit.sde.crp.CrpActivity;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporation;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporationDivision;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporationResearchField;
import enterprises.orbital.evekit.sde.crp.CrpNpcCorporationTrade;
import enterprises.orbital.evekit.sde.crp.CrpNpcDivision;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/crp")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Corporation"
    },
    produces = "application/json",
    consumes = "application/json")
public class CrpWS {

  @Path("/activity")
  @GET
  @ApiOperation(
      value = "Get corporation activities")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested corporation activities",
              response = CrpActivity.class,
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
  public Response getCorpActivities(
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
                                            value = "Description text selector") AttributeSelector description) {
    ServiceUtil.sanitizeAttributeSelector(activityID, activityName, description);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrpActivity> result = CrpActivity.access(contid, maxresults, activityID, activityName, description);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/npc_corp")
  @GET
  @ApiOperation(
      value = "Get NPC corporations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested NPC corporations",
              response = CrpNpcCorporation.class,
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
  public Response getNpcCorporations(
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
                                     @QueryParam("corporationID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "corporationID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Corporation ID selector") AttributeSelector corporationID,
                                     @QueryParam("border") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "border",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Border attribute selector") AttributeSelector border,
                                     @QueryParam("corridor") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "corridor",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Corridor attribute selector") AttributeSelector corridor,
                                     @QueryParam("description") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "description",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Description text selector") AttributeSelector description,
                                     @QueryParam("enemyID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "enemyID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Enemy ID selector") AttributeSelector enemyID,
                                     @QueryParam("extent") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "extent",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Extent selector") AttributeSelector extent,
                                     @QueryParam("factionID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "factionID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Faction ID selector") AttributeSelector factionID,
                                     @QueryParam("friendID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "friendID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Friend ID selector") AttributeSelector friendID,
                                     @QueryParam("fringe") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "fringe",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Fringe attribute selector") AttributeSelector fringe,
                                     @QueryParam("hub") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "hub",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Hub attribute selector") AttributeSelector hub,
                                     @QueryParam("iconID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "iconID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Icon ID selector") AttributeSelector iconID,
                                     @QueryParam("initialPrice") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "initialPrice",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Initial price selector") AttributeSelector initialPrice,
                                     @QueryParam("investorID1") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorID1",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "First investor ID selector") AttributeSelector investorID1,
                                     @QueryParam("investorID2") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorID2",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Second investor ID selector") AttributeSelector investorID2,
                                     @QueryParam("investorID3") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorID3",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Third investor ID selector") AttributeSelector investorID3,
                                     @QueryParam("investorID4") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorID4",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Fourth investor ID selector") AttributeSelector investorID4,
                                     @QueryParam("investorShares1") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorShares1",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "First investor shares selector") AttributeSelector investorShares1,
                                     @QueryParam("investorShares2") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorShares2",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Second investor shares selector") AttributeSelector investorShares2,
                                     @QueryParam("investorShares3") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorShares3",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Third investor shares selector") AttributeSelector investorShares3,
                                     @QueryParam("investorShares4") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "investorShares4",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Fourth investor shares selector") AttributeSelector investorShares4,
                                     @QueryParam("minSecurity") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "minSecurity",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Minimum security selector") AttributeSelector minSecurity,
                                     @QueryParam("publicShares") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "publicShares",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Public shares selector") AttributeSelector publicShares,
                                     @QueryParam("scattered") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "scattered",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Scattered attribute selector") AttributeSelector scattered,
                                     @QueryParam("size") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "size",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Size selector") AttributeSelector size,
                                     @QueryParam("sizeFactor") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "sizeFactor",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Size factor selector") AttributeSelector sizeFactor,
                                     @QueryParam("solarSystemID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "solarSystemID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Solar system IDselector") AttributeSelector solarSystemID,
                                     @QueryParam("stationCount") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "stationCount",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Station count selector") AttributeSelector stationCount,
                                     @QueryParam("stationSystemCount") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "stationSystemCount",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Station system count selector") AttributeSelector stationSystemCount) {
    ServiceUtil.sanitizeAttributeSelector(corporationID, border, corridor, description, enemyID, extent, factionID, friendID, fringe, hub, iconID, initialPrice,
                                          investorID1, investorID2, investorID3, investorID4, investorShares1, investorShares2, investorShares3,
                                          investorShares4, minSecurity, publicShares, scattered, size, sizeFactor, solarSystemID, stationCount,
                                          stationSystemCount);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrpNpcCorporation> result = CrpNpcCorporation.access(contid, maxresults, corporationID, border, corridor, description, enemyID, extent, factionID,
                                                                friendID, fringe, hub, iconID, initialPrice, investorID1, investorID2, investorID3, investorID4,
                                                                investorShares1, investorShares2, investorShares3, investorShares4, minSecurity, publicShares,
                                                                scattered, size, sizeFactor, solarSystemID, stationCount, stationSystemCount);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/npc_corp_division")
  @GET
  @ApiOperation(
      value = "Get NPC corporation divisions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested NPC corporation divisions",
              response = CrpNpcCorporationDivision.class,
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
  public Response getNpcCorporationDivisions(
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
                                             @QueryParam("size") @DefaultValue(
                                                 value = "{ any: true }") @ApiParam(
                                                     name = "size",
                                                     required = false,
                                                     defaultValue = "{ any: true }",
                                                     value = "Size selector") AttributeSelector size) {
    ServiceUtil.sanitizeAttributeSelector(corporationID, divisionID, size);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrpNpcCorporationDivision> result = CrpNpcCorporationDivision.access(contid, maxresults, corporationID, divisionID, size);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/npc_corp_research_field")
  @GET
  @ApiOperation(
      value = "Get NPC corporation research fields")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested NPC corporation research fields",
              response = CrpNpcCorporationResearchField.class,
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
  public Response getNpcCorporationResearchFields(
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
                                                  @QueryParam("skillID") @DefaultValue(
                                                      value = "{ any: true }") @ApiParam(
                                                          name = "skillID",
                                                          required = false,
                                                          defaultValue = "{ any: true }",
                                                          value = "Skill ID selector") AttributeSelector skillID,
                                                  @QueryParam("corporationID") @DefaultValue(
                                                      value = "{ any: true }") @ApiParam(
                                                          name = "corporationID",
                                                          required = false,
                                                          defaultValue = "{ any: true }",
                                                          value = "Corporation ID selector") AttributeSelector corporationID) {
    ServiceUtil.sanitizeAttributeSelector(skillID, corporationID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrpNpcCorporationResearchField> result = CrpNpcCorporationResearchField.access(contid, maxresults, skillID, corporationID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/npc_corp_trade")
  @GET
  @ApiOperation(
      value = "Get NPC corporation trades")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested NPC corporation trades",
              response = CrpNpcCorporationTrade.class,
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
  public Response getNpcCorporationTrades(
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
                                          @QueryParam("corporationID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "corporationID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Corporation ID selector") final AttributeSelector corporationID,
                                          @QueryParam("typeID") @DefaultValue(
                                              value = "{ any: true }") @ApiParam(
                                                  name = "typeID",
                                                  required = false,
                                                  defaultValue = "{ any: true }",
                                                  value = "Type ID selector") final AttributeSelector typeID) {
    ServiceUtil.sanitizeAttributeSelector(corporationID, typeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrpNpcCorporationTrade> result = CrpNpcCorporationTrade.access(contid, maxresults, corporationID, typeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/npc_division")
  @GET
  @ApiOperation(
      value = "Get NPC divisions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested NPC divisions",
              response = CrpNpcDivision.class,
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
  public Response getNpcDivisions(
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
                                  @QueryParam("divisionID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "divisionID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Division ID selector") AttributeSelector divisionID,
                                  @QueryParam("description") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "description",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Description text selector") AttributeSelector description,
                                  @QueryParam("divisionName") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "divisionName",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Division name selector") AttributeSelector divisionName,
                                  @QueryParam("leaderType") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "leaderType",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Leader type selector") AttributeSelector leaderType) {
    ServiceUtil.sanitizeAttributeSelector(divisionID, description, divisionName, leaderType);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrpNpcDivision> result = CrpNpcDivision.access(contid, maxresults, divisionID, description, divisionName, leaderType);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
