'use strict';

define(['frontend', 'services/errormodalService'], function(frontend) {
    frontend.directive('errormodal', function() {
        return {
            restrict: 'E',
            scope: {
              errors: '='
            },
            replace: false,
            templateUrl: 'views/_errormodalDirective.html'
        };
    });
    return frontend;
});
