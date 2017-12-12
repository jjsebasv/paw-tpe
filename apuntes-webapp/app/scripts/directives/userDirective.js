'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('user', function() {
      return {
        restrict: 'E',
        scope: {
          name: '='
        },
        replace: true,
        templateUrl: 'views/_userDirective.html'
      };
    });

});
