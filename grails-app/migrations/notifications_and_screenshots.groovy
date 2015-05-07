databaseChangeLog = {

	changeSet(author: "clark (generated)", id: "1430772568976-1") {
		addNotNullConstraint(columnDataType: "varchar(150)", columnName: "message", tableName: "notification")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-2") {
		addNotNullConstraint(columnDataType: "bigint", columnName: "notification_id", tableName: "profile_dismissed_notifications")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-3") {
		addNotNullConstraint(columnDataType: "bigint", columnName: "profile_id", tableName: "profile_dismissed_notifications")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-4") {
		dropNotNullConstraint(columnDataType: "binary(16)", columnName: "small_image_id", tableName: "screenshot")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-5") {
		addPrimaryKey(columnNames: "profile_id, notification_id", tableName: "profile_dismissed_notifications")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-6") {
		dropForeignKeyConstraint(baseTableName: "relationship_listing", baseTableSchemaName: "ozp", constraintName: "FKDDEF1F7D5A4BEA77")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-7") {
		dropIndex(indexName: "notification_expires_date_idx", tableName: "notification")
	}

	changeSet(author: "clark (generated)", id: "1430772568976-8") {
		dropTable(tableName: "relationship_listing")
	}

        changeSet(author: "clark (generated)", id: "1430772568976-9") {
                createIndex(indexName: "FKD032333CC0565C57", tableName: "profile_dismissed_notifications") {
                        column(name: "profile_id")
                }
        }

}
