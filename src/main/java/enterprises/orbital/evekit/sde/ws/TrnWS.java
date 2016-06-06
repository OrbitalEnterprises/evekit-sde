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
import enterprises.orbital.evekit.sde.trn.TrnTranslation;
import enterprises.orbital.evekit.sde.trn.TrnTranslationColumn;
import enterprises.orbital.evekit.sde.trn.TrnTranslationLanguage;
import enterprises.orbital.evekit.sde.trn.TrnTranslationTable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/trn")
@Consumes({
    "application/json"
})
@Produces({
    "application/json"
})
@Api(
    tags = {
        "Translation"
    },
    produces = "application/json",
    consumes = "application/json")
public class TrnWS {

  @Path("/translation")
  @GET
  @ApiOperation(
      value = "Get translations")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested translations",
              response = TrnTranslation.class,
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
  public Response getTranslations(
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
                                  @QueryParam("tcID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "tcID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Translation column ID selector") AttributeSelector tcID,
                                  @QueryParam("keyID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "keyID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Key ID selector") AttributeSelector keyID,
                                  @QueryParam("languageID") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "languageID",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Language ID selector") AttributeSelector languageID,
                                  @QueryParam("text") @DefaultValue(
                                      value = "{ any: true }") @ApiParam(
                                          name = "text",
                                          required = false,
                                          defaultValue = "{ any: true }",
                                          value = "Text selector") AttributeSelector text) {
    ServiceUtil.sanitizeAttributeSelector(tcID, keyID, languageID, text);
    maxresults = Math.min(1000, maxresults);
    try {
      List<TrnTranslation> result = TrnTranslation.access(contid, maxresults, tcID, keyID, languageID, text);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/column")
  @GET
  @ApiOperation(
      value = "Get columns")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested columns",
              response = TrnTranslationColumn.class,
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
  public Response getColumns(
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
                             @QueryParam("tcID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "tcID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Translation column ID selector") AttributeSelector tcID,
                             @QueryParam("columnName") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "columnName",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Column name selector") AttributeSelector columnName,
                             @QueryParam("masterID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "masterID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Master ID selector") AttributeSelector masterID,
                             @QueryParam("tableName") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "tableName",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Table name selector") AttributeSelector tableName,
                             @QueryParam("tcGroupID") @DefaultValue(
                                 value = "{ any: true }") @ApiParam(
                                     name = "tcGroupID",
                                     required = false,
                                     defaultValue = "{ any: true }",
                                     value = "Translation column group ID selector") AttributeSelector tcGroupID) {
    ServiceUtil.sanitizeAttributeSelector(tcID, columnName, masterID, tableName, tcGroupID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<TrnTranslationColumn> result = TrnTranslationColumn.access(contid, maxresults, tcID, columnName, masterID, tableName, tcGroupID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/language")
  @GET
  @ApiOperation(
      value = "Get languages")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested languages",
              response = TrnTranslationLanguage.class,
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
  public Response getLanguages(
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
                               @QueryParam("numericLanguageID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "numericLanguageID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Numeric language ID selector") AttributeSelector numericLanguageID,
                               @QueryParam("languageID") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "languageID",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Language ID selector") AttributeSelector languageID,
                               @QueryParam("languageName") @DefaultValue(
                                   value = "{ any: true }") @ApiParam(
                                       name = "languageName",
                                       required = false,
                                       defaultValue = "{ any: true }",
                                       value = "Language name selector") AttributeSelector languageName) {
    ServiceUtil.sanitizeAttributeSelector(numericLanguageID, languageID, languageName);
    maxresults = Math.min(1000, maxresults);
    try {
      List<TrnTranslationLanguage> result = TrnTranslationLanguage.access(contid, maxresults, numericLanguageID, languageID, languageName);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

  @Path("/table")
  @GET
  @ApiOperation(
      value = "Get tables")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "list of requested tables",
              response = TrnTranslationTable.class,
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
  public Response getTables(
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
                            @QueryParam("sourceTable") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "sourceTable",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Source table selector") AttributeSelector sourceTable,
                            @QueryParam("translatedKey") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "translatedKey",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Translated key selector") AttributeSelector translatedKey,
                            @QueryParam("destinationTable") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "destinationTable",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Destination table selector") AttributeSelector destinationTable,
                            @QueryParam("tcGroupID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "tcGroupID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Translation column group ID selector") AttributeSelector tcGroupID,
                            @QueryParam("tcID") @DefaultValue(
                                value = "{ any: true }") @ApiParam(
                                    name = "tcID",
                                    required = false,
                                    defaultValue = "{ any: true }",
                                    value = "Translation column ID selector") AttributeSelector tcID) {
    ServiceUtil.sanitizeAttributeSelector(sourceTable, translatedKey, destinationTable, tcGroupID, tcID);
    maxresults = Math.min(1000, maxresults);
    try {
      List<TrnTranslationTable> result = TrnTranslationTable.access(contid, maxresults, sourceTable, translatedKey, destinationTable, tcGroupID, tcID);
      return ServiceUtil.finish(result, request);
    } catch (NumberFormatException e) {
      ServiceError errMsg = new ServiceError(Status.BAD_REQUEST.getStatusCode(), "An attribute selector contained an illegal value");
      return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
    }
  }

}
