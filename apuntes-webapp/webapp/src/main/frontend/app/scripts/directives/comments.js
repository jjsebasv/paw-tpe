'use strict';

define(['frontend'], function(frontend) {
    frontend.directive('comments', function() {
        return {
            restrict: 'E',
      			scope: {
      				user: '=',
      				date: '=',
      				comment: '=',
      				document: '='
      			},
            template: '../views/_comment.html'
        };
    });
    return frontend;
});
