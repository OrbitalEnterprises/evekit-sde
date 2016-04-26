# EVE Online Static Data Export (SDE) Web Service

This module provides a servlet which exposes a conversion of the EVE Online Static Data Export (SDE).  The current SDE is a mix of a SQLite dump, a Microsoft SQLServer dump, and YAML files.  We use [Desmont McCallock's conversion tool](https://bitbucket.org/Desmont_McCallock/evesdetosql/overview) to convert the SDE to MySQL and provide a web service which exposes query methods for each of the tables.  The web service we expose is a REST API with [Swagger](http://swagger.io) annotations, which in turn makes it trivial to generate documentation and experiment with the API, as well as generate client libraries in various languages.

We maintain a [public instance](https://evekit-sde.orbital.enterprises/) of the web service which exposes the last two SDE releases.  To track changes between releases, we create a named release branch of this module corresponding to each SDE release.  The master branch of this module normally corresponds to the latest SDE release.

The rest of this guide describes how to configure, build and deploy your own instance of this service.

## Database Setup

The service expects you've already imported the SDE into your favorite database vendor.  For the public site, we use MySQL, but for the code below you can use any JDBC and Hibernate friendly vendor.  Desmont McCallock's [EVESDEToSQL](https://bitbucket.org/Desmont_McCallock/evesdetosql/overview) page has excellent documentation on converting the SDE to several SQL vendors.  We don't provide any links to the conversion we use.  You'll need to do your own conversion if you're setting up this service.

You'll normally want to clone the branch of this module corresponding to the release you plan to use.  If you're instantiating the service for an SDE release we don't support, then you'll need to determine whether there are any schema changes that aren't reflected in the code.  While we don't provide the complete SQL dump of each version we release, we DO provide a MySQL schema file that shows the schema of our underlying database.  Look for the file ```schema.sql``` in the top level directory of each release branch.

## Configuration  

The SDE web service requires configuration for database and servlet settings.  Since the SDE web service is normally built with [Maven](http://maven.apache.org), configuration is handled by setting or overriding properties in your local Maven settings.xml file.  The following configuration parameters should be set:

| Parameter | Meaning |
|-----------|---------|
|enterprises.orbital.evekit.sde.ws.basepath|The base location where the servlet is hosted, e.g. http://localhost:8080|
|enterprises.orbital.evekit.sde.ws.appname|Name of the servlet when deployed|
|enterprises.orbital.evekit.sde.jdbc.url|Hibernate JDBC connection URL for SDE instance|
|enterprises.orbital.evekit.sde.jdbc.user|Hibernate JDBC connection user name for SDE instance|
|enterprises.orbital.evekit.sde.jdbc.password|Hibernate JDBC connection password for SDE instance|
|enterprises.orbital.evekit.sde.jdbc.driver_class|Hibernate JDBC driver class name for SDE instance|
|enterprises.orbital.evekit.sde.jdbc.dialect_class|Hibernate dialect class name for SDE instance|

At build and deploy time, the parameters above are substituted into the following files:

* src/main/resources/META-INF/persistence.xml
* src/main/resources/SDE.properties
* src/main/webapp/WEB-INF/web.xml

If you are not using Maven to build, you'll need to substitute these settings manually.

## Build

We use [Maven](http://maven.apache.org) to build this module.  We currently don't release this module to [Maven Central](http://search.maven.org/) but we may in the future.  Therefore, you'll need to build it yourself.

A call to "mvn install" should build and test everything.  We normally only run the unit tests while we're preparing a release for a new SDE.  This is because the unit tests are substantial (read: take a long time to run) and serve mainly to detect schema changes and other mismatches between versions.  The unit tests also require a local database instance to run properly (the properties you configured above are also used for unit tests).  When we're ready to deploy this module, we skip unit tests by passing ```-D skipTests``` to Maven.

## Deployment

This project is designed to easily deploy in a standard Servlet container.  Two parameters need to be substituted in the web.xml file in order for deployment to work correctly:

| Parameter | Meaning |
|-----------|---------|
|enterprises.orbital.evekit.sde.ws.basepath|The base location where the servlet is hosted, e.g. http://localhost:8080|
|enterprises.orbital.evekit.sde.ws.appname|Name of the servlet when deployed|

If you follow the configuration and build instructions above, these parameters will be substituted for you.  These settings are used to define the base path for the REST API endpoints (via Swagger).

### Deploying to Tomcat

The default pom.xml in the project includes the [Tomcat Maven plugin](http://tomcat.apache.org/maven-plugin.html) which makes it easy to deploy directly to a Tomcat instance.  This is normally done by adding two stanzas to your settings.xml:

```xml
<servers>
  <server>
    <id>LocalTomcatServer</id>
    <username>admin</username>
    <password>password</password>
  </server>    
</servers>

<profiles>
  <profile>
    <id>LocalTomcat</id>
    <properties>
      <enterprises.orbital.evekit.sde.ws.tomcat.url>http://127.0.0.1:8080/manager/text</enterprises.orbital.evekit.sde.ws.tomcat.url>
      <enterprises.orbital.evekit.sde.ws.tomcat.server>LocalTomcatServer</enterprises.orbital.evekit.sde.ws.tomcat.server>
      <enterprises.orbital.evekit.sde.ws.tomcat.path>/sde</enterprises.orbital.evekit.sde.ws.tomcat.path>
    </properties>	
  </profile>
</profiles>
```

The first stanza specifies the management credentials for your Tomcat instance.  The second stanza defines the properties needed to install into the server you just defined.  With these settings, you can deploy to your Tomcat instance as follows (this example uses Tomcat 7):

```
mvn -P LocalTomcat tomcat7:deploy
```

If you've already deployed, use "redploy" instead.  See the [Tomcat Maven plugin documentation](http://tomcat.apache.org/maven-plugin-2.2/) for more details on how the deployment plugin works.  Note that the "path" Tomcat parameter should normally agree with "appname" as set in the configuration above.

## Usage

### Viewing Documentation and Trying the API with Swagger

The web page exposed by this module includes a link to the Swagger UI pre-populated with the correct ```swagger.json``` for your instance.  This view will let you experiment with the web service via the Swagger UI.  Use the [Swagger Editor](http://editor.swagger.io/) and import the ```swagger.json``` link (File->Import URL...) to generate a client in your language of choice.

### SDE API Method Structure and Queries

Parameters for the SDE REST API methods follow the same general convention as the [EveKit Model API](https://github.com/OrbitalEnterprises/evekit-model-frontend#model-api-method-structure-and-queries).  Every API method has at least two parameters:

1. The continuation ID for paged results (**contid**).  This value is used as a simple SQL offset into the paged results (minimum value capped at 0).
2. The maximum number of results (**maxresults**) to be returned by the method.

Both parameters are optional with sensible defaults.  Any remaining parameters are selectors on data fields stored in the SDE data.  A selector is a JSON string which can be used to filter results according to the following syntax:

* ```{any: <boolean>}``` Wildcard selector.  If true, then this data field is not used to filter returned data.  Setting this value to false has no effect.
* ```{like: <string>}``` String match selector.  If the associated data field is string valued, then all returned data must satisfy the SQL expression 'field LIKE selector'.  Normal SQL 'LIKE' syntax is allowed (e.g. % as wildcard). 
* ```{values: [<v1>,...,<vn>]}``` Set selector.  The associated data field of each returned data item must contain one of the listed values.
* ```{start: <lower>, end: <upper>}``` Range selector.  The associated data field of each returned data item must satisfy lower <= value <= upper.

This syntax allows very flexible selection on SDE data.  For example, to quickly find the inventory category for ships, set the ```categoryName``` selector to ```{like: "%Ship%" }``` on the inv/category endpoint.  To find all types with "Raven" in the name, set ```typeName``` to ```{like: "%Raven%"}``` on the inv/type endpoint; and, to further restrict this set to those types actually on the market, set ```marketGroupID``` to ```{start: 0, end: 1000000}```.

### SDE API Method Result Example

The SDE web service returns results in JSON format, for example:

```json
[
  {
    "typeID": 17636,
    "basePrice": 108750000,
    "capacity": 625,
    "chanceOfDuplicating": 0,
    "description": "The Navy Issue Raven represents the best the Caldari have to offer on the battlefield: an all-out assault vessel with tremendous electronic warfare capabilities.  Commissioned by Caldari Navy Special Operations Command in YC 104 in response to a campaign of coordinated Gurista attacks which threatened to decimate the populations of several planets in the Obe system, this hefty warship has since proven its worth many times over.",
    "factionID": 500001,
    "graphicID": 2123,
    "groupID": 27,
    "iconID": null,
    "marketGroupID": 1379,
    "mass": 97300000,
    "portionSize": 1,
    "published": 1,
    "raceID": 1,
    "radius": 250,
    "soundID": 20068,
    "typeName": "Raven Navy Issue",
    "volume": 486000
  }
]
```

The fields are exactly those contained in the underlying SDE database.  The SDE web service also returns data in the HTTP Response headers:

* ```Date``` Server time when the result was returned (UTC).
* ```EveKit-SDE-Version``` The name of the SDE release for which results were returned.
* ```expires``` The date when the returned data will expire.  Defaults to 24 hours for SDE results (since this are unchanged between releases).

## Getting Help

The best place to get help is on the [Orbital Forum](https://groups.google.com/forum/#!forum/orbital-enterprises).
