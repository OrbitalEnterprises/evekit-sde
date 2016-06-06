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
import enterprises.orbital.evekit.sde.skn.SknLicense;
import enterprises.orbital.evekit.sde.skn.SknMaterial;
import enterprises.orbital.evekit.sde.skn.SknSkin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/skn")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Skin"
    },
    produces = "application/json",
    consumes = "application/json")
public class SknWS {

  @Path("/license")
  @GET
  @ApiOperation(
      value = "Get licenses")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested licenses",
              response = SknLicense.class,
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
  public Response getLicenses(
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
                              @QueryParam("licenseTypeID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "licenseTypeID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "License type ID selector") AttributeSelector licenseTypeID,
                              @QueryParam("duration") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "duration",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Duration selector") AttributeSelector duration,
                              @QueryParam("skinID") @DefaultValue(
                                  value = "{ any: true }") @ApiParam(
                                      name = "skinID",
                                      required = false,
                                      defaultValue = "{ any: true }",
                                      value = "Skin ID selector") AttributeSelector skinID) {
    ServiceUtil.sanitizeAttributeSelector(licenseTypeID, duration, skinID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<SknLicense> result = SknLicense.access(contid, maxresults, licenseTypeID, duration, skinID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/material")
  @GET
  @ApiOperation(
      value = "Get materials")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested materials",
              response = SknMaterial.class,
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
  public Response getMaterials(
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
                               @QueryParam("skinMaterialID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "skinMaterialID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Skin material ID selector") AttributeSelector skinMaterialID,
                               @QueryParam("colorHull") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "colorHull",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Hull color selector") AttributeSelector colorHull,
                               @QueryParam("colorPrimary") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "colorPrimary",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Primary color selector") AttributeSelector colorPrimary,
                               @QueryParam("colorSecondary") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "colorSecondary",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Secondary color selector") AttributeSelector colorSecondary,
                               @QueryParam("colorWindow") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "colorWindow",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Window color selector") AttributeSelector colorWindow,
                               @QueryParam("displayNameID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "displayNameID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Display name ID selector") AttributeSelector displayNameID,
                               @QueryParam("material") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "material",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Material selector") AttributeSelector material,
                               @QueryParam("materialSetID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "materialSetID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Material set ID selector") AttributeSelector materialSetID) {
    ServiceUtil.sanitizeAttributeSelector(skinMaterialID, colorHull, colorPrimary, colorSecondary, colorWindow, displayNameID, material, materialSetID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<SknMaterial> result = SknMaterial.access(contid, maxresults, skinMaterialID, colorHull, colorPrimary, colorSecondary, colorWindow, displayNameID,
                                                    material, materialSetID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/skin")
  @GET
  @ApiOperation(
      value = "Get skins")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested skins",
              response = SknSkin.class,
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
  public Response getSkins(
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
                           @QueryParam("skinID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "skinID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Skin ID selector") AttributeSelector skinID,
                           @QueryParam("allowCCPDevs") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "allowCCPDevs",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Allow CCP devs flag selector") AttributeSelector allowCCPDevs,
                           @QueryParam("internalName") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "internalName",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Internal name selector") AttributeSelector internalName,
                           @QueryParam("skinMaterialID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "skinMaterialID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Skin material ID selector") AttributeSelector skinMaterialID,
                           @QueryParam("typeID") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "typeID",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Type ID selector") AttributeSelector typeID,
                           @QueryParam("visibleSerenity") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "visibleSerenity",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Visible in Serenity flag selector") AttributeSelector visibleSerenity,
                           @QueryParam("visibleTranquility") @DefaultValue(
                               value = "{ any: true }") @ApiParam(
                                   name = "visibleTranquility",
                                   required = false,
                                   defaultValue = "{ any: true }",
                                   value = "Visible in Tranquility flag selector") AttributeSelector visibleTranquility) {
    ServiceUtil.sanitizeAttributeSelector(skinID, allowCCPDevs, internalName, skinMaterialID, typeID, visibleSerenity, visibleTranquility);
    maxresults = Math.min(1000, maxresults);
    try {
      List<SknSkin> result = SknSkin.access(contid, maxresults, skinID, allowCCPDevs, internalName, skinMaterialID, typeID, visibleSerenity,
                                            visibleTranquility);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
