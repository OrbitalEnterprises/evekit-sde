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
import enterprises.orbital.evekit.sde.dgm.DgmAttributeCategory;
import enterprises.orbital.evekit.sde.dgm.DgmAttributeType;
import enterprises.orbital.evekit.sde.dgm.DgmEffect;
import enterprises.orbital.evekit.sde.dgm.DgmExpression;
import enterprises.orbital.evekit.sde.dgm.DgmMastery;
import enterprises.orbital.evekit.sde.dgm.DgmTrait;
import enterprises.orbital.evekit.sde.dgm.DgmTypeAttribute;
import enterprises.orbital.evekit.sde.dgm.DgmTypeEffect;
import enterprises.orbital.evekit.sde.dgm.DgmTypeMastery;
import enterprises.orbital.evekit.sde.dgm.DgmTypeTrait;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/ws/v20160429/dgm")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Dogma"
    },
    produces = "application/json",
    consumes = "application/json")
public class DgmWS {

  @Path("/attribute_category")
  @GET
  @ApiOperation(
      value = "Get attribute categories")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested attribute categories",
              response = DgmAttributeCategory.class,
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
  public Response getAttributeCategories(
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
                                         @QueryParam("categoryID") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "categoryID",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Category ID selector") AttributeSelector categoryID,
                                         @QueryParam("categoryDescription") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "categoryDescription",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Category description selector") AttributeSelector categoryDescription,
                                         @QueryParam("categoryName") @DefaultValue(
                                             value = "{ any: true }") @ApiParam(
                                                 name = "categoryName",
                                                 required = false,
                                                 defaultValue = "{ any: true }",
                                                 value = "Category name selector") AttributeSelector categoryName) {
    ServiceUtil.sanitizeAttributeSelector(categoryID, categoryDescription, categoryName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmAttributeCategory> result = DgmAttributeCategory.access(contid, maxresults, categoryID, categoryDescription, categoryName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/attribute_type")
  @GET
  @ApiOperation(
      value = "Get attribute types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested attribute types",
              response = DgmAttributeType.class,
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
  public Response getAttributeTypes(
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
                                    @QueryParam("categoryID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "categoryID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Category ID selector") AttributeSelector categoryID,
                                    @QueryParam("defaultValue") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "defaultValue",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Default value selector") AttributeSelector defaultValue,
                                    @QueryParam("description") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "description",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Description text selector") AttributeSelector description,
                                    @QueryParam("displayName") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "displayName",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Display name selector") AttributeSelector displayName,
                                    @QueryParam("highIsGood") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "highIsGood",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "High Is Good flag selector") AttributeSelector highIsGood,
                                    @QueryParam("iconID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "iconID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Icon ID selector") AttributeSelector iconID,
                                    @QueryParam("published") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "published",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Published flag selector") AttributeSelector published,
                                    @QueryParam("stackable") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "stackable",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Stackable flag selector") AttributeSelector stackable,
                                    @QueryParam("unitID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "unitID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Unit ID selector") AttributeSelector unitID) {
    ServiceUtil.sanitizeAttributeSelector(attributeID, attributeName, categoryID, defaultValue, description, displayName, highIsGood, iconID, published,
                                          stackable, unitID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmAttributeType> result = DgmAttributeType.access(contid, maxresults, attributeID, attributeName, categoryID, defaultValue, description,
                                                              displayName, highIsGood, iconID, published, stackable, unitID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/effect")
  @GET
  @ApiOperation(
      value = "Get effects")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested effects",
              response = DgmEffect.class,
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
  public Response getEffects(
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
                             @QueryParam("effectID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "effectID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Effect ID selector") AttributeSelector effectID,
                             @QueryParam("description") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "description",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Description text selector") AttributeSelector description,
                             @QueryParam("disallowAutoRepeat") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "disallowAutoRepeat",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Disallow auto repeat flag selector") AttributeSelector disallowAutoRepeat,
                             @QueryParam("dischargeAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "dischargeAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Discharge attribute ID selector") AttributeSelector dischargeAttributeID,
                             @QueryParam("displayName") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "displayName",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Display name selector") AttributeSelector displayName,
                             @QueryParam("distribution") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "distribution",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Distribution selector") AttributeSelector distribution,
                             @QueryParam("durationAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "durationAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Duration attribute ID selector") AttributeSelector durationAttributeID,
                             @QueryParam("effectCategory") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "effectCategory",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Effect category selector") AttributeSelector effectCategory,
                             @QueryParam("effectName") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "effectName",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Effect name selector") AttributeSelector effectName,
                             @QueryParam("electronicChance") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "electronicChance",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Electronic chance selector") AttributeSelector electronicChance,
                             @QueryParam("falloffAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "falloffAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Falloff attribute ID selector") AttributeSelector falloffAttributeID,
                             @QueryParam("fittingUsageChanceAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "fittingUsageChanceAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Fitting usage chance attribute ID selector") AttributeSelector fittingUsageChanceAttributeID,
                             @QueryParam("guid") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "guid",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "GUID selector") AttributeSelector guid,
                             @QueryParam("iconID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "iconID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Icon ID selector") AttributeSelector iconID,
                             @QueryParam("isAssistance") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "isAssistance",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Is Assistance flag selector") AttributeSelector isAssistance,
                             @QueryParam("isOffensive") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "isOffensive",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Is Offensive flag selector") AttributeSelector isOffensive,
                             @QueryParam("isWarpSafe") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "isWarpSafe",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Is Warp Safe flag selector") AttributeSelector isWarpSafe,
                             @QueryParam("modifierInfo") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "modifierInfo",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Modifier info selector") AttributeSelector modifierInfo,
                             @QueryParam("npcActivationChanceAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "npcActivationChanceAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "NPC activation chance attribute ID selector") AttributeSelector npcActivationChanceAttributeID,
                             @QueryParam("npcUsageChanceAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "npcUsageChanceAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "NPC usage chance attribute ID selector") AttributeSelector npcUsageChanceAttributeID,
                             @QueryParam("postExpression") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "postExpression",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Post expression selector") AttributeSelector postExpression,
                             @QueryParam("preExpression") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "preExpression",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Pre expression selector") AttributeSelector preExpression,
                             @QueryParam("propulsionChance") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "propulsionChance",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Propulsion chance selector") AttributeSelector propulsionChance,
                             @QueryParam("published") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "published",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Published flag selector") AttributeSelector published,
                             @QueryParam("rangeAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "rangeAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Range attribute ID selector") AttributeSelector rangeAttributeID,
                             @QueryParam("rangeChance") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "rangeChance",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Range chance selector") AttributeSelector rangeChance,
                             @QueryParam("sfxName") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "sfxName",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "SFX name selector") AttributeSelector sfxName,
                             @QueryParam("trackingSpeedAttributeID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "trackingSpeedAttributeID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Tracking speed attribute ID selector") AttributeSelector trackingSpeedAttributeID) {
    ServiceUtil.sanitizeAttributeSelector(effectID, description, disallowAutoRepeat, dischargeAttributeID, displayName, distribution, durationAttributeID,
                                          effectCategory, effectName, electronicChance, falloffAttributeID, fittingUsageChanceAttributeID, guid, iconID,
                                          isAssistance, isOffensive, isWarpSafe, modifierInfo, npcActivationChanceAttributeID, npcUsageChanceAttributeID,
                                          postExpression, preExpression, propulsionChance, published, rangeAttributeID, rangeChance, sfxName,
                                          trackingSpeedAttributeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmEffect> result = DgmEffect.access(contid, maxresults, effectID, description, disallowAutoRepeat, dischargeAttributeID, displayName, distribution,
                                                durationAttributeID, effectCategory, effectName, electronicChance, falloffAttributeID,
                                                fittingUsageChanceAttributeID, guid, iconID, isAssistance, isOffensive, isWarpSafe, modifierInfo,
                                                npcActivationChanceAttributeID, npcUsageChanceAttributeID, postExpression, preExpression, propulsionChance,
                                                published, rangeAttributeID, rangeChance, sfxName, trackingSpeedAttributeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/expression")
  @GET
  @ApiOperation(
      value = "Get expressions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested expressions",
              response = DgmExpression.class,
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
  public Response getExpressions(
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
                                 @QueryParam("expressionID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "expressionID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Expression ID selector") AttributeSelector expressionID,
                                 @QueryParam("arg1") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "arg1",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "First argument selector") AttributeSelector arg1,
                                 @QueryParam("arg2") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "arg2",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Second argument selector") AttributeSelector arg2,
                                 @QueryParam("description") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "description",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Description text selector") AttributeSelector description,
                                 @QueryParam("expressionAttributeID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "expressionAttributeID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Expression attribute ID selector") AttributeSelector expressionAttributeID,
                                 @QueryParam("expressionGroupID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "expressionGroupID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Expression group ID selector") AttributeSelector expressionGroupID,
                                 @QueryParam("expressionName") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "expressionName",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Expression name selector") AttributeSelector expressionName,
                                 @QueryParam("expressionTypeID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "expressionTypeID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Expression type ID selector") AttributeSelector expressionTypeID,
                                 @QueryParam("expressionValue") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "expressionValue",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Expression value selector") AttributeSelector expressionValue,
                                 @QueryParam("operandID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "operandID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Operand ID selector") AttributeSelector operandID) {
    ServiceUtil.sanitizeAttributeSelector(expressionID, arg1, arg2, description, expressionAttributeID, expressionGroupID, expressionName, expressionTypeID,
                                          expressionValue, operandID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmExpression> result = DgmExpression.access(contid, maxresults, expressionID, arg1, arg2, description, expressionAttributeID, expressionGroupID,
                                                        expressionName, expressionTypeID, expressionValue, operandID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/mastery")
  @GET
  @ApiOperation(
      value = "Get materies")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested masteries",
              response = DgmMastery.class,
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
  public Response getMasteries(
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
                               @QueryParam("masteryID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "masteryID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Mastery ID selector") AttributeSelector masteryID,
                               @QueryParam("certificateID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "certificateID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Certificate ID selector") AttributeSelector certificateID,
                               @QueryParam("grade") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "grade",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Grade selector") AttributeSelector grade) {
    ServiceUtil.sanitizeAttributeSelector(masteryID, certificateID, grade);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmMastery> result = DgmMastery.access(contid, maxresults, masteryID, certificateID, grade);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/trait")
  @GET
  @ApiOperation(
      value = "Get traits")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested traits",
              response = DgmTrait.class,
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
  public Response getTraits(
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
                            @QueryParam("traitID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "traitID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Trait ID selector") AttributeSelector traitID,
                            @QueryParam("bonusText") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "bonusText",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Bonus text selector") AttributeSelector bonusText,
                            @QueryParam("unitID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "unitID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Unit ID selector") AttributeSelector unitID) {
    ServiceUtil.sanitizeAttributeSelector(traitID, bonusText, unitID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmTrait> result = DgmTrait.access(contid, maxresults, traitID, bonusText, unitID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_attribute")
  @GET
  @ApiOperation(
      value = "Get type attributes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type attributes",
              response = DgmTypeAttribute.class,
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
  public Response getTypeAttributes(
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
                                    @QueryParam("attributeID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "attributeID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Attribute ID selector") AttributeSelector attributeID,
                                    @QueryParam("valueFloat") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "valueFloat",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Float value selector") AttributeSelector valueFloat,
                                    @QueryParam("valueInt") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "valueInt",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Integer value selector") AttributeSelector valueInt) {
    ServiceUtil.sanitizeAttributeSelector(typeID, attributeID, valueFloat, valueInt);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmTypeAttribute> result = DgmTypeAttribute.access(contid, maxresults, typeID, attributeID, valueFloat, valueInt);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_effect")
  @GET
  @ApiOperation(
      value = "Get type effects")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type effects",
              response = DgmTypeEffect.class,
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
  public Response getTypeEffects(
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
                                 @QueryParam("effectID") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "effectID",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Effect ID selector") AttributeSelector effectID,
                                 @QueryParam("isDefault") @DefaultValue(
                                     value = "{ any: true }") @ApiParam(
                                         name = "isDefault",
                                         required = false,
                                         defaultValue = "{ any: true }",
                                         value = "Default boolean selector") AttributeSelector isDefault) {
    ServiceUtil.sanitizeAttributeSelector(typeID, effectID, isDefault);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmTypeEffect> result = DgmTypeEffect.access(contid, maxresults, typeID, effectID, isDefault);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_mastery")
  @GET
  @ApiOperation(
      value = "Get type masteries")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type masteries",
              response = DgmTypeMastery.class,
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
  public Response getTypeMasteries(
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
                                   @QueryParam("masteryID") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "masteryID",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Mastery ID selector") AttributeSelector masteryID) {
    ServiceUtil.sanitizeAttributeSelector(typeID, masteryID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmTypeMastery> result = DgmTypeMastery.access(contid, maxresults, typeID, masteryID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_trait")
  @GET
  @ApiOperation(
      value = "Get type traits")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type traits",
              response = DgmTypeTrait.class,
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
  public Response getTypeTraits(
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
                                @QueryParam("parentTypeID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "parentTypeID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Parent type ID selector") AttributeSelector parentTypeID,
                                @QueryParam("traitID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "traitID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Trait ID selector") AttributeSelector traitID,
                                @QueryParam("bonus") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "bonus",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Bonus selector") AttributeSelector bonus) {
    ServiceUtil.sanitizeAttributeSelector(typeID, parentTypeID, traitID, bonus);
    maxresults = Math.min(1000, maxresults);
    try {
      List<DgmTypeTrait> result = DgmTypeTrait.access(contid, maxresults, typeID, parentTypeID, traitID, bonus);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
