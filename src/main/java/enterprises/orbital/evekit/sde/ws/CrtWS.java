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
import enterprises.orbital.evekit.sde.crt.CrtCertificate;
import enterprises.orbital.evekit.sde.crt.CrtClass;
import enterprises.orbital.evekit.sde.crt.CrtRecommendation;
import enterprises.orbital.evekit.sde.crt.CrtRelationship;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/crt")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Certificates"
    },
    produces = "application/json",
    consumes = "application/json")
public class CrtWS {

  @Path("/certificate")
  @GET
  @ApiOperation(
      value = "Get certificates")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested certificates",
              response = CrtCertificate.class,
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
  public Response getCertificates(
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
                                  @QueryParam("certificateID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "certificateID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Certificate ID selector") AttributeSelector certificateID,
                                  @QueryParam("classID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "classID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Class ID selector") AttributeSelector classID,
                                  @QueryParam("corpID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "corpID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Corporation ID selector") AttributeSelector corpID,
                                  @QueryParam("description") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "description",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Description text selector") AttributeSelector description,
                                  @QueryParam("grade") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "grade",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Grade selector") AttributeSelector grade,
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
                                          value = "Icon ID selector") AttributeSelector iconID) {
    ServiceUtil.sanitizeAttributeSelector(certificateID, classID, corpID, description, grade, groupID, iconID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrtCertificate> result = CrtCertificate.access(contid, maxresults, certificateID, classID, corpID, description, grade, groupID, iconID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/class")
  @GET
  @ApiOperation(
      value = "Get certificate classes")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested certificate classes",
              response = CrtClass.class,
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
  public Response getCertificateClasses(
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
                                        @QueryParam("classID") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "classID",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "Class ID selector") AttributeSelector classID,
                                        @QueryParam("className") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "className",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "Class name selector") AttributeSelector className,
                                        @QueryParam("description") @DefaultValue(
                                            value = "{ any: true }") @ApiParam(
                                                name = "description",
                                                required = false,
                                                defaultValue = "{ any: true }",
                                                value = "Description text selector") AttributeSelector description) {
    ServiceUtil.sanitizeAttributeSelector(classID, className, description);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrtClass> result = CrtClass.access(contid, maxresults, classID, className, description);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/recommendation")
  @GET
  @ApiOperation(
      value = "Get certificate recommendations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested certificate recommendations",
              response = CrtRecommendation.class,
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
  public Response getCertificateRecommendations(
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
                                                @QueryParam("recommendationID") @DefaultValue(
                                                    value = "{ any: true }") @ApiParam(
                                                        name = "recommendationID",
                                                        required = false,
                                                        defaultValue = "{ any: true }",
                                                        value = "Recommendation ID selector") AttributeSelector recommendationID,
                                                @QueryParam("certificateID") @DefaultValue(
                                                    value = "{ any: true }") @ApiParam(
                                                        name = "certificateID",
                                                        required = false,
                                                        defaultValue = "{ any: true }",
                                                        value = "Certificate ID selector") AttributeSelector certificateID,
                                                @QueryParam("recommendationLevel") @DefaultValue(
                                                    value = "{ any: true }") @ApiParam(
                                                        name = "recommendationLevel",
                                                        required = false,
                                                        defaultValue = "{ any: true }",
                                                        value = "Rcecommendation level selector") AttributeSelector recommendationLevel,
                                                @QueryParam("shipTypeID") @DefaultValue(
                                                    value = "{ any: true }") @ApiParam(
                                                        name = "shipTypeID",
                                                        required = false,
                                                        defaultValue = "{ any: true }",
                                                        value = "Ship type ID selector") AttributeSelector shipTypeID) {
    ServiceUtil.sanitizeAttributeSelector(recommendationID, certificateID, recommendationLevel, shipTypeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrtRecommendation> result = CrtRecommendation.access(contid, maxresults, recommendationID, certificateID, recommendationLevel, shipTypeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/relationship")
  @GET
  @ApiOperation(
      value = "Get certificate relationships")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested certificate relationships",
              response = CrtRelationship.class,
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
  public Response getCertificateRelationships(
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
                                              @QueryParam("relationshipID") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "relationshipID",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Relationship ID selector") AttributeSelector relationshipID,
                                              @QueryParam("childID") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "childID",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Child ID selector") AttributeSelector childID,
                                              @QueryParam("grade") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "grade",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Grade selector") AttributeSelector grade,
                                              @QueryParam("parentID") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "parentID",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Parent ID selector") AttributeSelector parentID,
                                              @QueryParam("parentLevel") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "parentLevel",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Parent level selector") AttributeSelector parentLevel,
                                              @QueryParam("parentTypeID") @DefaultValue(
                                                  value = "{ any: true }") @ApiParam(
                                                      name = "parentTypeID",
                                                      required = false,
                                                      defaultValue = "{ any: true }",
                                                      value = "Parent type ID selector") AttributeSelector parentTypeID) {
    ServiceUtil.sanitizeAttributeSelector(relationshipID, childID, grade, parentID, parentLevel, parentTypeID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<CrtRelationship> result = CrtRelationship.access(contid, maxresults, relationshipID, childID, grade, parentID, parentLevel, parentTypeID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
