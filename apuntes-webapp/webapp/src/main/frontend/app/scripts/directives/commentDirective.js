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
            template: '../views/_commentDirective.html'
        };
    });
    return frontend;
});
