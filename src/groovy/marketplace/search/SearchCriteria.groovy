package marketplace.search

class SearchCriteria {

    final static ALLOWED_FILTERS = [
            categories: 'categories.title',
            agency: 'agency.title',
            type: 'type.title',
            isFeatured: 'isFeatured'
    ]

    final static ALLOWED_ORDER = ['ASC', 'DESC']

    final static ALLOWED_SORT = ['title', 'avgRate', 'score', 'approvedDate']

    //multiple values of filters specified here are combined using UNION.  Filters not
    //specified here are combined using INTERSECTION.  Filters of different types are always
    //combined using INTERSECTION
    final static UNION_FILTERS = ['type.title', 'agency.title']

    Map<String, List<String>> filters = new HashMap<String, List<String>>()
    List<String> fields = ['title', 'description', 'descriptionShort', 'whatIsNew', 'requirements', 'tags']
    String order = 'DESC'
    String sort = 'score'
    String queryString = '*'
    long max = 24
    long offset = 0

    public static SearchCriteria fromQueryParams(Map<String, List<String>> queryParams) {
        def sc = new SearchCriteria()

        ALLOWED_FILTERS.each { param, field ->
            List<String> value = queryParams.get(param)
            if(value) sc.filters.put(field, value)
        }

        List<String> orderParam = queryParams.get('order')
        if(orderParam && orderParam[0] in ALLOWED_ORDER) {
            sc.order = orderParam[0]
        }

        List<String> sortParam = queryParams.get('sort')
        if(sortParam && sortParam[0] in ALLOWED_SORT) {
            sc.sort = sortParam[0] == 'title' ? 'sortTitle' : sortParam[0]
        }

        List<String> maxParam = queryParams.get('max')
        if(maxParam && maxParam[0].isLong()) {
            sc.max = Long.parseLong(maxParam[0])
        }

        List<String> offsetParam = queryParams.get('offset')
        if(offsetParam && offsetParam[0].isLong()) {
            sc.offset = Long.parseLong(offsetParam[0])
        }

        List<String> queryStringParam = queryParams.get('queryString')
        if(queryStringParam && queryStringParam[0]) {
            sc.queryString = queryStringParam[0]
        }

        sc
    }
}
