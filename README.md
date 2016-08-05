# EVE Online Static Data Export (SDE) Library

This module provides library code for accessing the EVE Online Static Data Export (SDE).  Branches of this module normally correspond to release dates of the SDE as determined by [CCP](https://www.ccpgames.com/).

## Database Setup

This module assumes the SDE has been imported into a database supported by [Hibernate](http://hibernate.org/orm/).  Prior to August 2016, we used Desmont McCallock's [EVESDEToSQL](https://bitbucket.org/Desmont_McCallock/evesdetosql/overview) tool to convert the raw SDE to database form.  Unfortunately, Des retired from EVE,
so after this date we use our own [SDE Converter](https://github.com/OrbitalEnterprises/evekit-sde-converter) tool which is a port of Des's tool to Java.  The new tool only works with the new YAML export of the SDE.  See the GitHub page for the converter for instructions on how to create your own conversions.  You'll normally want to clone the branch of this module corresponding to the release you plan to use.

## Configuration  

Check out the [SDE Web Service](https://github.com/OrbitalEnterprises/evekit-sde-ws) project for an example of configuring this module for accessing SDE data.

## Build

We use [Maven](http://maven.apache.org) to build this module.  We currently don't release this module to [Maven Central](http://search.maven.org/) but we may in the future.  Therefore, you'll need to build it yourself.

## Getting Help

The best place to get help is on the [Orbital Forum](https://groups.google.com/forum/#!forum/orbital-enterprises).
