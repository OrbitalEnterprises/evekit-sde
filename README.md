# EVE Online Static Data Export (SDE) Library

This module provides library code for accessing the EVE Online Static Data Export (SDE).  Branches of this module normally correspond to release dates of the SDE as determined by [CCP](https://www.ccpgames.com/).

## Database Setup

This module assumes the SDE has been imported into a database supported by [Hibernate](http://hibernate.org/orm/).  Prior to August 2016, we used Desmont McCallock's [EVESDEToSQL](https://bitbucket.org/Desmont_McCallock/evesdetosql/overview) tool to convert the raw SDE to database form.  Unfortunately, Des retired from EVE,
so after this date we use [Fuzzy Steve's conversions](https://www.fuzzwork.co.uk/dump/) which are created by his [YAML converter](https://github.com/fuzzysteve/yamlloader).

## Configuration  

Check out the [SDE Web Service](https://github.com/OrbitalEnterprises/evekit-sde-ws) project for an example of configuring this module for accessing SDE data.

## Build

We use [Maven](http://maven.apache.org) to build this module.  We currently don't release this module to [Maven Central](http://search.maven.org/) but we may in the future.  Therefore, you'll need to build it yourself.

A call to "mvn install" should build and test everything. We normally only run the unit tests while we're preparing a release for a new SDE.  This is because the unit tests are substantial (read: take a long time to run) and serve mainly to detect schema changes and other mismatches between versions.  The unit tests also require a local database instance to run properly. When we're ready to deploy this module, we skip unit tests by passing -D skipTests to Maven.

If you want to run unit tests, you'll first need to load the SDE into a local database, then set the following Maven configuration properties:

|Property|Meaning|
|--------|----------|
|enterprises.orbital.evekit.sde.jdbc.driver_class|Name of JDBC driver class|
|enterprises.orbital.evekit.sde.jdbc.url|JDBC URL to SDE database instance|
|enterprises.orbital.evekit.sde.jdbc.user|Database user name|
|enterprises.orbital.evekit.sde.jdbc.password|Database password|
|enterprises.orbital.evekit.sde.jdbc.dialect_class|Name of Hibernate JDBC dialect class|

It is good practice to store these settings in a local Maven profile (e.g. named by the version of the SDE you are using).

If you're not using Maven, then you'll need to hand substitute these values into src/test/resources/META-INF/persistence.xml

## Getting Help

The best place to get help is on the [Orbital Forum](https://groups.google.com/forum/#!forum/orbital-enterprises).
