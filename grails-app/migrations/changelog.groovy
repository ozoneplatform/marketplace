databaseChangeLog = {

    changeSet(author: 'ozp-rest', id: 'ozp-rest-0.1.0-1') {

        /**
         * Profile
         */
        createTable(tableName: "profile") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "profilePK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: 'bio', type: 'varchar(1000)')

            column(name: 'created_by_id', type: 'bigint')

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "display_name", type: "varchar(255)")

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "varchar(255)")

            column(name: "highest_role", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_login", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "launch_in_webtop", type: "boolean") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FKED8E89A97666C6D2", tableName: "profile") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FKED8E89A9E31CB353", tableName: "profile") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "UK2e2af56bde6e9cad39c0f4ae1993", tableName: "profile", unique: "true") {
            column(name: "username")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "profile", constraintName: "FKED8E89A97666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "profile", constraintName: "FKED8E89A9E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")


        /**
         * Notification
         */
        createTable(tableName: "notification") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "notificationPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "message", type: "varchar(150)")

            column(name: "expires_date", type: "datetime") {
                constraints(nullable: "false")
            }
        }
        createIndex(indexName: "notification_created_by_idx", tableName: "notification") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "notification_edited_by_idx", tableName: "notification") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "notification_expires_date_idx", tableName: "notification") {
            column(name: "expires_date")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "notification", constraintName: "notification_created_by_idx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "notification", constraintName: "notification_edited_by_idx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")

        createTable(tableName: "profile_dismissed_notifications") {
            column(name: "notification_id", type: "bigint")
            column(name: "profile_id", type: "bigint")
        }
        addForeignKeyConstraint(baseColumnNames: "notification_id", baseTableName: "profile_dismissed_notifications", constraintName: "profile_notification_notification_id_fk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "profile_dismissed_notifications", constraintName: "profile_notification_profile_id_fk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")

        /**
         * Type
         */
        createTable(tableName: "type") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "typePK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(255)")

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }

        createIndex(indexName: "FK368F3A7666C6D2", tableName: "type") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK368F3AE31CB353", tableName: "type") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "UK57c992e1354f398a8f71f29226dc", tableName: "type", unique: "true") {
            column(name: "title")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "type", constraintName: "FK368F3A7666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "type", constraintName: "FK368F3AE31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")




        /**
         * Agency
         */
        createTable(tableName: "agency") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "agencyPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "icon_id", type: "binary(16)")

            column(name: "short_name", type: "varchar(8)") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FKAB611C057666C6D2", tableName: "agency") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FKAB611C05E31CB353", tableName: "agency") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "UK492f940b0f57ca03abde64501045", tableName: "agency", unique: "true") {
            column(name: "title")
        }

        createIndex(indexName: "short_name_uniq_1414157183392", tableName: "agency", unique: "true") {
            column(name: "short_name")
        }

        createTable(tableName: "profile_agency") {
            column(name: "profile_organizations_id", type: "bigint")

            column(name: "agency_id", type: "bigint")

            column(name: "profile_stewarded_organizations_id", type: "bigint")
        }

        createIndex(indexName: "FKB82D0F5B143B24BD", tableName: "profile_agency") {
            column(name: "agency_id")
        }

        createIndex(indexName: "FKB82D0F5B4B3EE656", tableName: "profile_agency") {
            column(name: "profile_stewarded_organizations_id")
        }

        createIndex(indexName: "FKB82D0F5B7AA654D6", tableName: "profile_agency") {
            column(name: "profile_organizations_id")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "agency", constraintName: "FKAB611C057666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "agency", constraintName: "FKAB611C05E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "agency_id", baseTableName: "profile_agency", constraintName: "FKB82D0F5B143B24BD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "agency", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "profile_organizations_id", baseTableName: "profile_agency", constraintName: "FKB82D0F5B7AA654D6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "profile_stewarded_organizations_id", baseTableName: "profile_agency", constraintName: "FKB82D0F5B4B3EE656", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")





        /**
         * Listing Activity
         */
        createTable(tableName: "listing_activity") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "listing_activPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "action", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "activity_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "author_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "listing_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "listing_activities_idx", type: "integer")
        }

        createIndex(indexName: "FK9CE7FE6A5A032135", tableName: "listing_activity") {
            column(name: "author_id")
        }

        createIndex(indexName: "FK9CE7FE6A5A4BEA77", tableName: "listing_activity") {
            column(name: "listing_id")
        }

        createIndex(indexName: "FK9CE7FE6A7666C6D2", tableName: "listing_activity") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK9CE7FE6AE31CB353", tableName: "listing_activity") {
            column(name: "edited_by_id")
        }

        addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "listing_activity", constraintName: "FK9CE7FE6A5A032135", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "listing_activity", constraintName: "FK9CE7FE6A7666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "listing_activity", constraintName: "FK9CE7FE6AE31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")





        /**
         * Listing
         */
        createTable(tableName: "listing") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "listingPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "agency_id", type: "bigint")

            column(name: "approval_status", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "approved_date", type: "datetime")

            column(name: "avg_rate", type: "float") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(4000)")

            column(name: "description_short", type: "varchar(150)")

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "small_icon_id", type: "binary(16)")

            column(name: "large_icon_id", type: "binary(16)")

            column(name: "banner_icon_id", type: "binary(16)")

            column(name: "featured_banner_icon_id", type: "binary(16)")

            column(name: "is_enabled", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "is_featured", type: "bit")

            column(name: "last_activity_id", type: "bigint")

            column(name: "launch_url", type: "varchar(2083)")

            column(name: "requirements", type: "varchar(1000)")

            column(name: "title", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "total_comments", type: "integer") {
                constraints(nullable: "false")
            }

            column(name: "total_rate1", type: "integer")

            column(name: "total_rate2", type: "integer")

            column(name: "total_rate3", type: "integer")

            column(name: "total_rate4", type: "integer")

            column(name: "total_rate5", type: "integer")

            column(name: "total_votes", type: "integer") {
                constraints(nullable: "false")
            }

            column(name: "type_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "version_name", type: "varchar(255)")

            column(name: "what_is_new", type: "varchar(250)")

            column(name: 'width', type: 'integer')

            column(name: 'singleton', type: 'bit') {
                constraints(nullable: "false")
            }
            column(name: 'height', type: 'integer')
        }

        createIndex(indexName: "FKAD8BA84143B24BD", tableName: "listing") {
            column(name: "agency_id")
        }

        createIndex(indexName: "FKAD8BA847666C6D2", tableName: "listing") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FKAD8BA849809495D", tableName: "listing") {
            column(name: "type_id")
        }

        createIndex(indexName: "FKAD8BA84E31CB353", tableName: "listing") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "FKAD8BA84B803A812", tableName: "listing") {
            column(name: "last_activity_id")
        }

        createTable(tableName: "listing_profile") {
            column(name: "listing_owners_id", type: "bigint")

            column(name: "profile_id", type: "bigint")
        }

        createTable(tableName:"listing_listing") {
            column(name:"listing_required_id", type:"bigint")

            column(name:"listing_id", type:"bigint")
        }

        createIndex(indexName:"FK763057C9C50B9241", tableName: "listing_listing") {
            column(name: "listing_required_id")
        }

        createIndex(indexName:"FK763057C95A4BEA77", tableName: "listing_listing"){
            column(name: "listing_id")
        }

        createIndex(indexName: "FK58E626EE1459EB60", tableName: "listing_profile") {
            column(name: "listing_owners_id")
        }

        createIndex(indexName: "FK58E626EEC0565C57", tableName: "listing_profile") {
            column(name: "profile_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_owners_id", baseTableName: "listing_profile", constraintName: "FK58E626EE1459EB60", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "listing_profile", constraintName: "FK58E626EEC0565C57", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "agency_id", baseTableName: "listing", constraintName: "FKAD8BA84143B24BD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "agency", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "listing", constraintName: "FKAD8BA849809495D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "type", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "listing", constraintName: "FKAD8BA847666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "listing", constraintName: "FKAD8BA84E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "listing_required_id", baseTableName: "listing_listing", constraintName:"FK763057C9C50B9241", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "listing_listing", constraintName: "FK763057C95A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")


        /**
         * Listing and Listing Activity both have FKs referencing the other
         */
        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "listing_activity", constraintName: "FK9CE7FE6A5A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "last_activity_id", baseTableName: "listing", constraintName: "FKAD8BA84B803A812", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing_activity", referencesUniqueColumn: "false")





        /**
         * Category
         */
        createTable(tableName: "category") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "categoryPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(255)")

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }

        createIndex(indexName: "FK302BCFE7666C6D2", tableName: "category") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK302BCFEE31CB353", tableName: "category") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "UK7746033a611e7747013c9a9e2900", tableName: "category", unique: "true") {
            column(name: "title")
        }

        createTable(tableName: "listing_category") {
            column(name: "listing_categories_id", type: "bigint")

            column(name: "category_id", type: "bigint")
        }

        createIndex(indexName: "FK29EC859DA41995D", tableName: "listing_category") {
            column(name: "category_id")
        }

        createIndex(indexName: "FK29EC859DB0E6D64", tableName: "listing_category") {
            column(name: "listing_categories_id")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "category", constraintName: "FK302BCFE7666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "category", constraintName: "FK302BCFEE31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "listing_categories_id", baseTableName: "listing_category", constraintName: "FK29EC859DB0E6D64", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "listing_category", constraintName: "FK29EC859DA41995D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category", referencesUniqueColumn: "false")




        /**
         * Documentation URLs
         */
        createTable(tableName: "doc_url") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "doc_urlPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "listing_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "url", type: "varchar(2083)") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FK6CF91DE85A4BEA77", tableName: "doc_url") {
            column(name: "listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "doc_url", constraintName: "FK6CF91DE85A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")




        /**
         * Contact Type
         */
        createTable(tableName: "contact_type") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "contact_typePK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "required", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "varchar(50)") {
                constraints(nullable: "false", unique: "true")
            }
        }

        createIndex(indexName: "FK4C2BB7F97666C6D2", tableName: "contact_type") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK4C2BB7F9E31CB353", tableName: "contact_type") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "UK9359433766496916df2b541ad47e", tableName: "contact_type", unique: "true") {
            column(name: "title")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "contact_type", constraintName: "FK4C2BB7F97666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "contact_type", constraintName: "FK4C2BB7F9E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")





        /**
         * Contact
         */
        createTable(tableName: "contact") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "contactPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "listing_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "organization", type: "varchar(100)")

            column(name: "secure_phone", type: "varchar(50)")

            column(name: "type_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "unsecure_phone", type: "varchar(50)")
        }

        createIndex(indexName: "FK38B724205A4BEA77", tableName: "contact") {
            column(name: "listing_id")
        }

        createIndex(indexName: "FK38B724207666C6D2", tableName: "contact") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK38B72420BA3FC877", tableName: "contact") {
            column(name: "type_id")
        }

        createIndex(indexName: "FK38B72420E31CB353", tableName: "contact") {
            column(name: "edited_by_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "contact", constraintName: "FK38B724205A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "contact", constraintName: "FK38B72420BA3FC877", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contact_type", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "contact", constraintName: "FK38B724207666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "contact", constraintName: "FK38B72420E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")




        /**
         * Intent
         */
        createTable(tableName: "intent") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "intentPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "action", type: "varchar(64)") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "icon_id", type: "binary(16)")

            column(name: "label", type: "varchar(255)")

            column(name: "type", type: "varchar(129)") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FKB971369C7666C6D2", tableName: "intent") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FKB971369CE31CB353", tableName: "intent") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "UKc7042bb056f010832f67f6c69a3e", tableName: "intent", unique: "true") {
            column(name: "type")

            column(name: "action")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "intent", constraintName: "FKB971369C7666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "intent", constraintName: "FKB971369CE31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")

        createTable(tableName: "listing_intent") {
            column(name: "listing_intents_id", type: "bigint")

            column(name: "intent_id", type: "bigint")
        }

        createIndex(indexName: "FK17BE5CB742D2535F", tableName: "listing_intent") {
            column(name: "listing_intents_id")
        }

        createIndex(indexName: "FK17BE5CB7A651895D", tableName: "listing_intent") {
            column(name: "intent_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_intents_id", baseTableName: "listing_intent", constraintName: "FK17BE5CB742D2535F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "intent_id", baseTableName: "listing_intent", constraintName: "FK17BE5CB7A651895D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "intent", referencesUniqueColumn: "false")




        /**
         * Screenshot
         */
        createTable(tableName: "screenshot") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "screenshotPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "large_image_id", type: "binary(16)")

            column(name: "service_item_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "small_image_id", type: "binary(16)") {
                constraints(nullable: "false")
            }

            column(name: "ordinal", type: "integer")
        }

        createIndex(indexName: "FKE72D85666F8C13FE", tableName: "screenshot") {
            column(name: "service_item_id")
        }

        createIndex(indexName: "FKE72D85667666C6D2", tableName: "screenshot") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FKE72D8566E31CB353", tableName: "screenshot") {
            column(name: "edited_by_id")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "screenshot", constraintName: "FKE72D85667666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "screenshot", constraintName: "FKE72D8566E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "service_item_id", baseTableName: "screenshot", constraintName: "FKE72D85666F8C13FE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")




        /**
         * Tags
         */
        createTable(tableName: "listing_tags") {
            column(name: "listing_id", type: "bigint")

            column(name: "tags_string", type: "varchar(255)")
        }

        createIndex(indexName: "FK483C24F45A4BEA77", tableName: "listing_tags") {
            column(name: "listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "listing_tags", constraintName: "FK483C24F45A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")



        /**
         * Item Comment
         */
        createTable(tableName: "item_comment") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "item_commentPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "author_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "listing_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "rate", type: "integer")

            column(name: "text", type: "varchar(4000)")
        }

        createIndex(indexName: "FKE6D04D335A032135", tableName: "item_comment") {
            column(name: "author_id")
        }

        createIndex(indexName: "FKE6D04D335A4BEA77", tableName: "item_comment") {
            column(name: "listing_id")
        }

        createIndex(indexName: "FKE6D04D337666C6D2", tableName: "item_comment") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FKE6D04D33E31CB353", tableName: "item_comment") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "unique_listing_id", tableName: "item_comment", unique: "true") {
            column(name: "author_id")

            column(name: "listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "item_comment", constraintName: "FKE6D04D335A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "item_comment", constraintName: "FKE6D04D337666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "item_comment", constraintName: "FKE6D04D33E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "item_comment", constraintName: "FKE6D04D335A032135", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")




        /**
         * IWC Data Object
         */
        createTable(tableName: "iwc_data_object") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "iwc_data_objePK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "content_type", type: "varchar(129)") {
                constraints(nullable: "false")
            }

            column(name: "entity", type: "longtext")

            column(name: "key", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "profile_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FK999DCD2AC0565C57", tableName: "iwc_data_object") {
            column(name: "profile_id")
        }

        createIndex(indexName: "UKe1b86bb7cb89802b84044c6be65a", tableName: "iwc_data_object", unique: "true") {
            column(name: "key")

            column(name: "profile_id")
        }

        addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "iwc_data_object", constraintName: "FK999DCD2AC0565C57", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")




        /**
         * Change Detail
         */
        createTable(tableName: "change_detail") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "change_detailPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "field_name", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "new_value", type: "varchar(4000)")

            column(name: "old_value", type: "varchar(4000)")

            column(name: "service_item_activity_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FKB4467BC0160FF959", tableName: "change_detail") {
            column(name: "service_item_activity_id")
        }

        addForeignKeyConstraint(baseColumnNames: "service_item_activity_id", baseTableName: "change_detail", constraintName: "FKB4467BC0160FF959", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing_activity", referencesUniqueColumn: "false")




        /**
         * Application Library Entry
         */
        createTable(tableName: "application_library_entry") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "application_lPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "folder", type: "varchar(256)")

            column(name: "listing_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "owner_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "application_library_idx", type: "integer")
        }

        createIndex(indexName: "FK44E0233F5A4BEA77", tableName: "application_library_entry") {
            column(name: "listing_id")
        }

        createIndex(indexName: "FK44E0233F6530DF0D", tableName: "application_library_entry") {
            column(name: "owner_id")
        }

        createIndex(indexName: "FK44E0233F7666C6D2", tableName: "application_library_entry") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK44E0233FE31CB353", tableName: "application_library_entry") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "unique_listing_id", tableName: "application_library_entry", unique: "true") {
            column(name: "owner_id")

            column(name: "listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "application_library_entry", constraintName: "FK44E0233F5A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "application_library_entry", constraintName: "FK44E0233F6530DF0D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "application_library_entry", constraintName: "FK44E0233F7666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "application_library_entry", constraintName: "FK44E0233FE31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")




        /**
         * Rejection Listing
         */
        createTable(tableName: "rejection_listing") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "rejection_lisPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "author_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(2000)")

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "service_item_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FK3F2BD44E5A032135", tableName: "rejection_listing") {
            column(name: "author_id")
        }

        createIndex(indexName: "FK3F2BD44E6F8C13FE", tableName: "rejection_listing") {
            column(name: "service_item_id")
        }

        createIndex(indexName: "FK3F2BD44E7666C6D2", tableName: "rejection_listing") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK3F2BD44EE31CB353", tableName: "rejection_listing") {
            column(name: "edited_by_id")
        }

        createIndex(indexName: "rej_lst_author_id_idx", tableName: "rejection_listing") {
            column(name: "author_id")
        }

        createIndex(indexName: "rej_lst_svc_item_id_idx", tableName: "rejection_listing") {
            column(name: "service_item_id")
        }

        addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "rejection_listing", constraintName: "FK3F2BD44E5A032135", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "service_item_id", baseTableName: "rejection_listing", constraintName: "FK3F2BD44E6F8C13FE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "rejection_listing", constraintName: "FK3F2BD44E7666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "rejection_listing", constraintName: "FK3F2BD44EE31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")



        /**
         * Rejection Activity
         */
        createTable(tableName: "rejection_activity") {
            column(name: "id", type: "bigint") {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "rejection_actPK")
            }

            column(name: "rejection_listing_id", type: "bigint")
        }

        createIndex(indexName: "FKF35C12855416850B", tableName: "rejection_activity") {
            column(name: "id")
        }

        createIndex(indexName: "FKF35C128582548A4A", tableName: "rejection_activity") {
            column(name: "rejection_listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "rejection_listing_id", baseTableName: "rejection_activity", constraintName: "FKF35C128582548A4A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rejection_listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "id", baseTableName: "rejection_activity", constraintName: "FKF35C12855416850B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing_activity", referencesUniqueColumn: "false")



        /**
         * Listing Snapshot
         */
        createTable(tableName: "listing_snapshot") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "listing_snapsPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "listing_id", type: "bigint")

            column(name: "title", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }

        createIndex(indexName: "FK1096E11F5A4BEA77", tableName: "listing_snapshot") {
            column(name: "listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "listing_snapshot", constraintName: "FK1096E11F5A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")




        /**
         * Relationship Activity
         */
        createTable(tableName: "relationship_activity_log") {
            column(name: "mod_rel_activity_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "listing_snapshot_id", type: "bigint")

            column(name: "items_idx", type: "integer")
        }

        createIndex(indexName: "FK594974BB4DC662E0", tableName: "relationship_activity_log") {
            column(name: "listing_snapshot_id")
        }

        createTable(tableName: "relationship_listing") {
            column(name: "relationship_related_items_id", type: "bigint")

            column(name: "listing_id", type: "bigint")

            column(name: "related_items_idx", type: "integer")
        }

        createIndex(indexName: "FKDDEF1F7D5A4BEA77", tableName: "relationship_listing") {
            column(name: "listing_id")
        }

        addForeignKeyConstraint(baseColumnNames: "listing_id", baseTableName: "relationship_listing", constraintName: "FKDDEF1F7D5A4BEA77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "listing_snapshot_id", baseTableName: "relationship_activity_log", constraintName: "FK594974BB4DC662E0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing_snapshot", referencesUniqueColumn: "false")




        /**
         * Scorecard
         */
        createTable(tableName: "scorecard") {
            column(autoIncrement: 'true', name: 'id', type: 'bigint') {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "scorecardPK")
            }

            column(name: 'version', type: 'bigint') {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "bigint")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(500)") {
                constraints(nullable: "false")
            }

            column(name: "edited_by_id", type: "bigint")

            column(name: "edited_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "image", type: "varchar(2083)")

            column(name: "question", type: "varchar(250)") {
                constraints(nullable: "false")
            }

            column(name: "show_on_listing", type: "bit")
        }

        createTable(tableName: "service_item_score_card_item") {
            column(name: "service_item_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "score_card_item_id", type: "bigint")
        }

        createIndex(indexName: "FKBF91F936F8C13FE", tableName: "service_item_score_card_item") {
            column(name: "service_item_id")
        }

        createIndex(indexName: "FKBF91F9388E6B0C4", tableName: "service_item_score_card_item") {
            column(name: "score_card_item_id")
        }

        createIndex(indexName: "FK7EEC20A27666C6D2", tableName: "scorecard") {
            column(name: "created_by_id")
        }

        createIndex(indexName: "FK7EEC20A2E31CB353", tableName: "scorecard") {
            column(name: "edited_by_id")
        }

        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "scorecard", constraintName: "FK7EEC20A27666C6D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "edited_by_id", baseTableName: "scorecard", constraintName: "FK7EEC20A2E31CB353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "service_item_id", baseTableName: "service_item_score_card_item", constraintName: "FKBF91F936F8C13FE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing", referencesUniqueColumn: "false")
        addForeignKeyConstraint(baseColumnNames: "score_card_item_id", baseTableName: "service_item_score_card_item", constraintName: "FKBF91F9388E6B0C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "scorecard", referencesUniqueColumn: "false")




        /**
         * Modify Relationship Activity
         */
        createTable(tableName: "modify_relationship_activity") {
            column(name: "id", type: "bigint") {
                constraints(nullable: 'false', primaryKey: 'true', primaryKeyName: "modify_relatiPK")
            }
        }

        createIndex(indexName: "FKE68D3F715416850B", tableName: "modify_relationship_activity") {
            column(name: "id")
        }

        addForeignKeyConstraint(baseColumnNames: "id", baseTableName: "modify_relationship_activity", constraintName: "FKE68D3F715416850B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "listing_activity", referencesUniqueColumn: "false")

    }

	include file: 'notifications_and_screenshots.groovy'
}
