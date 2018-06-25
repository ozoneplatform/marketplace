define([
    'underscore'
], function () {
    var changelogs = {};

    changelogs.CREATED = {"id":27,"author":"Mike Parizer Admin","aggregate":false,"authorUserName":"MikePAdmin","entryIsNewDay":false,"activityTimestamp":"12/17/2013 04:24 PM","action": {description: "Created"},"entryHasNewAuthor":false};
    changelogs.INSIDE = {"id":28,"author":"Mike Parizer Admin","aggregate":false,"authorUserName":"MikePAdmin","entryIsNewDay":false,"activityTimestamp":"12/17/2013 04:24 PM","action": {description: "Inside"},"entryHasNewAuthor":false};
    changelogs.OUTSIDE = {"id":29,"author":"Mike Parizer Admin","aggregate":false,"authorUserName":"MikePAdmin","entryIsNewDay":false,"activityTimestamp":"12/17/2013 04:24 PM","action": {description: "Outside"},"entryHasNewAuthor":false};
    changelogs.APPROVED = {"id":30,"author":"Mike Parizer Admin","aggregate":false,"authorUserName":"MikePAdmin","entryIsNewDay":false,"activityTimestamp":"12/17/2013 04:24 PM","action": {description: "Approved"},"entryHasNewAuthor":false};
    changelogs.REQUIREMENT_ADDED = {"id":162,"author":"Mike Parizer Admin","aggregate":false,"authorUserName":"MikePAdmin","entryIsNewDay":false,"activityTimestamp":"12/17/2013 04:25 PM","action": {description: "Added as a requirement"},"entryHasNewAuthor":false,"relatedItems":[{"id":6,"title":"app comp 1 wayne","hasAccess":true}]};
    changelogs.REQUIREMENT_REMOVED = {"id": 344,"author": "Nish Patel Admin","aggregate": false,"authorUserName": "NishAdmin","entryIsNewDay": true,"activityTimestamp": "12/31/2013 11:36 AM","action": { description: "Requirements removed"},"entryHasNewAuthor": true,"relatedItems": [{"id": 36,"title": "2 Web app wayne","hasAccess": true}]};
    changelogs.SCORECARD_UPDATED = {"id": 274,"author": "Mike Parizer Admin","aggregate": false,"authorUserName": "MikePAdmin","entryIsNewDay": false,"activityTimestamp": "12/17/2013 04:26 PM","action":  {name: 'LOCAL_SCORECARD_QUESTION_UPDATED', description: 'Scorecard Updated'}, "entryHasNewAuthor": false};
    changelogs.REJECTED = {"rejectionReason":"Data Content","id":340,"author":"Nish Patel Admin",rejectionListing:{"description":"reason", justification:{title:"title"}},"aggregate":false,"authorUserName":"NishAdmin","entryIsNewDay":true,"activityTimestamp":"12/31/2013 11:28 AM","action": {description: "Rejected"},"entryHasNewAuthor":true};
    changelogs.MODIFIED = {"id":341,"author":"Nish Patel Admin","aggregate":false,"authorUserName":"NishAdmin","entryIsNewDay":true,"activityTimestamp":"12/31/2013 11:30 AM","action": {description: "Modified"},"changeDetails":[{"id":167,"objectVersion":5,"newValue":"on","objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":"true","fieldName":"visibleInLaunch","displayName":"Visible"},{"id":168,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"<b> a BUG!<\/b> <button> test <\/button>","displayName":"<b> a BUG!<\/b> <button> test <\/button>"},{"id":169,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Alternate POC Info","displayName":"Alternate POC Info"},{"id":170,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Alternate POC Info","displayName":"Alternate POC Info"},{"id":171,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"bad label","displayName":"bad label"},{"id":172,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"CF One Type","displayName":"CF One Type"},{"id":173,"objectVersion":5,"newValue":"false","objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Checkbox","displayName":"Checkbox"},{"id":174,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Government Sponsor","displayName":"Government Sponsor"},{"id":175,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Image URL","displayName":"Image URL"},{"id":176,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Intelligence","displayName":"Intelligence"},{"id":177,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Sponsor Contact","displayName":"Sponsor Contact"},{"id":178,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Support POC Info","displayName":"Support POC Info"},{"id":179,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Technical POC Info","displayName":"Technical POC Info"},{"id":180,"objectVersion":5,"newValue":null,"objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"Text Area","displayName":"Text Area"},{"id":182,"objectVersion":5,"newValue":"some description","objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"description","displayName":"Description"},{"id":183,"objectVersion":5,"newValue":"[RyanAdmin]","objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":null,"fieldName":"techPocs","displayName":"Technical POCs"},{"id":184,"objectVersion":5,"newValue":"Rejected listing","objectClassName":"marketplace.ServiceItem","objectId":29,"oldValue":"Rejected","fieldName":"title","displayName":"Name"}],"entryHasNewAuthor":true,"changeLog":{"changedBy":{"id":11,"username":"NishAdmin","bio":"","email":"testAdmin@nowhere.com","class":"class marketplace.Profile","uuid":"d438cf33-e8ce-41e4-b3c3-a42e4170f8c5","displayName":"Nish Patel Admin"},"id":6,"objectVersion":5,"changeDate":"Dec 31, 2013 11:30:07 AM EST ","objectClassName":"marketplace.ServiceItem","description":"update","objectId":29}};

    changelogs.RESPONSE = {
        success: true,
        totalCount: 9,
        rows: [
            changelogs.CREATED,
            changelogs.INSIDE,
            changelogs.OUTSIDE,
            changelogs.APPROVED,
            changelogs.REQUIREMENT_ADDED,
            changelogs.REQUIREMENT_REMOVED,
            changelogs.SCORECARD_UPDATED,
            changelogs.REJECTED,
            changelogs.MODIFIED
        ]
    };

    return changelogs;
});
