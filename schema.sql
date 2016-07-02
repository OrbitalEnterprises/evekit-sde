-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: evedb_20160628
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agtagents`
--

DROP TABLE IF EXISTS `agtagents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agtagents` (
  `agentID` int(10) unsigned NOT NULL,
  `divisionID` tinyint(3) unsigned DEFAULT NULL,
  `corporationID` int(10) unsigned DEFAULT NULL,
  `locationID` int(10) unsigned DEFAULT NULL,
  `level` tinyint(3) DEFAULT NULL,
  `quality` smallint(6) DEFAULT NULL,
  `agentTypeID` int(10) unsigned DEFAULT NULL,
  `isLocator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`agentID`),
  KEY `agtAgents_IX_corporation` (`corporationID`),
  KEY `agtAgents_IX_station` (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agtagenttypes`
--

DROP TABLE IF EXISTS `agtagenttypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agtagenttypes` (
  `agentTypeID` int(10) unsigned NOT NULL,
  `agentType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`agentTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agtresearchagents`
--

DROP TABLE IF EXISTS `agtresearchagents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agtresearchagents` (
  `agentID` int(10) unsigned NOT NULL,
  `typeID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`agentID`,`typeID`),
  KEY `agtResearchAgents_IX_type` (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chrancestries`
--

DROP TABLE IF EXISTS `chrancestries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chrancestries` (
  `ancestryID` tinyint(3) unsigned NOT NULL,
  `ancestryName` varchar(100) DEFAULT NULL,
  `bloodlineID` tinyint(3) unsigned DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `perception` tinyint(3) DEFAULT NULL,
  `willpower` tinyint(3) DEFAULT NULL,
  `charisma` tinyint(3) DEFAULT NULL,
  `memory` tinyint(3) DEFAULT NULL,
  `intelligence` tinyint(3) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `shortDescription` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ancestryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chrattributes`
--

DROP TABLE IF EXISTS `chrattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chrattributes` (
  `attributeID` tinyint(3) unsigned NOT NULL,
  `attributeName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `shortDescription` varchar(500) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`attributeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chrbloodlines`
--

DROP TABLE IF EXISTS `chrbloodlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chrbloodlines` (
  `bloodlineID` tinyint(3) unsigned NOT NULL,
  `bloodlineName` varchar(100) DEFAULT NULL,
  `raceID` tinyint(3) unsigned DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `maleDescription` varchar(1000) DEFAULT NULL,
  `femaleDescription` varchar(1000) DEFAULT NULL,
  `shipTypeID` int(10) unsigned DEFAULT NULL,
  `corporationID` int(10) unsigned DEFAULT NULL,
  `perception` tinyint(3) DEFAULT NULL,
  `willpower` tinyint(3) DEFAULT NULL,
  `charisma` tinyint(3) DEFAULT NULL,
  `memory` tinyint(3) DEFAULT NULL,
  `intelligence` tinyint(3) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `shortDescription` varchar(500) DEFAULT NULL,
  `shortMaleDescription` varchar(500) DEFAULT NULL,
  `shortFemaleDescription` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`bloodlineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chrfactions`
--

DROP TABLE IF EXISTS `chrfactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chrfactions` (
  `factionID` int(10) unsigned NOT NULL,
  `factionName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `raceIDs` int(10) unsigned DEFAULT NULL,
  `solarSystemID` int(10) unsigned DEFAULT NULL,
  `corporationID` int(10) unsigned DEFAULT NULL,
  `sizeFactor` double DEFAULT NULL,
  `stationCount` smallint(6) DEFAULT NULL,
  `stationSystemCount` smallint(6) DEFAULT NULL,
  `militiaCorporationID` int(10) unsigned DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`factionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chrraces`
--

DROP TABLE IF EXISTS `chrraces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chrraces` (
  `raceID` tinyint(3) unsigned NOT NULL,
  `raceName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `shortDescription` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`raceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crpactivities`
--

DROP TABLE IF EXISTS `crpactivities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crpactivities` (
  `activityID` tinyint(3) unsigned NOT NULL,
  `activityName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`activityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crpnpccorporationdivisions`
--

DROP TABLE IF EXISTS `crpnpccorporationdivisions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crpnpccorporationdivisions` (
  `corporationID` int(10) unsigned NOT NULL,
  `divisionID` tinyint(3) unsigned NOT NULL,
  `size` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`corporationID`,`divisionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crpnpccorporationresearchfields`
--

DROP TABLE IF EXISTS `crpnpccorporationresearchfields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crpnpccorporationresearchfields` (
  `skillID` int(10) unsigned NOT NULL,
  `corporationID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`skillID`,`corporationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crpnpccorporations`
--

DROP TABLE IF EXISTS `crpnpccorporations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crpnpccorporations` (
  `corporationID` int(10) unsigned NOT NULL,
  `size` char(1) DEFAULT NULL,
  `extent` char(1) DEFAULT NULL,
  `solarSystemID` int(10) unsigned DEFAULT NULL,
  `investorID1` int(10) unsigned DEFAULT NULL,
  `investorShares1` tinyint(3) DEFAULT NULL,
  `investorID2` int(10) unsigned DEFAULT NULL,
  `investorShares2` tinyint(3) DEFAULT NULL,
  `investorID3` int(10) unsigned DEFAULT NULL,
  `investorShares3` tinyint(3) DEFAULT NULL,
  `investorID4` int(10) unsigned DEFAULT NULL,
  `investorShares4` tinyint(3) DEFAULT NULL,
  `friendID` int(10) unsigned DEFAULT NULL,
  `enemyID` int(10) unsigned DEFAULT NULL,
  `publicShares` bigint(20) DEFAULT NULL,
  `initialPrice` int(10) DEFAULT NULL,
  `minSecurity` double DEFAULT NULL,
  `scattered` tinyint(1) DEFAULT NULL,
  `fringe` tinyint(3) DEFAULT NULL,
  `corridor` tinyint(3) DEFAULT NULL,
  `hub` tinyint(3) DEFAULT NULL,
  `border` tinyint(3) DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  `sizeFactor` double DEFAULT NULL,
  `stationCount` smallint(6) DEFAULT NULL,
  `stationSystemCount` smallint(6) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`corporationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crpnpccorporationtrades`
--

DROP TABLE IF EXISTS `crpnpccorporationtrades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crpnpccorporationtrades` (
  `corporationID` int(10) unsigned NOT NULL,
  `typeID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`corporationID`,`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crpnpcdivisions`
--

DROP TABLE IF EXISTS `crpnpcdivisions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crpnpcdivisions` (
  `divisionID` tinyint(3) unsigned NOT NULL,
  `divisionName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `leaderType` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`divisionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crtcertificates`
--

DROP TABLE IF EXISTS `crtcertificates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crtcertificates` (
  `certificateID` int(10) unsigned NOT NULL,
  `groupID` smallint(6) unsigned DEFAULT NULL,
  `classID` int(10) unsigned DEFAULT NULL,
  `grade` tinyint(3) DEFAULT NULL,
  `corpID` int(10) unsigned DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`certificateID`),
  KEY `crtCertificates_IX_group` (`groupID`),
  KEY `crtCertificates_IX_class` (`classID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crtclasses`
--

DROP TABLE IF EXISTS `crtclasses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crtclasses` (
  `classID` int(10) unsigned NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `className` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`classID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crtrecommendations`
--

DROP TABLE IF EXISTS `crtrecommendations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crtrecommendations` (
  `recommendationID` int(10) unsigned NOT NULL,
  `shipTypeID` int(10) unsigned DEFAULT NULL,
  `certificateID` int(10) unsigned DEFAULT NULL,
  `recommendationLevel` tinyint(3) NOT NULL,
  PRIMARY KEY (`recommendationID`),
  KEY `crtRecommendations_IX_certificate` (`certificateID`),
  KEY `crtRecommendations_IX_shipType` (`shipTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `crtrelationships`
--

DROP TABLE IF EXISTS `crtrelationships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crtrelationships` (
  `relationshipID` int(10) unsigned NOT NULL,
  `parentID` int(10) unsigned DEFAULT NULL,
  `parentTypeID` int(10) unsigned DEFAULT NULL,
  `parentLevel` tinyint(3) DEFAULT NULL,
  `childID` int(10) unsigned DEFAULT NULL,
  `grade` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`relationshipID`),
  KEY `crtRelationships_IX_child` (`childID`),
  KEY `crtRelationships_IX_parent` (`parentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmattributecategories`
--

DROP TABLE IF EXISTS `dgmattributecategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmattributecategories` (
  `categoryID` tinyint(3) unsigned NOT NULL,
  `categoryName` varchar(50) DEFAULT NULL,
  `categoryDescription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmattributetypes`
--

DROP TABLE IF EXISTS `dgmattributetypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmattributetypes` (
  `attributeID` smallint(6) unsigned NOT NULL,
  `attributeName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `defaultValue` double DEFAULT NULL,
  `published` tinyint(1) DEFAULT NULL,
  `displayName` varchar(150) DEFAULT NULL,
  `unitID` tinyint(3) unsigned DEFAULT NULL,
  `stackable` tinyint(1) DEFAULT NULL,
  `highIsGood` tinyint(1) DEFAULT NULL,
  `categoryID` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`attributeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmeffects`
--

DROP TABLE IF EXISTS `dgmeffects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmeffects` (
  `effectID` smallint(6) unsigned NOT NULL,
  `effectName` varchar(400) DEFAULT NULL,
  `effectCategory` smallint(6) DEFAULT NULL,
  `preExpression` int(10) DEFAULT NULL,
  `postExpression` int(10) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `guid` varchar(60) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `isOffensive` tinyint(1) DEFAULT NULL,
  `isAssistance` tinyint(1) DEFAULT NULL,
  `durationAttributeID` smallint(6) unsigned DEFAULT NULL,
  `trackingSpeedAttributeID` smallint(6) unsigned DEFAULT NULL,
  `dischargeAttributeID` smallint(6) unsigned DEFAULT NULL,
  `rangeAttributeID` smallint(6) unsigned DEFAULT NULL,
  `falloffAttributeID` smallint(6) unsigned DEFAULT NULL,
  `disallowAutoRepeat` tinyint(1) DEFAULT NULL,
  `published` tinyint(1) DEFAULT NULL,
  `displayName` varchar(100) DEFAULT NULL,
  `isWarpSafe` tinyint(1) DEFAULT NULL,
  `rangeChance` tinyint(1) DEFAULT NULL,
  `electronicChance` tinyint(1) DEFAULT NULL,
  `propulsionChance` tinyint(1) DEFAULT NULL,
  `distribution` tinyint(3) unsigned DEFAULT NULL,
  `sfxName` varchar(20) DEFAULT NULL,
  `npcUsageChanceAttributeID` smallint(6) unsigned DEFAULT NULL,
  `npcActivationChanceAttributeID` smallint(6) unsigned DEFAULT NULL,
  `fittingUsageChanceAttributeID` smallint(6) unsigned DEFAULT NULL,
  `modifierInfo` varchar(8000) DEFAULT NULL,
  PRIMARY KEY (`effectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmexpressions`
--

DROP TABLE IF EXISTS `dgmexpressions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmexpressions` (
  `expressionID` int(10) unsigned NOT NULL,
  `operandID` int(10) unsigned DEFAULT NULL,
  `arg1` int(10) unsigned DEFAULT NULL,
  `arg2` int(10) DEFAULT NULL,
  `expressionValue` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `expressionName` varchar(500) DEFAULT NULL,
  `expressionTypeID` int(10) unsigned DEFAULT NULL,
  `expressionGroupID` smallint(6) unsigned DEFAULT NULL,
  `expressionAttributeID` smallint(6) unsigned DEFAULT NULL,
  PRIMARY KEY (`expressionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmmasteries`
--

DROP TABLE IF EXISTS `dgmmasteries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmmasteries` (
  `masteryID` int(10) unsigned NOT NULL,
  `certificateID` int(10) unsigned NOT NULL,
  `grade` tinyint(3) NOT NULL,
  PRIMARY KEY (`masteryID`),
  UNIQUE KEY `dgmMasteries_UC_certificate_grade` (`certificateID`,`grade`),
  KEY `dgmMasteries_IX_certificate` (`certificateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmtypeattributes`
--

DROP TABLE IF EXISTS `dgmtypeattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmtypeattributes` (
  `typeID` int(10) unsigned NOT NULL,
  `attributeID` smallint(6) unsigned NOT NULL,
  `valueInt` int(10) DEFAULT NULL,
  `valueFloat` double DEFAULT NULL,
  PRIMARY KEY (`typeID`,`attributeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmtypeeffects`
--

DROP TABLE IF EXISTS `dgmtypeeffects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmtypeeffects` (
  `typeID` int(10) unsigned NOT NULL,
  `effectID` smallint(6) unsigned NOT NULL,
  `isDefault` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`typeID`,`effectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dgmtypemasteries`
--

DROP TABLE IF EXISTS `dgmtypemasteries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dgmtypemasteries` (
  `typeID` int(10) unsigned NOT NULL,
  `masteryID` smallint(6) unsigned NOT NULL,
  PRIMARY KEY (`typeID`,`masteryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evegraphics`
--

DROP TABLE IF EXISTS `evegraphics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evegraphics` (
  `graphicID` int(10) unsigned NOT NULL,
  `graphicFile` varchar(500) NOT NULL DEFAULT '',
  `description` varchar(8000) NOT NULL DEFAULT '',
  `obsolete` tinyint(1) NOT NULL DEFAULT '0',
  `graphicType` varchar(100) DEFAULT NULL,
  `collidable` tinyint(1) DEFAULT NULL,
  `directoryID` int(10) unsigned DEFAULT NULL,
  `graphicName` varchar(64) NOT NULL DEFAULT '',
  `gfxRaceID` varchar(255) DEFAULT NULL,
  `colorScheme` varchar(255) DEFAULT NULL,
  `sofHullName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`graphicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `eveicons`
--

DROP TABLE IF EXISTS `eveicons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eveicons` (
  `iconID` int(10) unsigned NOT NULL,
  `iconFile` varchar(500) NOT NULL DEFAULT '',
  `description` varchar(8000) NOT NULL DEFAULT '',
  PRIMARY KEY (`iconID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `eveunits`
--

DROP TABLE IF EXISTS `eveunits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eveunits` (
  `unitID` tinyint(3) unsigned NOT NULL,
  `unitName` varchar(100) DEFAULT NULL,
  `displayName` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`unitID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invblueprinttypes`
--

DROP TABLE IF EXISTS `invblueprinttypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invblueprinttypes` (
  `blueprintTypeID` int(10) unsigned NOT NULL,
  `parentBlueprintTypeID` int(10) unsigned DEFAULT NULL,
  `productTypeID` int(10) unsigned DEFAULT NULL,
  `productionTime` int(10) DEFAULT NULL,
  `techLevel` smallint(6) DEFAULT NULL,
  `researchProductivityTime` int(10) DEFAULT NULL,
  `researchMaterialTime` int(10) DEFAULT NULL,
  `researchCopyTime` int(10) DEFAULT NULL,
  `researchTechTime` int(10) DEFAULT NULL,
  `duplicatingTime` int(10) DEFAULT NULL,
  `reverseEngineeringTime` int(10) DEFAULT NULL,
  `inventionTime` int(10) DEFAULT NULL,
  `productivityModifier` int(10) DEFAULT NULL,
  `materialModifier` smallint(6) DEFAULT NULL,
  `wasteFactor` smallint(6) DEFAULT NULL,
  `maxProductionLimit` int(10) DEFAULT NULL,
  PRIMARY KEY (`blueprintTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invcategories`
--

DROP TABLE IF EXISTS `invcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invcategories` (
  `categoryID` int(10) unsigned NOT NULL,
  `categoryName` varchar(100) DEFAULT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `published` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invcontrabandtypes`
--

DROP TABLE IF EXISTS `invcontrabandtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invcontrabandtypes` (
  `factionID` int(10) unsigned NOT NULL,
  `typeID` int(10) unsigned NOT NULL,
  `standingLoss` double DEFAULT NULL,
  `confiscateMinSec` double DEFAULT NULL,
  `fineByValue` double DEFAULT NULL,
  `attackMinSec` double DEFAULT NULL,
  PRIMARY KEY (`factionID`,`typeID`),
  KEY `invContrabandTypes_IX_type` (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invcontroltowerresourcepurposes`
--

DROP TABLE IF EXISTS `invcontroltowerresourcepurposes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invcontroltowerresourcepurposes` (
  `purpose` tinyint(3) unsigned NOT NULL,
  `purposeText` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`purpose`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invcontroltowerresources`
--

DROP TABLE IF EXISTS `invcontroltowerresources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invcontroltowerresources` (
  `controlTowerTypeID` int(10) unsigned NOT NULL,
  `resourceTypeID` int(10) unsigned NOT NULL,
  `purpose` tinyint(3) unsigned DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `minSecurityLevel` double DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`controlTowerTypeID`,`resourceTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invflags`
--

DROP TABLE IF EXISTS `invflags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invflags` (
  `flagID` smallint(6) unsigned NOT NULL,
  `flagName` varchar(200) DEFAULT NULL,
  `flagText` varchar(100) DEFAULT NULL,
  `orderID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`flagID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invgroups`
--

DROP TABLE IF EXISTS `invgroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invgroups` (
  `groupID` int(10) unsigned NOT NULL,
  `categoryID` int(10) unsigned DEFAULT NULL,
  `groupName` varchar(100) DEFAULT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `useBasePrice` tinyint(1) DEFAULT NULL,
  `allowManufacture` tinyint(1) DEFAULT NULL,
  `allowRecycler` tinyint(1) DEFAULT NULL,
  `anchored` tinyint(1) DEFAULT NULL,
  `anchorable` tinyint(1) DEFAULT NULL,
  `fittableNonSingleton` tinyint(1) DEFAULT NULL,
  `published` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`groupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invitems`
--

DROP TABLE IF EXISTS `invitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitems` (
  `itemID` bigint(20) unsigned NOT NULL,
  `typeID` int(10) unsigned NOT NULL,
  `ownerID` int(10) unsigned NOT NULL,
  `locationID` bigint(20) unsigned NOT NULL,
  `flagID` smallint(6) unsigned NOT NULL,
  `quantity` int(10) NOT NULL,
  PRIMARY KEY (`itemID`),
  KEY `items_IX_Location` (`locationID`),
  KEY `items_IX_OwnerLocation` (`ownerID`,`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invmarketgroups`
--

DROP TABLE IF EXISTS `invmarketgroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invmarketgroups` (
  `marketGroupID` int(10) unsigned NOT NULL,
  `parentGroupID` int(10) unsigned DEFAULT NULL,
  `marketGroupName` varchar(100) DEFAULT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `hasTypes` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`marketGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invmetagroups`
--

DROP TABLE IF EXISTS `invmetagroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invmetagroups` (
  `metaGroupID` smallint(6) unsigned NOT NULL,
  `metaGroupName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`metaGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invmetatypes`
--

DROP TABLE IF EXISTS `invmetatypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invmetatypes` (
  `typeID` int(10) unsigned NOT NULL,
  `parentTypeID` int(10) unsigned DEFAULT NULL,
  `metaGroupID` smallint(6) unsigned DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invnames`
--

DROP TABLE IF EXISTS `invnames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invnames` (
  `itemID` bigint(20) unsigned NOT NULL,
  `itemName` varchar(200) NOT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invpositions`
--

DROP TABLE IF EXISTS `invpositions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invpositions` (
  `itemID` bigint(20) unsigned NOT NULL,
  `x` double NOT NULL DEFAULT '0',
  `y` double NOT NULL DEFAULT '0',
  `z` double NOT NULL DEFAULT '0',
  `yaw` double DEFAULT NULL,
  `pitch` double DEFAULT NULL,
  `roll` double DEFAULT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invtypematerials`
--

DROP TABLE IF EXISTS `invtypematerials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invtypematerials` (
  `typeID` int(10) unsigned NOT NULL,
  `materialTypeID` int(10) unsigned NOT NULL,
  `quantity` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`typeID`,`materialTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invtypereactions`
--

DROP TABLE IF EXISTS `invtypereactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invtypereactions` (
  `reactionTypeID` int(10) unsigned NOT NULL,
  `input` tinyint(1) NOT NULL,
  `typeID` int(10) unsigned NOT NULL,
  `quantity` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`reactionTypeID`,`input`,`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invtypes`
--

DROP TABLE IF EXISTS `invtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invtypes` (
  `typeID` int(10) unsigned NOT NULL,
  `groupID` int(10) unsigned DEFAULT NULL,
  `typeName` varchar(100) DEFAULT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `mass` double DEFAULT NULL,
  `volume` double DEFAULT NULL,
  `capacity` double DEFAULT NULL,
  `portionSize` int(10) DEFAULT NULL,
  `raceID` tinyint(3) unsigned DEFAULT NULL,
  `basePrice` decimal(15,2) DEFAULT NULL,
  `published` tinyint(1) DEFAULT NULL,
  `marketGroupID` int(10) unsigned DEFAULT NULL,
  `chanceOfDuplicating` double DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  `graphicID` int(10) unsigned DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `radius` double DEFAULT NULL,
  `soundID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`typeID`),
  KEY `invTypes_IX_Group` (`groupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invuniquenames`
--

DROP TABLE IF EXISTS `invuniquenames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invuniquenames` (
  `itemID` int(10) unsigned NOT NULL,
  `itemName` varchar(200) NOT NULL,
  `groupID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `invUniqueNames_IX_GroupName` (`groupID`,`itemName`),
  KEY `invUniqueNames_UQ` (`itemName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapcelestialstatistics`
--

DROP TABLE IF EXISTS `mapcelestialstatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapcelestialstatistics` (
  `celestialID` int(10) unsigned NOT NULL,
  `temperature` double DEFAULT NULL,
  `spectralClass` varchar(10) DEFAULT NULL,
  `luminosity` double DEFAULT NULL,
  `age` double DEFAULT NULL,
  `life` double DEFAULT NULL,
  `orbitRadius` double DEFAULT NULL,
  `eccentricity` double DEFAULT NULL,
  `massDust` double DEFAULT NULL,
  `massGas` double DEFAULT NULL,
  `fragmented` tinyint(1) DEFAULT NULL,
  `density` double DEFAULT NULL,
  `surfaceGravity` double DEFAULT NULL,
  `escapeVelocity` double DEFAULT NULL,
  `orbitPeriod` double DEFAULT NULL,
  `rotationRate` double DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `pressure` double DEFAULT NULL,
  `radius` double DEFAULT NULL,
  `mass` double DEFAULT NULL,
  PRIMARY KEY (`celestialID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapconstellationjumps`
--

DROP TABLE IF EXISTS `mapconstellationjumps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapconstellationjumps` (
  `fromRegionID` int(10) unsigned DEFAULT NULL,
  `fromConstellationID` int(10) unsigned NOT NULL,
  `toConstellationID` int(10) unsigned NOT NULL,
  `toRegionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`fromConstellationID`,`toConstellationID`),
  KEY `mapConstellationJumps_IX_fromRegion` (`fromRegionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapconstellations`
--

DROP TABLE IF EXISTS `mapconstellations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapconstellations` (
  `regionID` int(10) unsigned DEFAULT NULL,
  `constellationID` int(10) unsigned NOT NULL,
  `constellationName` varchar(100) DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `xMin` double DEFAULT NULL,
  `xMax` double DEFAULT NULL,
  `yMin` double DEFAULT NULL,
  `yMax` double DEFAULT NULL,
  `zMin` double DEFAULT NULL,
  `zMax` double DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  `radius` double DEFAULT NULL,
  PRIMARY KEY (`constellationID`),
  KEY `mapConstellations_IX_region` (`regionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapdenormalize`
--

DROP TABLE IF EXISTS `mapdenormalize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapdenormalize` (
  `itemID` int(10) unsigned NOT NULL,
  `typeID` int(10) unsigned DEFAULT NULL,
  `groupID` int(10) unsigned DEFAULT NULL,
  `solarSystemID` int(10) unsigned DEFAULT NULL,
  `constellationID` int(10) unsigned DEFAULT NULL,
  `regionID` int(10) unsigned DEFAULT NULL,
  `orbitID` int(10) unsigned DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `radius` double DEFAULT NULL,
  `itemName` varchar(100) DEFAULT NULL,
  `security` double DEFAULT NULL,
  `celestialIndex` tinyint(3) DEFAULT NULL,
  `orbitIndex` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `mapDenormalize_IX_constellation` (`constellationID`),
  KEY `mapDenormalize_IX_groupConstellation` (`groupID`,`constellationID`),
  KEY `mapDenormalize_IX_groupRegion` (`groupID`,`regionID`),
  KEY `mapDenormalize_IX_groupSystem` (`groupID`,`solarSystemID`),
  KEY `mapDenormalize_IX_orbit` (`orbitID`),
  KEY `mapDenormalize_IX_region` (`regionID`),
  KEY `mapDenormalize_IX_system` (`solarSystemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapjumps`
--

DROP TABLE IF EXISTS `mapjumps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapjumps` (
  `stargateID` int(10) unsigned NOT NULL,
  `celestialID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`stargateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maplandmarks`
--

DROP TABLE IF EXISTS `maplandmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maplandmarks` (
  `landmarkID` smallint(6) unsigned NOT NULL,
  `landmarkName` varchar(100) DEFAULT NULL,
  `description` varchar(7000) DEFAULT NULL,
  `locationID` int(10) unsigned DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `radius` double DEFAULT NULL,
  `iconID` int(10) unsigned DEFAULT NULL,
  `importance` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`landmarkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maplocationscenes`
--

DROP TABLE IF EXISTS `maplocationscenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maplocationscenes` (
  `locationID` int(10) unsigned NOT NULL,
  `graphicID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maplocationwormholeclasses`
--

DROP TABLE IF EXISTS `maplocationwormholeclasses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maplocationwormholeclasses` (
  `locationID` int(10) unsigned NOT NULL,
  `wormholeClassID` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapregionjumps`
--

DROP TABLE IF EXISTS `mapregionjumps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapregionjumps` (
  `fromRegionID` int(10) unsigned NOT NULL,
  `toRegionID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fromRegionID`,`toRegionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapregions`
--

DROP TABLE IF EXISTS `mapregions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapregions` (
  `regionID` int(10) unsigned NOT NULL,
  `regionName` varchar(100) DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `xMin` double DEFAULT NULL,
  `xMax` double DEFAULT NULL,
  `yMin` double DEFAULT NULL,
  `yMax` double DEFAULT NULL,
  `zMin` double DEFAULT NULL,
  `zMax` double DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  `radius` double DEFAULT NULL,
  PRIMARY KEY (`regionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapsolarsystemjumps`
--

DROP TABLE IF EXISTS `mapsolarsystemjumps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapsolarsystemjumps` (
  `fromRegionID` int(10) unsigned DEFAULT NULL,
  `fromConstellationID` int(10) unsigned DEFAULT NULL,
  `fromSolarSystemID` int(10) unsigned NOT NULL,
  `toSolarSystemID` int(10) unsigned NOT NULL,
  `toConstellationID` int(10) unsigned DEFAULT NULL,
  `toRegionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`fromSolarSystemID`,`toSolarSystemID`),
  KEY `mapSolarSystemJumps_IX_fromConstellation` (`fromConstellationID`),
  KEY `mapSolarSystemJumps_IX_fromRegion` (`fromRegionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapsolarsystems`
--

DROP TABLE IF EXISTS `mapsolarsystems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapsolarsystems` (
  `regionID` int(10) unsigned DEFAULT NULL,
  `constellationID` int(10) unsigned DEFAULT NULL,
  `solarSystemID` int(10) unsigned NOT NULL,
  `solarSystemName` varchar(100) DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `xMin` double DEFAULT NULL,
  `xMax` double DEFAULT NULL,
  `yMin` double DEFAULT NULL,
  `yMax` double DEFAULT NULL,
  `zMin` double DEFAULT NULL,
  `zMax` double DEFAULT NULL,
  `luminosity` double DEFAULT NULL,
  `border` tinyint(1) DEFAULT NULL,
  `fringe` tinyint(1) DEFAULT NULL,
  `corridor` tinyint(1) DEFAULT NULL,
  `hub` tinyint(1) DEFAULT NULL,
  `international` tinyint(1) DEFAULT NULL,
  `regional` tinyint(1) DEFAULT NULL,
  `constellation` tinyint(1) DEFAULT NULL,
  `security` double DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  `radius` double DEFAULT NULL,
  `sunTypeID` int(10) unsigned DEFAULT NULL,
  `securityClass` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`solarSystemID`),
  KEY `mapSolarSystems_IX_constellation` (`constellationID`),
  KEY `mapSolarSystems_IX_region` (`regionID`),
  KEY `mapSolarSystems_IX_security` (`security`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapuniverse`
--

DROP TABLE IF EXISTS `mapuniverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapuniverse` (
  `universeID` int(10) unsigned NOT NULL,
  `universeName` varchar(100) DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `xMin` double DEFAULT NULL,
  `xMax` double DEFAULT NULL,
  `yMin` double DEFAULT NULL,
  `yMax` double DEFAULT NULL,
  `zMin` double DEFAULT NULL,
  `zMax` double DEFAULT NULL,
  `radius` double DEFAULT NULL,
  PRIMARY KEY (`universeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `planetschematics`
--

DROP TABLE IF EXISTS `planetschematics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planetschematics` (
  `schematicID` smallint(6) unsigned NOT NULL,
  `schematicName` varchar(255) DEFAULT NULL,
  `cycleTime` int(10) DEFAULT NULL,
  PRIMARY KEY (`schematicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `planetschematicspinmap`
--

DROP TABLE IF EXISTS `planetschematicspinmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planetschematicspinmap` (
  `schematicID` smallint(6) unsigned NOT NULL,
  `pinTypeID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`schematicID`,`pinTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `planetschematicstypemap`
--

DROP TABLE IF EXISTS `planetschematicstypemap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planetschematicstypemap` (
  `schematicID` smallint(6) unsigned NOT NULL,
  `typeID` int(10) unsigned NOT NULL,
  `quantity` smallint(6) DEFAULT NULL,
  `isInput` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`schematicID`,`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ramactivities`
--

DROP TABLE IF EXISTS `ramactivities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ramactivities` (
  `activityID` tinyint(3) unsigned NOT NULL,
  `activityName` varchar(100) DEFAULT NULL,
  `iconNo` varchar(5) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `published` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`activityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ramassemblylinestations`
--

DROP TABLE IF EXISTS `ramassemblylinestations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ramassemblylinestations` (
  `stationID` int(10) unsigned NOT NULL,
  `assemblyLineTypeID` tinyint(3) unsigned NOT NULL,
  `quantity` tinyint(3) DEFAULT NULL,
  `stationTypeID` int(10) unsigned DEFAULT NULL,
  `ownerID` int(10) unsigned DEFAULT NULL,
  `solarSystemID` int(10) unsigned DEFAULT NULL,
  `regionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`stationID`,`assemblyLineTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ramassemblylinetypedetailpercategory`
--

DROP TABLE IF EXISTS `ramassemblylinetypedetailpercategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ramassemblylinetypedetailpercategory` (
  `assemblyLineTypeID` tinyint(3) unsigned NOT NULL,
  `categoryID` int(10) unsigned NOT NULL,
  `timeMultiplier` double DEFAULT NULL,
  `materialMultiplier` double DEFAULT NULL,
  `costMultiplier` double DEFAULT NULL,
  PRIMARY KEY (`assemblyLineTypeID`,`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ramassemblylinetypedetailpergroup`
--

DROP TABLE IF EXISTS `ramassemblylinetypedetailpergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ramassemblylinetypedetailpergroup` (
  `assemblyLineTypeID` tinyint(3) unsigned NOT NULL,
  `groupID` int(10) unsigned NOT NULL,
  `timeMultiplier` double DEFAULT NULL,
  `materialMultiplier` double DEFAULT NULL,
  `costMultiplier` double DEFAULT NULL,
  PRIMARY KEY (`assemblyLineTypeID`,`groupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ramassemblylinetypes`
--

DROP TABLE IF EXISTS `ramassemblylinetypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ramassemblylinetypes` (
  `assemblyLineTypeID` tinyint(3) unsigned NOT NULL,
  `assemblyLineTypeName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `baseTimeMultiplier` double DEFAULT NULL,
  `baseMaterialMultiplier` double DEFAULT NULL,
  `baseCostMultiplier` double DEFAULT NULL,
  `volume` double DEFAULT NULL,
  `activityID` tinyint(3) unsigned DEFAULT NULL,
  `minCostPerHour` double DEFAULT NULL,
  PRIMARY KEY (`assemblyLineTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `raminstallationtypecontents`
--

DROP TABLE IF EXISTS `raminstallationtypecontents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raminstallationtypecontents` (
  `installationTypeID` int(10) unsigned NOT NULL,
  `assemblyLineTypeID` tinyint(3) unsigned NOT NULL,
  `quantity` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`installationTypeID`,`assemblyLineTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ramtyperequirements`
--

DROP TABLE IF EXISTS `ramtyperequirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ramtyperequirements` (
  `typeID` int(10) unsigned NOT NULL,
  `activityID` tinyint(3) unsigned NOT NULL,
  `requiredTypeID` int(10) unsigned NOT NULL,
  `quantity` int(10) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `damagePerJob` double DEFAULT NULL,
  `recycle` tinyint(1) DEFAULT NULL,
  `raceID` int(10) unsigned DEFAULT NULL,
  `probability` double DEFAULT NULL,
  `consume` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`typeID`,`activityID`,`requiredTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sknlicenses`
--

DROP TABLE IF EXISTS `sknlicenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sknlicenses` (
  `licenseTypeID` int(10) unsigned NOT NULL,
  `skinID` int(10) unsigned NOT NULL,
  `duration` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`licenseTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sknmaterials`
--

DROP TABLE IF EXISTS `sknmaterials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sknmaterials` (
  `skinMaterialID` int(10) unsigned NOT NULL,
  `materialSetID` int(10) unsigned NOT NULL DEFAULT '0',
  `displayNameID` int(10) unsigned NOT NULL DEFAULT '0',
  `material` varchar(255) NOT NULL DEFAULT '',
  `colorHull` varchar(6) DEFAULT NULL,
  `colorWindow` varchar(6) DEFAULT NULL,
  `colorPrimary` varchar(6) DEFAULT NULL,
  `colorSecondary` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`skinMaterialID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sknskins`
--

DROP TABLE IF EXISTS `sknskins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sknskins` (
  `skinID` int(10) unsigned NOT NULL,
  `internalName` varchar(100) NOT NULL DEFAULT '',
  `skinMaterialID` int(10) unsigned DEFAULT NULL,
  `typeID` int(10) unsigned DEFAULT NULL,
  `allowCCPDevs` tinyint(1) NOT NULL DEFAULT '0',
  `visibleSerenity` tinyint(1) NOT NULL DEFAULT '0',
  `visibleTranquility` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`skinID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staoperations`
--

DROP TABLE IF EXISTS `staoperations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staoperations` (
  `activityID` tinyint(3) unsigned DEFAULT NULL,
  `operationID` tinyint(3) unsigned NOT NULL,
  `operationName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `fringe` tinyint(3) DEFAULT NULL,
  `corridor` tinyint(3) DEFAULT NULL,
  `hub` tinyint(3) DEFAULT NULL,
  `border` tinyint(3) DEFAULT NULL,
  `ratio` tinyint(3) DEFAULT NULL,
  `caldariStationTypeID` int(10) unsigned DEFAULT NULL,
  `minmatarStationTypeID` int(10) unsigned DEFAULT NULL,
  `amarrStationTypeID` int(10) unsigned DEFAULT NULL,
  `gallenteStationTypeID` int(10) unsigned DEFAULT NULL,
  `joveStationTypeID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`operationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staoperationservices`
--

DROP TABLE IF EXISTS `staoperationservices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staoperationservices` (
  `operationID` tinyint(3) unsigned NOT NULL,
  `serviceID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`operationID`,`serviceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staservices`
--

DROP TABLE IF EXISTS `staservices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staservices` (
  `serviceID` int(10) unsigned NOT NULL,
  `serviceName` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`serviceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stastations`
--

DROP TABLE IF EXISTS `stastations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stastations` (
  `stationID` int(10) unsigned NOT NULL,
  `security` double DEFAULT NULL,
  `dockingCostPerVolume` double DEFAULT NULL,
  `maxShipVolumeDockable` double DEFAULT NULL,
  `officeRentalCost` int(10) DEFAULT NULL,
  `operationID` tinyint(3) unsigned DEFAULT NULL,
  `stationTypeID` int(10) unsigned DEFAULT NULL,
  `corporationID` int(10) unsigned DEFAULT NULL,
  `solarSystemID` int(10) unsigned DEFAULT NULL,
  `constellationID` int(10) unsigned DEFAULT NULL,
  `regionID` int(10) unsigned DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `z` double DEFAULT NULL,
  `reprocessingEfficiency` double DEFAULT NULL,
  `reprocessingStationsTake` double DEFAULT NULL,
  `reprocessingHangarFlag` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`stationID`),
  KEY `staStations_IX_constellation` (`corporationID`),
  KEY `staStations_IX_corporation` (`corporationID`),
  KEY `staStations_IX_operation` (`operationID`),
  KEY `staStations_IX_region` (`regionID`),
  KEY `staStations_IX_system` (`solarSystemID`),
  KEY `staStations_IX_type` (`stationTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stastationtypes`
--

DROP TABLE IF EXISTS `stastationtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stastationtypes` (
  `stationTypeID` int(10) unsigned NOT NULL,
  `dockEntryX` double DEFAULT NULL,
  `dockEntryY` double DEFAULT NULL,
  `dockEntryZ` double DEFAULT NULL,
  `dockOrientationX` double DEFAULT NULL,
  `dockOrientationY` double DEFAULT NULL,
  `dockOrientationZ` double DEFAULT NULL,
  `operationID` tinyint(3) unsigned DEFAULT NULL,
  `officeSlots` tinyint(3) DEFAULT NULL,
  `reprocessingEfficiency` double DEFAULT NULL,
  `conquerable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`stationTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `translationtables`
--

DROP TABLE IF EXISTS `translationtables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `translationtables` (
  `sourceTable` varchar(200) NOT NULL,
  `destinationTable` varchar(200) DEFAULT NULL,
  `translatedKey` varchar(200) NOT NULL,
  `tcGroupID` int(10) unsigned DEFAULT NULL,
  `tcID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`sourceTable`,`translatedKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trntranslationcolumns`
--

DROP TABLE IF EXISTS `trntranslationcolumns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trntranslationcolumns` (
  `tcGroupID` smallint(6) unsigned DEFAULT NULL,
  `tcID` smallint(6) unsigned NOT NULL,
  `tableName` varchar(256) NOT NULL,
  `columnName` varchar(128) NOT NULL,
  `masterID` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`tcID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trntranslationlanguages`
--

DROP TABLE IF EXISTS `trntranslationlanguages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trntranslationlanguages` (
  `numericLanguageID` int(10) unsigned NOT NULL,
  `languageID` varchar(50) DEFAULT NULL,
  `languageName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`numericLanguageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trntranslations`
--

DROP TABLE IF EXISTS `trntranslations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trntranslations` (
  `tcID` smallint(6) unsigned NOT NULL,
  `keyID` int(10) unsigned NOT NULL,
  `languageID` varchar(50) NOT NULL,
  `text` varchar(8000) NOT NULL,
  PRIMARY KEY (`tcID`,`keyID`,`languageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `warcombatzones`
--

DROP TABLE IF EXISTS `warcombatzones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warcombatzones` (
  `combatZoneID` int(10) NOT NULL DEFAULT '-1',
  `combatZoneName` varchar(100) DEFAULT NULL,
  `factionID` int(10) unsigned DEFAULT NULL,
  `centerSystemID` int(10) unsigned DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`combatZoneID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `warcombatzonesystems`
--

DROP TABLE IF EXISTS `warcombatzonesystems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warcombatzonesystems` (
  `solarSystemID` int(10) unsigned NOT NULL,
  `combatZoneID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`solarSystemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-01 19:56:41
