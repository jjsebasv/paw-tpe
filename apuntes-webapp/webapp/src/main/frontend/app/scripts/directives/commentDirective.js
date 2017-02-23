'use strict';

define(['frontend'], function(frontend) {
    frontend.directive('comment', function() {
        return {
            restrict: 'E',
      			scope: {
      				user: '=',
      				date: '=',
      				comment: '=',
      				document: '='
      			},
            replace: true,
            templateUrl: '../views/_commentDirective.html'
        };
    });
    return frontend;
});
