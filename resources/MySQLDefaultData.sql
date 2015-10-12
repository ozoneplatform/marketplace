--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: changelog_master.groovy
--  Ran at: 10/12/15 7:16 AM
--  Against: root@localhost@jdbc:mysql://localhost:3306/omp
--  Liquibase version: 2.0.5
--  *********************************************************************

--  Changeset default_data.groovy::defaultData-2::marketplace::(Checksum: 3:cd41e0d8b824b2f31d6aea045e28f589)
INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Example Category A', '2015-10-12 07:16:25', 'Category A', 'd932ce0c-7319-402a-9376-64d316c534d7', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Example Category B', '2015-10-12 07:16:25', 'Category B', '704fdeaf-0506-4c8b-9241-094bc9b1e94f', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Example Category C', '2015-10-12 07:16:25', 'Category C', '6b1c21b1-fded-4612-84b7-047372aacc0c', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Analytics based on geographic data', '2015-10-12 07:16:25', 'Geospatial', '4abaf0e5-8d3a-4dec-9dbe-86db5521c6f1', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Data set retrieval', '2015-10-12 07:16:25', 'Query', 'ef7d8a2a-b797-4789-b023-2af80248df67', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Data set summarization', '2015-10-12 07:16:25', 'Reporting', '18c5c147-ceba-487c-bb57-3345ec1b1080', 0);

INSERT INTO `category` (`created_date`, `description`, `edited_date`, `title`, `uuid`, `version`) VALUES ('2015-10-12 07:16:25', 'Amaltics based on temporal data', '2015-10-12 07:16:25', 'Temporal', '1586ab39-111d-4375-b6a2-d51ed5f1424f', 0);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('marketplace', '', NOW(), 'Insert Row (x7)', 'EXECUTED', 'default_data.groovy', 'defaultData-2', '2.0.5', '3:cd41e0d8b824b2f31d6aea045e28f589', 177);

--  Changeset default_data.groovy::defaultData-5::marketplace::(Checksum: 3:d42fe857e7d34ea2f3f6cc7171d1ea6a)
INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-12 07:16:25', 'In order to satisfy this criterion, the application must be supported with Tier 1 support so that users can access help for any arising issues.', '2015-10-12 07:16:25', '/marketplace/themes/common/images/scorecard/ScorecardIcons_EMS_lrg.png', 'Is Enterprise Management System (EMS) part of the support structure?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-12 07:16:25', 'In order to satisfy this criterion, the application must be running within the cloud structure. If an application is made up of multiple parts, all parts must be running within the cloud.', '2015-10-12 07:16:25', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudHost_lrg.png', 'Is the application hosted within the infrastructure of the cloud?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-12 07:16:25', 'In order to satisfy this criterion, the application must be able to dynamically handle how many users are trying to access it. For instance, if a low number of users are accessing the App Component a small number of resources are used; if a large number of users are accessing the App Component, the App Component scales to take advantage of additional resources in the cloud.', '2015-10-12 07:16:25', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Scale_lrg.png', 'Does the application elastically scale?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-12 07:16:25', 'In order to satisfy this criterion, the system should operate without constraining the user to interact with it.', '2015-10-12 07:16:25', '/marketplace/themes/common/images/scorecard/ScorecardIcons_LicenseFree_lrg.png', 'Does this system operate without license constraints?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-12 07:16:25', 'In order to satisfy this criterion, the application''''s data must be within cloud storage. If an application utilizes multiple data resources, all parts must utilize cloud storage.', '2015-10-12 07:16:25', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudStorage_lrg.png', 'Is the application data utilizing cloud storage?', 1, 0);

INSERT INTO `score_card_item` (`created_date`, `description`, `edited_date`, `image`, `question`, `show_on_listing`, `version`) VALUES ('2015-10-12 07:16:25', 'In order to satisfy this criterion, the application must be accessible via an URL/URI that can be launched by a web browser.', '2015-10-12 07:16:25', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Browser_lrg.png', 'Is the application accessible through a web browser?', 1, 0);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('marketplace', '', NOW(), 'Insert Row (x6)', 'EXECUTED', 'default_data.groovy', 'defaultData-5', '2.0.5', '3:d42fe857e7d34ea2f3f6cc7171d1ea6a', 178);

