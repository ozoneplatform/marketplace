-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_master.groovy
-- Ran at: 10/14/15 12:56 PM
-- Against: postgres@jdbc:postgresql://localhost:5432/omp
-- Liquibase version: 2.0.5
-- *********************************************************************

-- Changeset default_data.groovy::defaultData-1::marketplace::(Checksum: 3:8a72896a119302a682eebcb0ab45c9f5)
-- Ensure that the category table has auto-incrementing ids
ALTER TABLE category ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence');

INSERT INTO databasechangelog (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('marketplace', 'Ensure that the category table has auto-incrementing ids', NOW(), 'Custom SQL', 'EXECUTED', 'default_data.groovy', 'defaultData-1', '2.0.5', '3:8a72896a119302a682eebcb0ab45c9f5', 170);

-- Changeset default_data.groovy::defaultData-2::marketplace::(Checksum: 3:e6f8e4784572dabe24ccd980d7113da2)
INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Example Category A', '2015-10-14 12:57:02', 'Category A', '30c9d9c3-5e44-491d-9de8-f5ad239f795b', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Example Category B', '2015-10-14 12:57:02', 'Category B', '8f2ee0a8-781d-4fdd-bdab-9ef6ef35b5de', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Example Category C', '2015-10-14 12:57:02', 'Category C', '75cfb2e6-0a34-47f7-a762-525785a5783e', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Analytics based on geographic data', '2015-10-14 12:57:02', 'Geospatial', '69184cfc-8413-4a62-b911-ec5950137170', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Data set retrieval', '2015-10-14 12:57:02', 'Query', 'a336e57d-e67b-421d-8b0f-575b81cd0371', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Data set summarization', '2015-10-14 12:57:02', 'Reporting', 'a8702b49-b4ac-4aeb-aab9-7602f5c49543', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-14 12:57:02', 'Amaltics based on temporal data', '2015-10-14 12:57:02', 'Temporal', '5f06523a-c1b0-43b2-a6c8-6b6648cd1de9', 0);

INSERT INTO databasechangelog (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('marketplace', '', NOW(), 'Insert Row (x7)', 'EXECUTED', 'default_data.groovy', 'defaultData-2', '2.0.5', '3:e6f8e4784572dabe24ccd980d7113da2', 171);

-- Changeset default_data.groovy::defaultData-5::marketplace::(Checksum: 3:580077db56edb8239df6b3399f2f3df0)
INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-14 12:57:02', 'In order to satisfy this criterion, the application must be supported with Tier 1 support so that users can access help for any arising issues.', '2015-10-14 12:57:02', '/marketplace/themes/common/images/scorecard/ScorecardIcons_EMS_lrg.png', 'Is Enterprise Management System (EMS) part of the support structure?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-14 12:57:02', 'In order to satisfy this criterion, the application must be running within the cloud structure. If an application is made up of multiple parts, all parts must be running within the cloud.', '2015-10-14 12:57:02', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudHost_lrg.png', 'Is the application hosted within the infrastructure of the cloud?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-14 12:57:02', 'In order to satisfy this criterion, the application must be able to dynamically handle how many users are trying to access it. For instance, if a low number of users are accessing the App Component a small number of resources are used; if a large number of users are accessing the App Component, the App Component scales to take advantage of additional resources in the cloud.', '2015-10-14 12:57:02', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Scale_lrg.png', 'Does the application elastically scale?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-14 12:57:02', 'In order to satisfy this criterion, the system should operate without constraining the user to interact with it.', '2015-10-14 12:57:02', '/marketplace/themes/common/images/scorecard/ScorecardIcons_LicenseFree_lrg.png', 'Does this system operate without license constraints?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-14 12:57:02', 'In order to satisfy this criterion, the application''''s data must be within cloud storage. If an application utilizes multiple data resources, all parts must utilize cloud storage.', '2015-10-14 12:57:02', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudStorage_lrg.png', 'Is the application data utilizing cloud storage?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-14 12:57:02', 'In order to satisfy this criterion, the application must be accessible via an URL/URI that can be launched by a web browser.', '2015-10-14 12:57:02', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Browser_lrg.png', 'Is the application accessible through a web browser?', TRUE, 0);

INSERT INTO databasechangelog (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('marketplace', '', NOW(), 'Insert Row (x6)', 'EXECUTED', 'default_data.groovy', 'defaultData-5', '2.0.5', '3:580077db56edb8239df6b3399f2f3df0', 172);

