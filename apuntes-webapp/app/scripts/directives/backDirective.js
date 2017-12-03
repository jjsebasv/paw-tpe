'use strict';

define(['frontend'], function(frontend) {
    frontend.directive('back', function() {
        return {
            restrict: 'E',
            replace: true,
            scope: {
              data: '&',
              goto: '&'
            },
            templateUrl: '../views/_backDirective.html'
        };
    });
    return frontend;
});
