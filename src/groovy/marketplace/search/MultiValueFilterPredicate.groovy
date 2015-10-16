package marketplace.search

/**
 * Represents a predicate which may have multiple values joined by an OR or AND boolean operator.
 */
class MultiValueFilterPredicate extends MultiValuePredicate {
    @Override
    def getSearchClause() {
        return {
            must {
                nested {
                    path = this.predicateName
                    valueList.eachWithIndex { value, index ->
                        query {
                            if (isCustomField)
                                query_string(default_field: "_all", query: "(customFieldName:${indexFieldName} AND fieldValueText:${queryString})")
                            else
                                query_string(default_field: this.indexFieldName, query: value)
                        }
                    }                    
                }                    
            }
        }
    }
}