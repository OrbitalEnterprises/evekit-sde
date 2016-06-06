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
import enterprises.orbital.evekit.sde.chr.ChrAncestry;
import enterprises.orbital.evekit.sde.chr.ChrAttribute;
import enterprises.orbital.evekit.sde.chr.ChrBloodline;
import enterprises.orbital.evekit.sde.chr.ChrFaction;
import enterprises.orbital.evekit.sde.chr.ChrRace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/chr")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Character"
    },
    produces = "application/json",
    consumes = "application/json")
public class ChrWS {

  @Path("/ancestry")
  @GET
  @ApiOperation(
      value = "Get ancestries")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested ancestries",
              response = ChrAncestry.class,
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
  public Response getAncestries(
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
                                @QueryParam("ancestryID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "ancestryID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Ancestry ID selector") AttributeSelector ancestryID,
                                @QueryParam("ancestryName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "ancestryName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Ancestry name selector") AttributeSelector ancestryName,
                                @QueryParam("bloodlineID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "bloodlineID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Bloodline ID selector") AttributeSelector bloodlineID,
                                @QueryParam("charisma") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "charisma",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Charisma value selector") AttributeSelector charisma,
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
                                @QueryParam("intelligence") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "intelligence",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Intelligence value selector") AttributeSelector intelligence,
                                @QueryParam("memory") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "memory",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Memory value selector") AttributeSelector memory,
                                @QueryParam("perception") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "perception",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Perception value selector") AttributeSelector perception,
                                @QueryParam("shortDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "shortDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Short description text selector") AttributeSelector shortDescription,
                                @QueryParam("willpower") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "willpower",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Willpower value selector") AttributeSelector willpower) {
    ServiceUtil.sanitizeAttributeSelector(ancestryID, ancestryName, bloodlineID, charisma, description, iconID, intelligence, memory, perception,
                                          shortDescription, willpower);
    maxresults = Math.min(1000, maxresults);
    try {
      List<ChrAncestry> result = ChrAncestry.access(contid, maxresults, ancestryID, ancestryName, bloodlineID, charisma, description, iconID, intelligence,
                                                    memory, perception, shortDescription, willpower);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/attribute")
  @GET
  @ApiOperation(
      value = "Get attributes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested attributes",
              response = ChrAttribute.class,
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
  public Response getAttributes(
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
                                @QueryParam("attributeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "attributeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Attribute ID selector") AttributeSelector attributeID,
                                @QueryParam("attributeName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "attributeName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Attribute name selector") AttributeSelector attributeName,
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
                                @QueryParam("notes") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "notes",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Notes text selector") AttributeSelector notes,
                                @QueryParam("shortDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "shortDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Short description text selector") AttributeSelector shortDescription) {
    ServiceUtil.sanitizeAttributeSelector(attributeID, attributeName, description, iconID, notes, shortDescription);
    maxresults = Math.min(1000, maxresults);
    try {
      List<ChrAttribute> result = ChrAttribute.access(contid, maxresults, attributeID, attributeName, description, iconID, notes, shortDescription);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/bloodline")
  @GET
  @ApiOperation(
      value = "Get bloodlines")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested bloodlines",
              response = ChrBloodline.class,
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
  public Response getBloodlines(
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
                                @QueryParam("bloodlineID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "bloodlineID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Bloodline ID selector") AttributeSelector bloodlineID,
                                @QueryParam("bloodlineName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "bloodlineName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Bloodline name selector") AttributeSelector bloodlineName,
                                @QueryParam("charisma") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "charisma",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Charisma vlaue selector") AttributeSelector charisma,
                                @QueryParam("corporationID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "corporationID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Corporation ID selector") AttributeSelector corporationID,
                                @QueryParam("description") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "description",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Description text selector") AttributeSelector description,
                                @QueryParam("femaleDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "femaleDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Female description text selector") AttributeSelector femaleDescription,
                                @QueryParam("iconID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "iconID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Icon ID selector") AttributeSelector iconID,
                                @QueryParam("intelligence") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "intelligence",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Intelligence value selector") AttributeSelector intelligence,
                                @QueryParam("maleDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "maleDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Male description text selector") AttributeSelector maleDescription,
                                @QueryParam("memory") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "memory",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Memory value selector") AttributeSelector memory,
                                @QueryParam("perception") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "perception",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Perception value selector") AttributeSelector perception,
                                @QueryParam("raceID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "raceID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Race ID selector") AttributeSelector raceID,
                                @QueryParam("shipTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "shipTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Ship type ID selector") AttributeSelector shipTypeID,
                                @QueryParam("shortDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "shortDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Short description text selector") AttributeSelector shortDescription,
                                @QueryParam("shortFemaleDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "shortFemaleDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Short female description text selector") AttributeSelector shortFemaleDescription,
                                @QueryParam("shortMaleDescription") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "shortMaleDescription",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Short male description text selector") AttributeSelector shortMaleDescription,
                                @QueryParam("willpower") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "willpower",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Willpower value selector") AttributeSelector willpower) {
    ServiceUtil.sanitizeAttributeSelector(bloodlineID, bloodlineName, charisma, corporationID, description, femaleDescription, iconID, intelligence,
                                          maleDescription, memory, perception, raceID, shipTypeID, shortDescription, shortFemaleDescription,
                                          shortMaleDescription, willpower);
    maxresults = Math.min(1000, maxresults);
    try {
      List<ChrBloodline> result = ChrBloodline.access(contid, maxresults, bloodlineID, bloodlineName, charisma, corporationID, description, femaleDescription,
                                                      iconID, intelligence, maleDescription, memory, perception, raceID, shipTypeID, shortDescription,
                                                      shortFemaleDescription, shortMaleDescription, willpower);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/faction")
  @GET
  @ApiOperation(
      value = "Get factions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested factions",
              response = ChrFaction.class,
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
  public Response getFactions(
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
                              @QueryParam("factionID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "factionID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector factionID,
                              @QueryParam("corporationID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "corporationID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector corporationID,
                              @QueryParam("description") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "description",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector description,
                              @QueryParam("factionName") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "factionName",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector factionName,
                              @QueryParam("iconID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "iconID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector iconID,
                              @QueryParam("militiaCorporationID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "militiaCorporationID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector militiaCorporationID,
                              @QueryParam("raceIDs") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "raceIDs",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector raceIDs,
                              @QueryParam("sizeFactor") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "sizeFactor",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector sizeFactor,
                              @QueryParam("solarSystemID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "solarSystemID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector solarSystemID,
                              @QueryParam("stationCount") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "stationCount",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector stationCount,
                              @QueryParam("stationSystemCount") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "stationSystemCount",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Agent ID selector") AttributeSelector stationSystemCount) {
    ServiceUtil.sanitizeAttributeSelector(factionID, corporationID, description, factionName, iconID, militiaCorporationID, raceIDs, sizeFactor, solarSystemID,
                                          stationCount, stationSystemCount);
    maxresults = Math.min(1000, maxresults);
    try {
      List<ChrFaction> result = ChrFaction.access(contid, maxresults, factionID, corporationID, description, factionName, iconID, militiaCorporationID, raceIDs,
                                                  sizeFactor, solarSystemID, stationCount, stationSystemCount);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/race")
  @GET
  @ApiOperation(
      value = "Get races")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested races",
              response = ChrRace.class,
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
  public Response getRaces(
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
                           @QueryParam("raceID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "raceID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Race ID selector") AttributeSelector raceID,
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
                           @QueryParam("raceName") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "raceName",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Race name selector") AttributeSelector raceName,
                           @QueryParam("shortDescription") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "shortDescription",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Short description text selector") AttributeSelector shortDescription) {
    ServiceUtil.sanitizeAttributeSelector(raceID, description, iconID, raceName, shortDescription);
    maxresults = Math.min(1000, maxresults);
    try {
      List<ChrRace> result = ChrRace.access(contid, maxresults, raceID, description, iconID, raceName, shortDescription);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
