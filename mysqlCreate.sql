--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: changelog.groovy
--  Ran at: 5/5/15 9:04 AM
--  Against: ozp@10.0.2.2@jdbc:mysql://localhost:3306/ozp
--  Liquibase version: 2.0.5
--  *********************************************************************

--  Create Database Lock Table
CREATE TABLE `DATABASECHANGELOGLOCK` (`ID` INT NOT NULL, `LOCKED` TINYINT(1) NOT NULL, `LOCKGRANTED` DATETIME NULL, `LOCKEDBY` VARCHAR(255) NULL, CONSTRAINT `PK_DATABASECHANGELOGLOCK` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOGLOCK` (`ID`, `LOCKED`) VALUES (1, 0);

--  Lock Database
--  Create Database Change Log Table
CREATE TABLE `DATABASECHANGELOG` (`ID` VARCHAR(63) NOT NULL, `AUTHOR` VARCHAR(63) NOT NULL, `FILENAME` VARCHAR(200) NOT NULL, `DATEEXECUTED` DATETIME NOT NULL, `ORDEREXECUTED` INT NOT NULL, `EXECTYPE` VARCHAR(10) NOT NULL, `MD5SUM` VARCHAR(35) NULL, `DESCRIPTION` VARCHAR(255) NULL, `COMMENTS` VARCHAR(255) NULL, `TAG` VARCHAR(255) NULL, `LIQUIBASE` VARCHAR(20) NULL, CONSTRAINT `PK_DATABASECHANGELOG` PRIMARY KEY (`ID`, `AUTHOR`, `FILENAME`));

--  Changeset changelog.groovy::ozp-rest-0.1.0-1::ozp-rest::(Checksum: 3:1b55e92750620a44677e226bcd9359b5)
CREATE TABLE `profile` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `bio` VARCHAR(1000) NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `display_name` VARCHAR(255) NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `email` VARCHAR(255) NULL, `highest_role` VARCHAR(255) NOT NULL, `last_login` DATETIME NOT NULL, `username` VARCHAR(255) NOT NULL, `launch_in_webtop` TINYINT(1) NOT NULL, CONSTRAINT `profilePK` PRIMARY KEY (`id`));

CREATE INDEX `FKED8E89A97666C6D2` ON `profile`(`created_by_id`);

CREATE INDEX `FKED8E89A9E31CB353` ON `profile`(`edited_by_id`);

CREATE UNIQUE INDEX `UK2e2af56bde6e9cad39c0f4ae1993` ON `profile`(`username`);

ALTER TABLE `profile` ADD CONSTRAINT `FKED8E89A97666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `profile` ADD CONSTRAINT `FKED8E89A9E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `notification` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `message` VARCHAR(150) NULL, `expires_date` DATETIME NOT NULL, CONSTRAINT `notificationPK` PRIMARY KEY (`id`));

CREATE INDEX `notification_created_by_idx` ON `notification`(`created_by_id`);

CREATE INDEX `notification_edited_by_idx` ON `notification`(`edited_by_id`);

CREATE INDEX `notification_expires_date_idx` ON `notification`(`expires_date`);

ALTER TABLE `notification` ADD CONSTRAINT `notification_created_by_idx` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `notification` ADD CONSTRAINT `notification_edited_by_idx` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `profile_dismissed_notifications` (`notification_id` BIGINT NULL, `profile_id` BIGINT NULL);

ALTER TABLE `profile_dismissed_notifications` ADD CONSTRAINT `profile_notification_notification_id_fk` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`);

ALTER TABLE `profile_dismissed_notifications` ADD CONSTRAINT `profile_notification_profile_id_fk` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`);

CREATE TABLE `type` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `description` VARCHAR(255) NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `title` VARCHAR(50) NOT NULL, CONSTRAINT `typePK` PRIMARY KEY (`id`), UNIQUE (`title`));

CREATE INDEX `FK368F3A7666C6D2` ON `type`(`created_by_id`);

CREATE INDEX `FK368F3AE31CB353` ON `type`(`edited_by_id`);

CREATE UNIQUE INDEX `UK57c992e1354f398a8f71f29226dc` ON `type`(`title`);

ALTER TABLE `type` ADD CONSTRAINT `FK368F3A7666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `type` ADD CONSTRAINT `FK368F3AE31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `agency` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `icon_id` binary(16) NULL, `short_name` VARCHAR(8) NOT NULL, `title` VARCHAR(255) NOT NULL, CONSTRAINT `agencyPK` PRIMARY KEY (`id`));

CREATE INDEX `FKAB611C057666C6D2` ON `agency`(`created_by_id`);

CREATE INDEX `FKAB611C05E31CB353` ON `agency`(`edited_by_id`);

CREATE UNIQUE INDEX `UK492f940b0f57ca03abde64501045` ON `agency`(`title`);

CREATE UNIQUE INDEX `short_name_uniq_1414157183392` ON `agency`(`short_name`);

CREATE TABLE `profile_agency` (`profile_organizations_id` BIGINT NULL, `agency_id` BIGINT NULL, `profile_stewarded_organizations_id` BIGINT NULL);

CREATE INDEX `FKB82D0F5B143B24BD` ON `profile_agency`(`agency_id`);

CREATE INDEX `FKB82D0F5B4B3EE656` ON `profile_agency`(`profile_stewarded_organizations_id`);

CREATE INDEX `FKB82D0F5B7AA654D6` ON `profile_agency`(`profile_organizations_id`);

ALTER TABLE `agency` ADD CONSTRAINT `FKAB611C057666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `agency` ADD CONSTRAINT `FKAB611C05E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `profile_agency` ADD CONSTRAINT `FKB82D0F5B143B24BD` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`);

ALTER TABLE `profile_agency` ADD CONSTRAINT `FKB82D0F5B7AA654D6` FOREIGN KEY (`profile_organizations_id`) REFERENCES `profile` (`id`);

ALTER TABLE `profile_agency` ADD CONSTRAINT `FKB82D0F5B4B3EE656` FOREIGN KEY (`profile_stewarded_organizations_id`) REFERENCES `profile` (`id`);

CREATE TABLE `listing_activity` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `action` VARCHAR(255) NOT NULL, `activity_date` DATETIME NOT NULL, `author_id` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `listing_id` BIGINT NOT NULL, `listing_activities_idx` INT NULL, CONSTRAINT `listing_activPK` PRIMARY KEY (`id`));

CREATE INDEX `FK9CE7FE6A5A032135` ON `listing_activity`(`author_id`);

CREATE INDEX `FK9CE7FE6A5A4BEA77` ON `listing_activity`(`listing_id`);

CREATE INDEX `FK9CE7FE6A7666C6D2` ON `listing_activity`(`created_by_id`);

CREATE INDEX `FK9CE7FE6AE31CB353` ON `listing_activity`(`edited_by_id`);

ALTER TABLE `listing_activity` ADD CONSTRAINT `FK9CE7FE6A5A032135` FOREIGN KEY (`author_id`) REFERENCES `profile` (`id`);

ALTER TABLE `listing_activity` ADD CONSTRAINT `FK9CE7FE6A7666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `listing_activity` ADD CONSTRAINT `FK9CE7FE6AE31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `listing` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `agency_id` BIGINT NULL, `approval_status` VARCHAR(255) NOT NULL, `approved_date` DATETIME NULL, `avg_rate` FLOAT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `description` VARCHAR(4000) NULL, `description_short` VARCHAR(150) NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `small_icon_id` binary(16) NULL, `large_icon_id` binary(16) NULL, `banner_icon_id` binary(16) NULL, `featured_banner_icon_id` binary(16) NULL, `is_enabled` bit NOT NULL, `is_featured` bit NULL, `last_activity_id` BIGINT NULL, `launch_url` VARCHAR(2083) NULL, `requirements` VARCHAR(1000) NULL, `title` VARCHAR(255) NOT NULL, `total_comments` INT NOT NULL, `total_rate1` INT NULL, `total_rate2` INT NULL, `total_rate3` INT NULL, `total_rate4` INT NULL, `total_rate5` INT NULL, `total_votes` INT NOT NULL, `type_id` BIGINT NOT NULL, `uuid` VARCHAR(255) NOT NULL, `version_name` VARCHAR(255) NULL, `what_is_new` VARCHAR(250) NULL, `width` INT NULL, `singleton` bit NOT NULL, `height` INT NULL, CONSTRAINT `listingPK` PRIMARY KEY (`id`));

CREATE INDEX `FKAD8BA84143B24BD` ON `listing`(`agency_id`);

CREATE INDEX `FKAD8BA847666C6D2` ON `listing`(`created_by_id`);

CREATE INDEX `FKAD8BA849809495D` ON `listing`(`type_id`);

CREATE INDEX `FKAD8BA84E31CB353` ON `listing`(`edited_by_id`);

CREATE INDEX `FKAD8BA84B803A812` ON `listing`(`last_activity_id`);

CREATE TABLE `listing_profile` (`listing_owners_id` BIGINT NULL, `profile_id` BIGINT NULL);

CREATE TABLE `listing_listing` (`listing_required_id` BIGINT NULL, `listing_id` BIGINT NULL);

CREATE INDEX `FK763057C9C50B9241` ON `listing_listing`(`listing_required_id`);

CREATE INDEX `FK763057C95A4BEA77` ON `listing_listing`(`listing_id`);

CREATE INDEX `FK58E626EE1459EB60` ON `listing_profile`(`listing_owners_id`);

CREATE INDEX `FK58E626EEC0565C57` ON `listing_profile`(`profile_id`);

ALTER TABLE `listing_profile` ADD CONSTRAINT `FK58E626EE1459EB60` FOREIGN KEY (`listing_owners_id`) REFERENCES `listing` (`id`);

ALTER TABLE `listing_profile` ADD CONSTRAINT `FK58E626EEC0565C57` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`);

ALTER TABLE `listing` ADD CONSTRAINT `FKAD8BA84143B24BD` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`);

ALTER TABLE `listing` ADD CONSTRAINT `FKAD8BA849809495D` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`);

ALTER TABLE `listing` ADD CONSTRAINT `FKAD8BA847666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `listing` ADD CONSTRAINT `FKAD8BA84E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `listing_listing` ADD CONSTRAINT `FK763057C9C50B9241` FOREIGN KEY (`listing_required_id`) REFERENCES `listing` (`id`);

ALTER TABLE `listing_listing` ADD CONSTRAINT `FK763057C95A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

ALTER TABLE `listing_activity` ADD CONSTRAINT `FK9CE7FE6A5A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

ALTER TABLE `listing` ADD CONSTRAINT `FKAD8BA84B803A812` FOREIGN KEY (`last_activity_id`) REFERENCES `listing_activity` (`id`);

CREATE TABLE `category` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `description` VARCHAR(255) NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `title` VARCHAR(50) NOT NULL, CONSTRAINT `categoryPK` PRIMARY KEY (`id`), UNIQUE (`title`));

CREATE INDEX `FK302BCFE7666C6D2` ON `category`(`created_by_id`);

CREATE INDEX `FK302BCFEE31CB353` ON `category`(`edited_by_id`);

CREATE UNIQUE INDEX `UK7746033a611e7747013c9a9e2900` ON `category`(`title`);

CREATE TABLE `listing_category` (`listing_categories_id` BIGINT NULL, `category_id` BIGINT NULL);

CREATE INDEX `FK29EC859DA41995D` ON `listing_category`(`category_id`);

CREATE INDEX `FK29EC859DB0E6D64` ON `listing_category`(`listing_categories_id`);

ALTER TABLE `category` ADD CONSTRAINT `FK302BCFE7666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `category` ADD CONSTRAINT `FK302BCFEE31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `listing_category` ADD CONSTRAINT `FK29EC859DB0E6D64` FOREIGN KEY (`listing_categories_id`) REFERENCES `listing` (`id`);

ALTER TABLE `listing_category` ADD CONSTRAINT `FK29EC859DA41995D` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

CREATE TABLE `doc_url` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `listing_id` BIGINT NOT NULL, `name` VARCHAR(255) NOT NULL, `url` VARCHAR(2083) NOT NULL, CONSTRAINT `doc_urlPK` PRIMARY KEY (`id`));

CREATE INDEX `FK6CF91DE85A4BEA77` ON `doc_url`(`listing_id`);

ALTER TABLE `doc_url` ADD CONSTRAINT `FK6CF91DE85A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

CREATE TABLE `contact_type` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `required` bit NOT NULL, `title` VARCHAR(50) NOT NULL, CONSTRAINT `contact_typePK` PRIMARY KEY (`id`), UNIQUE (`title`));

CREATE INDEX `FK4C2BB7F97666C6D2` ON `contact_type`(`created_by_id`);

CREATE INDEX `FK4C2BB7F9E31CB353` ON `contact_type`(`edited_by_id`);

CREATE UNIQUE INDEX `UK9359433766496916df2b541ad47e` ON `contact_type`(`title`);

ALTER TABLE `contact_type` ADD CONSTRAINT `FK4C2BB7F97666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `contact_type` ADD CONSTRAINT `FK4C2BB7F9E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `contact` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `email` VARCHAR(100) NOT NULL, `listing_id` BIGINT NOT NULL, `name` VARCHAR(100) NOT NULL, `organization` VARCHAR(100) NULL, `secure_phone` VARCHAR(50) NULL, `type_id` BIGINT NOT NULL, `unsecure_phone` VARCHAR(50) NULL, CONSTRAINT `contactPK` PRIMARY KEY (`id`));

CREATE INDEX `FK38B724205A4BEA77` ON `contact`(`listing_id`);

CREATE INDEX `FK38B724207666C6D2` ON `contact`(`created_by_id`);

CREATE INDEX `FK38B72420BA3FC877` ON `contact`(`type_id`);

CREATE INDEX `FK38B72420E31CB353` ON `contact`(`edited_by_id`);

ALTER TABLE `contact` ADD CONSTRAINT `FK38B724205A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

ALTER TABLE `contact` ADD CONSTRAINT `FK38B72420BA3FC877` FOREIGN KEY (`type_id`) REFERENCES `contact_type` (`id`);

ALTER TABLE `contact` ADD CONSTRAINT `FK38B724207666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `contact` ADD CONSTRAINT `FK38B72420E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `intent` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `action` VARCHAR(64) NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `icon_id` binary(16) NULL, `label` VARCHAR(255) NULL, `type` VARCHAR(129) NOT NULL, CONSTRAINT `intentPK` PRIMARY KEY (`id`));

CREATE INDEX `FKB971369C7666C6D2` ON `intent`(`created_by_id`);

CREATE INDEX `FKB971369CE31CB353` ON `intent`(`edited_by_id`);

CREATE UNIQUE INDEX `UKc7042bb056f010832f67f6c69a3e` ON `intent`(`type`, `action`);

ALTER TABLE `intent` ADD CONSTRAINT `FKB971369C7666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `intent` ADD CONSTRAINT `FKB971369CE31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `listing_intent` (`listing_intents_id` BIGINT NULL, `intent_id` BIGINT NULL);

CREATE INDEX `FK17BE5CB742D2535F` ON `listing_intent`(`listing_intents_id`);

CREATE INDEX `FK17BE5CB7A651895D` ON `listing_intent`(`intent_id`);

ALTER TABLE `listing_intent` ADD CONSTRAINT `FK17BE5CB742D2535F` FOREIGN KEY (`listing_intents_id`) REFERENCES `listing` (`id`);

ALTER TABLE `listing_intent` ADD CONSTRAINT `FK17BE5CB7A651895D` FOREIGN KEY (`intent_id`) REFERENCES `intent` (`id`);

CREATE TABLE `screenshot` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `large_image_id` binary(16) NULL, `service_item_id` BIGINT NOT NULL, `small_image_id` binary(16) NOT NULL, `ordinal` INT NULL, CONSTRAINT `screenshotPK` PRIMARY KEY (`id`));

CREATE INDEX `FKE72D85666F8C13FE` ON `screenshot`(`service_item_id`);

CREATE INDEX `FKE72D85667666C6D2` ON `screenshot`(`created_by_id`);

CREATE INDEX `FKE72D8566E31CB353` ON `screenshot`(`edited_by_id`);

ALTER TABLE `screenshot` ADD CONSTRAINT `FKE72D85667666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `screenshot` ADD CONSTRAINT `FKE72D8566E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `screenshot` ADD CONSTRAINT `FKE72D85666F8C13FE` FOREIGN KEY (`service_item_id`) REFERENCES `listing` (`id`);

CREATE TABLE `listing_tags` (`listing_id` BIGINT NULL, `tags_string` VARCHAR(255) NULL);

CREATE INDEX `FK483C24F45A4BEA77` ON `listing_tags`(`listing_id`);

ALTER TABLE `listing_tags` ADD CONSTRAINT `FK483C24F45A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

CREATE TABLE `item_comment` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `author_id` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `listing_id` BIGINT NOT NULL, `rate` INT NULL, `text` VARCHAR(4000) NULL, CONSTRAINT `item_commentPK` PRIMARY KEY (`id`));

CREATE INDEX `FKE6D04D335A032135` ON `item_comment`(`author_id`);

CREATE INDEX `FKE6D04D335A4BEA77` ON `item_comment`(`listing_id`);

CREATE INDEX `FKE6D04D337666C6D2` ON `item_comment`(`created_by_id`);

CREATE INDEX `FKE6D04D33E31CB353` ON `item_comment`(`edited_by_id`);

CREATE UNIQUE INDEX `unique_listing_id` ON `item_comment`(`author_id`, `listing_id`);

ALTER TABLE `item_comment` ADD CONSTRAINT `FKE6D04D335A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

ALTER TABLE `item_comment` ADD CONSTRAINT `FKE6D04D337666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `item_comment` ADD CONSTRAINT `FKE6D04D33E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `item_comment` ADD CONSTRAINT `FKE6D04D335A032135` FOREIGN KEY (`author_id`) REFERENCES `profile` (`id`);

CREATE TABLE `iwc_data_object` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `content_type` VARCHAR(129) NOT NULL, `entity` longtext NULL, `key` VARCHAR(255) NOT NULL, `profile_id` BIGINT NOT NULL, CONSTRAINT `iwc_data_objePK` PRIMARY KEY (`id`));

CREATE INDEX `FK999DCD2AC0565C57` ON `iwc_data_object`(`profile_id`);

CREATE UNIQUE INDEX `UKe1b86bb7cb89802b84044c6be65a` ON `iwc_data_object`(`key`, `profile_id`);

ALTER TABLE `iwc_data_object` ADD CONSTRAINT `FK999DCD2AC0565C57` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`);

CREATE TABLE `change_detail` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `field_name` VARCHAR(255) NOT NULL, `new_value` VARCHAR(4000) NULL, `old_value` VARCHAR(4000) NULL, `service_item_activity_id` BIGINT NOT NULL, CONSTRAINT `change_detailPK` PRIMARY KEY (`id`));

CREATE INDEX `FKB4467BC0160FF959` ON `change_detail`(`service_item_activity_id`);

ALTER TABLE `change_detail` ADD CONSTRAINT `FKB4467BC0160FF959` FOREIGN KEY (`service_item_activity_id`) REFERENCES `listing_activity` (`id`);

CREATE TABLE `application_library_entry` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `folder` VARCHAR(256) NULL, `listing_id` BIGINT NOT NULL, `owner_id` BIGINT NOT NULL, `application_library_idx` INT NULL, CONSTRAINT `application_lPK` PRIMARY KEY (`id`));

CREATE INDEX `FK44E0233F5A4BEA77` ON `application_library_entry`(`listing_id`);

CREATE INDEX `FK44E0233F6530DF0D` ON `application_library_entry`(`owner_id`);

CREATE INDEX `FK44E0233F7666C6D2` ON `application_library_entry`(`created_by_id`);

CREATE INDEX `FK44E0233FE31CB353` ON `application_library_entry`(`edited_by_id`);

CREATE UNIQUE INDEX `unique_listing_id` ON `application_library_entry`(`owner_id`, `listing_id`);

ALTER TABLE `application_library_entry` ADD CONSTRAINT `FK44E0233F5A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

ALTER TABLE `application_library_entry` ADD CONSTRAINT `FK44E0233F6530DF0D` FOREIGN KEY (`owner_id`) REFERENCES `profile` (`id`);

ALTER TABLE `application_library_entry` ADD CONSTRAINT `FK44E0233F7666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `application_library_entry` ADD CONSTRAINT `FK44E0233FE31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `rejection_listing` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `author_id` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `description` VARCHAR(2000) NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `service_item_id` BIGINT NOT NULL, CONSTRAINT `rejection_lisPK` PRIMARY KEY (`id`));

CREATE INDEX `FK3F2BD44E5A032135` ON `rejection_listing`(`author_id`);

CREATE INDEX `FK3F2BD44E6F8C13FE` ON `rejection_listing`(`service_item_id`);

CREATE INDEX `FK3F2BD44E7666C6D2` ON `rejection_listing`(`created_by_id`);

CREATE INDEX `FK3F2BD44EE31CB353` ON `rejection_listing`(`edited_by_id`);

CREATE INDEX `rej_lst_author_id_idx` ON `rejection_listing`(`author_id`);

CREATE INDEX `rej_lst_svc_item_id_idx` ON `rejection_listing`(`service_item_id`);

ALTER TABLE `rejection_listing` ADD CONSTRAINT `FK3F2BD44E5A032135` FOREIGN KEY (`author_id`) REFERENCES `profile` (`id`);

ALTER TABLE `rejection_listing` ADD CONSTRAINT `FK3F2BD44E6F8C13FE` FOREIGN KEY (`service_item_id`) REFERENCES `listing` (`id`);

ALTER TABLE `rejection_listing` ADD CONSTRAINT `FK3F2BD44E7666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `rejection_listing` ADD CONSTRAINT `FK3F2BD44EE31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

CREATE TABLE `rejection_activity` (`id` BIGINT NOT NULL, `rejection_listing_id` BIGINT NULL, CONSTRAINT `rejection_actPK` PRIMARY KEY (`id`));

CREATE INDEX `FKF35C12855416850B` ON `rejection_activity`(`id`);

CREATE INDEX `FKF35C128582548A4A` ON `rejection_activity`(`rejection_listing_id`);

ALTER TABLE `rejection_activity` ADD CONSTRAINT `FKF35C128582548A4A` FOREIGN KEY (`rejection_listing_id`) REFERENCES `rejection_listing` (`id`);

ALTER TABLE `rejection_activity` ADD CONSTRAINT `FKF35C12855416850B` FOREIGN KEY (`id`) REFERENCES `listing_activity` (`id`);

CREATE TABLE `listing_snapshot` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `listing_id` BIGINT NULL, `title` VARCHAR(255) NOT NULL, CONSTRAINT `listing_snapsPK` PRIMARY KEY (`id`));

CREATE INDEX `FK1096E11F5A4BEA77` ON `listing_snapshot`(`listing_id`);

ALTER TABLE `listing_snapshot` ADD CONSTRAINT `FK1096E11F5A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

CREATE TABLE `relationship_activity_log` (`mod_rel_activity_id` BIGINT NOT NULL, `listing_snapshot_id` BIGINT NULL, `items_idx` INT NULL);

CREATE INDEX `FK594974BB4DC662E0` ON `relationship_activity_log`(`listing_snapshot_id`);

CREATE TABLE `relationship_listing` (`relationship_related_items_id` BIGINT NULL, `listing_id` BIGINT NULL, `related_items_idx` INT NULL);

CREATE INDEX `FKDDEF1F7D5A4BEA77` ON `relationship_listing`(`listing_id`);

ALTER TABLE `relationship_listing` ADD CONSTRAINT `FKDDEF1F7D5A4BEA77` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`);

ALTER TABLE `relationship_activity_log` ADD CONSTRAINT `FK594974BB4DC662E0` FOREIGN KEY (`listing_snapshot_id`) REFERENCES `listing_snapshot` (`id`);

CREATE TABLE `scorecard` (`id` BIGINT AUTO_INCREMENT NOT NULL, `version` BIGINT NOT NULL, `created_by_id` BIGINT NULL, `created_date` DATETIME NOT NULL, `description` VARCHAR(500) NOT NULL, `edited_by_id` BIGINT NULL, `edited_date` DATETIME NOT NULL, `image` VARCHAR(2083) NULL, `question` VARCHAR(250) NOT NULL, `show_on_listing` bit NULL, CONSTRAINT `scorecardPK` PRIMARY KEY (`id`));

CREATE TABLE `service_item_score_card_item` (`service_item_id` BIGINT NOT NULL, `score_card_item_id` BIGINT NULL);

CREATE INDEX `FKBF91F936F8C13FE` ON `service_item_score_card_item`(`service_item_id`);

CREATE INDEX `FKBF91F9388E6B0C4` ON `service_item_score_card_item`(`score_card_item_id`);

CREATE INDEX `FK7EEC20A27666C6D2` ON `scorecard`(`created_by_id`);

CREATE INDEX `FK7EEC20A2E31CB353` ON `scorecard`(`edited_by_id`);

ALTER TABLE `scorecard` ADD CONSTRAINT `FK7EEC20A27666C6D2` FOREIGN KEY (`created_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `scorecard` ADD CONSTRAINT `FK7EEC20A2E31CB353` FOREIGN KEY (`edited_by_id`) REFERENCES `profile` (`id`);

ALTER TABLE `service_item_score_card_item` ADD CONSTRAINT `FKBF91F936F8C13FE` FOREIGN KEY (`service_item_id`) REFERENCES `listing` (`id`);

ALTER TABLE `service_item_score_card_item` ADD CONSTRAINT `FKBF91F9388E6B0C4` FOREIGN KEY (`score_card_item_id`) REFERENCES `scorecard` (`id`);

CREATE TABLE `modify_relationship_activity` (`id` BIGINT NOT NULL, CONSTRAINT `modify_relatiPK` PRIMARY KEY (`id`));

CREATE INDEX `FKE68D3F715416850B` ON `modify_relationship_activity`(`id`);

ALTER TABLE `modify_relationship_activity` ADD CONSTRAINT `FKE68D3F715416850B` FOREIGN KEY (`id`) REFERENCES `listing_activity` (`id`);

CREATE INDEX `FKD032333CC0565C57` ON `profile_dismissed_notifications`(`profile_id`);

ALTER TABLE `notification` MODIFY `message` VARCHAR(150) NOT NULL;
--  Changeset notifications_and_screenshots.groovy::1430772568976-1::clark (generated)::(Checksum: 3:969059c357aa2e949734cda5000f2750)

ALTER TABLE `profile_dismissed_notifications` MODIFY `notification_id` BIGINT NOT NULL;
--  Changeset notifications_and_screenshots.groovy::1430772568976-2::clark (generated)::(Checksum: 3:b820fbb399cbf76472b5260769bb9e20)

ALTER TABLE `profile_dismissed_notifications` MODIFY `profile_id` BIGINT NOT NULL;
--  Changeset notifications_and_screenshots.groovy::1430772568976-3::clark (generated)::(Checksum: 3:c7f3a69158ebe1f304052c38111afd16)

ALTER TABLE `screenshot` MODIFY `small_image_id` binary(16) NULL;
--  Changeset notifications_and_screenshots.groovy::1430772568976-4::clark (generated)::(Checksum: 3:878cd06d9f220d61881d7d6b6de26bcc)

ALTER TABLE `profile_dismissed_notifications` ADD PRIMARY KEY (`profile_id`, `notification_id`);
--  Changeset notifications_and_screenshots.groovy::1430772568976-5::clark (generated)::(Checksum: 3:475c966d2f22fb596d007a1c8c213286)

ALTER TABLE `ozp`.`relationship_listing` DROP FOREIGN KEY `FKDDEF1F7D5A4BEA77`;
--  Changeset notifications_and_screenshots.groovy::1430772568976-6::clark (generated)::(Checksum: 3:874d96cb5f5e18e5111ab7040d1311d7)

DROP INDEX `notification_expires_date_idx` ON `notification`;
--  Changeset notifications_and_screenshots.groovy::1430772568976-7::clark (generated)::(Checksum: 3:fbb330fa552aad50bf6fc22cd6956b95)

DROP TABLE `relationship_listing`;
--  Changeset notifications_and_screenshots.groovy::1430772568976-8::clark (generated)::(Checksum: 3:e2445793c27061aa15568c1e610ecb70)

CREATE INDEX `FKD032333CC0565C57` ON `profile_dismissed_notifications`(`profile_id`);
--  Changeset notifications_and_screenshots.groovy::1430772568976-9::clark (generated)::(Checksum: 3:96f1fe866ad22b49556ab507f478592c)



INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('ozp-rest', '', NOW(), 'Create Table, Create Index (x3), Add Foreign Key Constraint (x2), Create Table, Create Index (x3), Add Foreign Key Constraint (x2), Create Table, Add Foreign Key Constraint (x2), Create Table, Create Index (x3), Add Foreign Key Constraint (x2), Create ...', 'EXECUTED', 'changelog.groovy', 'ozp-rest-0.1.0-1', '2.0.5', '3:1b55e92750620a44677e226bcd9359b5', 1);
