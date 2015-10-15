--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: changelog_master.groovy
--  Ran at: 10/14/15 12:44 PM
--  Against: root@localhost@jdbc:mysql://localhost:3306/omp
--  Liquibase version: 2.0.5
--  *********************************************************************

--  Changeset default_data.groovy::defaultData-2::marketplace::(Checksum: 3:097db65e7145d138af42a1dc049678ec)
INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Example Category A', '2015-10-14 12:45:11', 'Category A', 'f438527b-da89-4e40-9446-ad9a4703fac5', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Example Category B', '2015-10-14 12:45:11', 'Category B', '53e72c16-f37c-4caf-b8d7-e85becb18f0c', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Example Category C', '2015-10-14 12:45:11', 'Category C', '796c8244-be3c-427e-a9f4-796aff5148ab', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Analytics based on geographic data', '2015-10-14 12:45:11', 'Geospatial', '0f2b554a-36c0-4abe-ab40-e84bd9a25038', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Data set retrieval', '2015-10-14 12:45:11', 'Query', '27b71415-9136-420b-bbb6-d5aa0e93ba0d', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Data set summarization', '2015-10-14 12:45:11', 'Reporting', '87611aaa-2634-4fa4-824c-233d97ec36f3', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-14 12:45:11', 'Amaltics based on temporal data', '2015-10-14 12:45:11', 'Temporal', '85017a76-8213-4b03-ad95-7836bd4817aa', 0);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('marketplace', '', NOW(), 'Insert Row (x7)', 'EXECUTED', 'default_data.groovy', 'defaultData-2', '2.0.5', '3:097db65e7145d138af42a1dc049678ec', 177);

--  Changeset default_data.groovy::defaultData-5::marketplace::(Checksum: 3:508259bc098450e229461dfdfc2ce68b)
INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-14 12:45:11', 'In order to satisfy this criterion, the application must be supported with Tier 1 support so that users can access help for any arising issues.', '2015-10-14 12:45:11', '/marketplace/themes/common/images/scorecard/ScorecardIcons_EMS_lrg.png', 'Is Enterprise Management System (EMS) part of the support structure?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-14 12:45:11', 'In order to satisfy this criterion, the application must be running within the cloud structure. If an application is made up of multiple parts, all parts must be running within the cloud.', '2015-10-14 12:45:11', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudHost_lrg.png', 'Is the application hosted within the infrastructure of the cloud?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-14 12:45:11', 'In order to satisfy this criterion, the application must be able to dynamically handle how many users are trying to access it. For instance, if a low number of users are accessing the App Component a small number of resources are used; if a large number of users are accessing the App Component, the App Component scales to take advantage of additional resources in the cloud.', '2015-10-14 12:45:11', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Scale_lrg.png', 'Does the application elastically scale?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-14 12:45:11', 'In order to satisfy this criterion, the system should operate without constraining the user to interact with it.', '2015-10-14 12:45:11', '/marketplace/themes/common/images/scorecard/ScorecardIcons_LicenseFree_lrg.png', 'Does this system operate without license constraints?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-14 12:45:11', 'In order to satisfy this criterion, the application''''s data must be within cloud storage. If an application utilizes multiple data resources, all parts must utilize cloud storage.', '2015-10-14 12:45:11', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudStorage_lrg.png', 'Is the application data utilizing cloud storage?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-14 12:45:11', 'In order to satisfy this criterion, the application must be accessible via an URL/URI that can be launched by a web browser.', '2015-10-14 12:45:11', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Browser_lrg.png', 'Is the application accessible through a web browser?', 1, 0);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('marketplace', '', NOW(), 'Insert Row (x6)', 'EXECUTED', 'default_data.groovy', 'defaultData-5', '2.0.5', '3:508259bc098450e229461dfdfc2ce68b', 178);

