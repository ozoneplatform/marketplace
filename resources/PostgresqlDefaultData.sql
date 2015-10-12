-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_master.groovy
-- Ran at: 10/12/15 9:41 AM
-- Against: postgres@jdbc:postgresql://localhost:5432/omp
-- Liquibase version: 2.0.5
-- *********************************************************************

-- Changeset default_data.groovy::defaultData-1::marketplace::(Checksum: 3:8a72896a119302a682eebcb0ab45c9f5)
-- Ensure that the category table has auto-incrementing ids
ALTER TABLE category ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence');

INSERT INTO databasechangelog (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('marketplace', 'Ensure that the category table has auto-incrementing ids', NOW(), 'Custom SQL', 'EXECUTED', 'default_data.groovy', 'defaultData-1', '2.0.5', '3:8a72896a119302a682eebcb0ab45c9f5', 172);

-- Changeset default_data.groovy::defaultData-2::marketplace::(Checksum: 3:70c9b9cce86051ba982ddb4d5536ebba)
INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Example Category A', '2015-10-12 09:42:05', 'Category A', 'c4becee8-69a7-40ea-8354-a668bef5162c', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Example Category B', '2015-10-12 09:42:05', 'Category B', '379d2bee-436b-4eb9-a9b8-5262dcc2d5a0', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Example Category C', '2015-10-12 09:42:05', 'Category C', '94da22b9-de9b-4d18-8f37-7265b934c580', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Analytics based on geographic data', '2015-10-12 09:42:05', 'Geospatial', 'd320a3c0-f713-4cb9-83f6-e52b2574604c', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Data set retrieval', '2015-10-12 09:42:05', 'Query', 'e267fcfe-2db4-4368-9c70-8389d3d621de', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Data set summarization', '2015-10-12 09:42:05', 'Reporting', 'c0bad5e8-e19f-4211-8f13-9a8e1f36be93', 0);

INSERT INTO category (created_date, description, edited_date, title, uuid, "version") VALUES ('2015-10-12 09:42:05', 'Amaltics based on temporal data', '2015-10-12 09:42:05', 'Temporal', '42cec44d-d90b-4a51-b18e-bfe52d14f5da', 0);

INSERT INTO databasechangelog (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('marketplace', '', NOW(), 'Insert Row (x7)', 'EXECUTED', 'default_data.groovy', 'defaultData-2', '2.0.5', '3:70c9b9cce86051ba982ddb4d5536ebba', 173);

-- Changeset default_data.groovy::defaultData-5::marketplace::(Checksum: 3:91f37eacec41e7da77e39ff942658ae5)
INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-12 09:42:05', 'In order to satisfy this criterion, the application must be supported with Tier 1 support so that users can access help for any arising issues.', '2015-10-12 09:42:05', '/marketplace/themes/common/images/scorecard/ScorecardIcons_EMS_lrg.png', 'Is Enterprise Management System (EMS) part of the support structure?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-12 09:42:05', 'In order to satisfy this criterion, the application must be running within the cloud structure. If an application is made up of multiple parts, all parts must be running within the cloud.', '2015-10-12 09:42:05', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudHost_lrg.png', 'Is the application hosted within the infrastructure of the cloud?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-12 09:42:05', 'In order to satisfy this criterion, the application must be able to dynamically handle how many users are trying to access it. For instance, if a low number of users are accessing the App Component a small number of resources are used; if a large number of users are accessing the App Component, the App Component scales to take advantage of additional resources in the cloud.', '2015-10-12 09:42:05', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Scale_lrg.png', 'Does the application elastically scale?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-12 09:42:05', 'In order to satisfy this criterion, the system should operate without constraining the user to interact with it.', '2015-10-12 09:42:05', '/marketplace/themes/common/images/scorecard/ScorecardIcons_LicenseFree_lrg.png', 'Does this system operate without license constraints?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-12 09:42:05', 'In order to satisfy this criterion, the application''''s data must be within cloud storage. If an application utilizes multiple data resources, all parts must utilize cloud storage.', '2015-10-12 09:42:05', '/marketplace/themes/common/images/scorecard/ScorecardIcons_CloudStorage_lrg.png', 'Is the application data utilizing cloud storage?', TRUE, 0);

INSERT INTO score_card_item (created_date, description, edited_date, image, question, show_on_listing, "version") VALUES ('2015-10-12 09:42:05', 'In order to satisfy this criterion, the application must be accessible via an URL/URI that can be launched by a web browser.', '2015-10-12 09:42:05', '/marketplace/themes/common/images/scorecard/ScorecardIcons_Browser_lrg.png', 'Is the application accessible through a web browser?', TRUE, 0);

INSERT INTO databasechangelog (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('marketplace', '', NOW(), 'Insert Row (x6)', 'EXECUTED', 'default_data.groovy', 'defaultData-5', '2.0.5', '3:91f37eacec41e7da77e39ff942658ae5', 174);

