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
import enterprises.orbital.evekit.sde.inv.InvBlueprintType;
import enterprises.orbital.evekit.sde.inv.InvCategory;
import enterprises.orbital.evekit.sde.inv.InvContrabandType;
import enterprises.orbital.evekit.sde.inv.InvControlTowerResource;
import enterprises.orbital.evekit.sde.inv.InvControlTowerResourcePurpose;
import enterprises.orbital.evekit.sde.inv.InvFlag;
import enterprises.orbital.evekit.sde.inv.InvGroup;
import enterprises.orbital.evekit.sde.inv.InvItem;
import enterprises.orbital.evekit.sde.inv.InvMetaGroup;
import enterprises.orbital.evekit.sde.inv.InvMetaType;
import enterprises.orbital.evekit.sde.inv.InvName;
import enterprises.orbital.evekit.sde.inv.InvPosition;
import enterprises.orbital.evekit.sde.inv.InvType;
import enterprises.orbital.evekit.sde.inv.InvTypeMaterial;
import enterprises.orbital.evekit.sde.inv.InvTypeReaction;
import enterprises.orbital.evekit.sde.inv.InvUniqueName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/inv")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Inventory"
    },
    produces = "application/json",
    consumes = "application/json")
public class InvWS {

  @Path("/blueprint_type")
  @GET
  @ApiOperation(
      value = "Get blueprint types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested blueprint types",
              response = InvBlueprintType.class,
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
  public Response getBlueprintTypes(
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
                                    @QueryParam("blueprintTypeID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "blueprintTypeID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Blueprint type ID selector") AttributeSelector blueprintTypeID,
                                    @QueryParam("duplicatingTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "duplicatingTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Duplicating time selector") AttributeSelector duplicatingTime,
                                    @QueryParam("inventionTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "inventionTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Invention time selector") AttributeSelector inventionTime,
                                    @QueryParam("materialModifier") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "materialModifier",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Material modifier selector") AttributeSelector materialModifier,
                                    @QueryParam("maxProductionLimit") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "maxProductionLimit",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Max production limit selector") AttributeSelector maxProductionLimit,
                                    @QueryParam("parentBlueprintTypeID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "parentBlueprintTypeID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Parent blueprint type ID selector") AttributeSelector parentBlueprintTypeID,
                                    @QueryParam("productionTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "productionTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Production time selector") AttributeSelector productionTime,
                                    @QueryParam("productivityModifier") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "productivityModifier",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Productivity modifier selector") AttributeSelector productivityModifier,
                                    @QueryParam("productTypeID") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "productTypeID",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Product type ID selector") AttributeSelector productTypeID,
                                    @QueryParam("researchCopyTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "researchCopyTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Research copy time selector") AttributeSelector researchCopyTime,
                                    @QueryParam("researchMaterialTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "researchMaterialTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Research material time selector") AttributeSelector researchMaterialTime,
                                    @QueryParam("researchProductivityTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "researchProductivityTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Research productivity time selector") AttributeSelector researchProductivityTime,
                                    @QueryParam("researchTechTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "researchTechTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Research tech time selector") AttributeSelector researchTechTime,
                                    @QueryParam("reverseEngineeringTime") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "reverseEngineeringTime",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Reverse engineering time selector") AttributeSelector reverseEngineeringTime,
                                    @QueryParam("techLevel") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "techLevel",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Tech level selector") AttributeSelector techLevel,
                                    @QueryParam("wasteFactor") @DefaultValue(
                                        value = "{ any: true }") @ApiParam(
                                            name = "wasteFactor",
                                            required = false,
                                            defaultValue = "{ any: true }",
                                            value = "Waster factor selector") AttributeSelector wasteFactor) {
    ServiceUtil.sanitizeAttributeSelector(blueprintTypeID, duplicatingTime, inventionTime, materialModifier, maxProductionLimit, parentBlueprintTypeID,
                                          productionTime, productivityModifier, productTypeID, researchCopyTime, researchMaterialTime, researchProductivityTime,
                                          researchTechTime, reverseEngineeringTime, techLevel, wasteFactor);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvBlueprintType> result = InvBlueprintType.access(contid, maxresults, blueprintTypeID, duplicatingTime, inventionTime, materialModifier,
                                                              maxProductionLimit, parentBlueprintTypeID, productionTime, productivityModifier, productTypeID,
                                                              researchCopyTime, researchMaterialTime, researchProductivityTime, researchTechTime,
                                                              reverseEngineeringTime, techLevel, wasteFactor);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/category")
  @GET
  @ApiOperation(
      value = "Get categories")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested categories",
              response = InvCategory.class,
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
  public Response getCategories(
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
                                @QueryParam("categoryName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "categoryName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Category name selector") AttributeSelector categoryName,
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
                                @QueryParam("published") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "published",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Published flag selector") AttributeSelector published) {
    ServiceUtil.sanitizeAttributeSelector(categoryID, categoryName, description, iconID, published);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvCategory> result = InvCategory.access(contid, maxresults, categoryID, categoryName, description, iconID, published);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/contraband_type")
  @GET
  @ApiOperation(
      value = "Get contraband types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested contraband types",
              response = InvContrabandType.class,
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
  public Response getContrabandTypes(
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
                                             value = "Faction ID selector") AttributeSelector factionID,
                                     @QueryParam("typeID") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "typeID",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Type ID selector") AttributeSelector typeID,
                                     @QueryParam("attackMinSec") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "attackMinSec",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Attack minimum security level selector") AttributeSelector attackMinSec,
                                     @QueryParam("confiscateMinSec") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "confiscateMinSec",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Confiscation minimum security level selector") AttributeSelector confiscateMinSec,
                                     @QueryParam("fineByValue") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "fineByValue",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Fine by value selector") AttributeSelector fineByValue,
                                     @QueryParam("standingLoss") @DefaultValue(
                                         value = "{ any: true }") @ApiParam(
                                             name = "standingLoss",
                                             required = false,
                                             defaultValue = "{ any: true }",
                                             value = "Standing loss selector") AttributeSelector standingLoss) {
    ServiceUtil.sanitizeAttributeSelector(factionID, typeID, attackMinSec, confiscateMinSec, fineByValue, standingLoss);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvContrabandType> result = InvContrabandType.access(contid, maxresults, factionID, typeID, attackMinSec, confiscateMinSec, fineByValue,
                                                                standingLoss);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/control_tower_resource")
  @GET
  @ApiOperation(
      value = "Get control tower resources")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested control tower resources",
              response = InvControlTowerResource.class,
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
  public Response getControlTowerResources(
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
                                           @QueryParam("controlTowerTypeID") @DefaultValue(
                                               value = "{ any: true }") @ApiParam(
                                                   name = "controlTowerTypeID",
                                                   required = false,
                                                   defaultValue = "{ any: true }",
                                                   value = "Control tower type ID selector") AttributeSelector controlTowerTypeID,
                                           @QueryParam("resourceTypeID") @DefaultValue(
                                               value = "{ any: true }") @ApiParam(
                                                   name = "resourceTypeID",
                                                   required = false,
                                                   defaultValue = "{ any: true }",
                                                   value = "Resource type ID selector") AttributeSelector resourceTypeID,
                                           @QueryParam("factionID") @DefaultValue(
                                               value = "{ any: true }") @ApiParam(
                                                   name = "factionID",
                                                   required = false,
                                                   defaultValue = "{ any: true }",
                                                   value = "Faction ID selector") AttributeSelector factionID,
                                           @QueryParam("minSecurityLevel") @DefaultValue(
                                               value = "{ any: true }") @ApiParam(
                                                   name = "minSecurityLevel",
                                                   required = false,
                                                   defaultValue = "{ any: true }",
                                                   value = "Minimum security level selector") AttributeSelector minSecurityLevel,
                                           @QueryParam("purpose") @DefaultValue(
                                               value = "{ any: true }") @ApiParam(
                                                   name = "purpose",
                                                   required = false,
                                                   defaultValue = "{ any: true }",
                                                   value = "Purpose selector") AttributeSelector purpose,
                                           @QueryParam("quantity") @DefaultValue(
                                               value = "{ any: true }") @ApiParam(
                                                   name = "quantity",
                                                   required = false,
                                                   defaultValue = "{ any: true }",
                                                   value = "Quantity selector") AttributeSelector quantity) {
    ServiceUtil.sanitizeAttributeSelector(controlTowerTypeID, resourceTypeID, factionID, minSecurityLevel, purpose, quantity);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvControlTowerResource> result = InvControlTowerResource.access(contid, maxresults, controlTowerTypeID, resourceTypeID, factionID, minSecurityLevel,
                                                                            purpose, quantity);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/control_tower_resource_purpose")
  @GET
  @ApiOperation(
      value = "Get control tower resource purposes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested control tower resource purposes",
              response = InvControlTowerResourcePurpose.class,
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
  public Response getControlTowerResourcePurposes(
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
                                                  @QueryParam("purpose") @DefaultValue(
                                                      value = "{ any: true }") @ApiParam(
                                                          name = "purpose",
                                                          required = false,
                                                          defaultValue = "{ any: true }",
                                                          value = "Purpose selector") AttributeSelector purpose,
                                                  @QueryParam("purposeText") @DefaultValue(
                                                      value = "{ any: true }") @ApiParam(
                                                          name = "purposeText",
                                                          required = false,
                                                          defaultValue = "{ any: true }",
                                                          value = "Purpose text selector") AttributeSelector purposeText) {
    ServiceUtil.sanitizeAttributeSelector(purpose, purposeText);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvControlTowerResourcePurpose> result = InvControlTowerResourcePurpose.access(contid, maxresults, purpose, purposeText);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/flag")
  @GET
  @ApiOperation(
      value = "Get flags")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested flags",
              response = InvFlag.class,
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
  public Response getFlags(
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
                           @QueryParam("flagID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "flagID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Flag ID selector") AttributeSelector flagID,
                           @QueryParam("flagName") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "flagName",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Flag name selector") AttributeSelector flagName,
                           @QueryParam("flagText") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "flagText",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Flag text selector") AttributeSelector flagText,
                           @QueryParam("orderID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "orderID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Order ID selector") AttributeSelector orderID) {
    ServiceUtil.sanitizeAttributeSelector(flagID, flagName, flagText, orderID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvFlag> result = InvFlag.access(contid, maxresults, flagID, flagName, flagText, orderID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/group")
  @GET
  @ApiOperation(
      value = "Get groups")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested groups",
              response = InvGroup.class,
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
  public Response getGroups(
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
                            @QueryParam("groupID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "groupID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Group ID selector") AttributeSelector groupID,
                            @QueryParam("allowManufacture") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "allowManufacture",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Allow manufacture flag selector") AttributeSelector allowManufacture,
                            @QueryParam("allowRecycler") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "allowRecycler",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Allow recycler flag selector") AttributeSelector allowRecycler,
                            @QueryParam("anchorable") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "anchorable",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Anchorable flag selector") AttributeSelector anchorable,
                            @QueryParam("anchored") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "anchored",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Anchored flag selector") AttributeSelector anchored,
                            @QueryParam("categoryID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "categoryID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Category ID selector") AttributeSelector categoryID,
                            @QueryParam("description") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "description",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Description text selector") AttributeSelector description,
                            @QueryParam("fittableNonSingleton") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "fittableNonSingleton",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Fittable non-singleton flag selector") AttributeSelector fittableNonSingleton,
                            @QueryParam("groupName") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "groupName",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Group name selector") AttributeSelector groupName,
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
                            @QueryParam("useBasePrice") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "useBasePrice",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Use base price selector") AttributeSelector useBasePrice) {
    ServiceUtil.sanitizeAttributeSelector(groupID, allowManufacture, allowRecycler, anchorable, anchored, categoryID, description, fittableNonSingleton,
                                          groupName, iconID, published, useBasePrice);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvGroup> result = InvGroup.access(contid, maxresults, groupID, allowManufacture, allowRecycler, anchorable, anchored, categoryID, description,
                                              fittableNonSingleton, groupName, iconID, published, useBasePrice);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/item")
  @GET
  @ApiOperation(
      value = "Get items")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested items",
              response = InvItem.class,
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
  public Response getItems(
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
                           @QueryParam("flagID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "flagID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Flag ID selector") AttributeSelector flagID,
                           @QueryParam("locationID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "locationID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Location ID selector") AttributeSelector locationID,
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
                           @QueryParam("typeID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "typeID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Type ID selector") AttributeSelector typeID) {
    ServiceUtil.sanitizeAttributeSelector(itemID, flagID, locationID, ownerID, quantity, typeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvItem> result = InvItem.access(contid, maxresults, itemID, flagID, locationID, ownerID, quantity, typeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/meta_group")
  @GET
  @ApiOperation(
      value = "Get meta groups")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested meta groups",
              response = InvMetaGroup.class,
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
  public Response getMetaGroups(
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
                                @QueryParam("metaGroupID") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "metaGroupID",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Meta group ID selector") AttributeSelector metaGroupID,
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
                                @QueryParam("metaGroupName") @DefaultValue(
                                    value = "{ any: true }") @ApiParam(
                                        name = "metaGroupName",
                                        required = false,
                                        defaultValue = "{ any: true }",
                                        value = "Meta group name selector") AttributeSelector metaGroupName) {
    ServiceUtil.sanitizeAttributeSelector(metaGroupID, description, iconID, metaGroupName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvMetaGroup> result = InvMetaGroup.access(contid, maxresults, metaGroupID, description, iconID, metaGroupName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/meta_type")
  @GET
  @ApiOperation(
      value = "Get meta types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested meta types",
              response = InvMetaType.class,
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
  public Response getMetaTypes(
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
                               @QueryParam("metaGroupID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "metaGroupID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Meta group ID selector") AttributeSelector metaGroupID,
                               @QueryParam("parentTypeID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "parentTypeID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Parent type ID selector") AttributeSelector parentTypeID) {
    ServiceUtil.sanitizeAttributeSelector(typeID, metaGroupID, parentTypeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvMetaType> result = InvMetaType.access(contid, maxresults, typeID, metaGroupID, parentTypeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/name")
  @GET
  @ApiOperation(
      value = "Get names")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested names",
              response = InvName.class,
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
  public Response getNames(
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
                           @QueryParam("itemName") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "itemName",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Item name selector") AttributeSelector itemName) {
    ServiceUtil.sanitizeAttributeSelector(itemID, itemName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvName> result = InvName.access(contid, maxresults, itemID, itemName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/position")
  @GET
  @ApiOperation(
      value = "Get positions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested positions",
              response = InvPosition.class,
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
  public Response getPositions(
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
                               @QueryParam("pitch") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "pitch",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Pitch selector") AttributeSelector pitch,
                               @QueryParam("roll") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "roll",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Roll selector") AttributeSelector roll,
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
                               @QueryParam("yaw") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "yaw",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Yaw selector") AttributeSelector yaw,
                               @QueryParam("z") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "z",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Z selector") AttributeSelector z) {
    ServiceUtil.sanitizeAttributeSelector(itemID, pitch, roll, x, y, yaw, z);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvPosition> result = InvPosition.access(contid, maxresults, itemID, pitch, roll, x, y, yaw, z);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type")
  @GET
  @ApiOperation(
      value = "Get types")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested types",
              response = InvType.class,
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
  public Response getTypes(
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
                           @QueryParam("basePrice") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "basePrice",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Base price selector") AttributeSelector basePrice,
                           @QueryParam("capacity") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "capacity",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Capacity selector") AttributeSelector capacity,
                           @QueryParam("chanceOfDuplicating") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "chanceOfDuplicating",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Chance of duplicating selector") AttributeSelector chanceOfDuplicating,
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
                                   value = "Faction ID selector") AttributeSelector factionID,
                           @QueryParam("graphicID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "graphicID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Graphic ID selector") AttributeSelector graphicID,
                           @QueryParam("groupID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "groupID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Group ID selector") AttributeSelector groupID,
                           @QueryParam("iconID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "iconID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Icon ID selector") AttributeSelector iconID,
                           @QueryParam("marketGroupID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "marketGroupID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Market group ID selector") AttributeSelector marketGroupID,
                           @QueryParam("mass") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "mass",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Mass selector") AttributeSelector mass,
                           @QueryParam("portionSize") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "portionSize",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Portion size selector") AttributeSelector portionSize,
                           @QueryParam("published") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "published",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Published flag selector") AttributeSelector published,
                           @QueryParam("raceID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "raceID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Race ID selector") AttributeSelector raceID,
                           @QueryParam("radius") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "radius",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Radius selector") AttributeSelector radius,
                           @QueryParam("soundID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "soundID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Sound ID selector") AttributeSelector soundID,
                           @QueryParam("typeName") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "typeName",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Type name selector") AttributeSelector typeName,
                           @QueryParam("volume") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "volume",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Volume selector") AttributeSelector volume) {
    ServiceUtil.sanitizeAttributeSelector(typeID, basePrice, capacity, chanceOfDuplicating, description, factionID, graphicID, groupID, iconID, marketGroupID,
                                          mass, portionSize, published, raceID, radius, soundID, typeName, volume);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvType> result = InvType.access(contid, maxresults, typeID, basePrice, capacity, chanceOfDuplicating, description, factionID, graphicID, groupID,
                                            iconID, marketGroupID, mass, portionSize, published, raceID, radius, soundID, typeName, volume);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_material")
  @GET
  @ApiOperation(
      value = "Get type materials")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type materials",
              response = InvTypeMaterial.class,
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
  public Response getTypeMaterials(
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
                                   @QueryParam("materialTypeID") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "materialTypeID",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Material type ID selector") AttributeSelector materialTypeID,
                                   @QueryParam("quantity") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "quantity",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Quantity selector") AttributeSelector quantity) {
    ServiceUtil.sanitizeAttributeSelector(typeID, materialTypeID, quantity);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvTypeMaterial> result = InvTypeMaterial.access(contid, maxresults, typeID, materialTypeID, quantity);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/type_reaction")
  @GET
  @ApiOperation(
      value = "Get type reactions")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested type reactions",
              response = InvTypeReaction.class,
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
  public Response getTypeReactions(
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
                                   @QueryParam("reactionTypeID") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "reactionTypeID",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Reaction type ID selector") AttributeSelector reactionTypeID,
                                   @QueryParam("input") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "input",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Input selector") AttributeSelector input,
                                   @QueryParam("typeID") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "typeID",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Type ID selector") AttributeSelector typeID,
                                   @QueryParam("quantity") @DefaultValue(
                                       value = "{ any: true }") @ApiParam(
                                           name = "quantity",
                                           required = false,
                                           defaultValue = "{ any: true }",
                                           value = "Quantity selector") AttributeSelector quantity) {
    ServiceUtil.sanitizeAttributeSelector(reactionTypeID, input, typeID, quantity);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvTypeReaction> result = InvTypeReaction.access(contid, maxresults, reactionTypeID, input, typeID, quantity);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/unique_name")
  @GET
  @ApiOperation(
      value = "Get unique names")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested unique names",
              response = InvUniqueName.class,
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
  public Response getUniqueNames(
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
                                         value = "Item name selector") AttributeSelector itemName) {
    ServiceUtil.sanitizeAttributeSelector(itemID, groupID, itemName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<InvUniqueName> result = InvUniqueName.access(contid, maxresults, itemID, groupID, itemName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
