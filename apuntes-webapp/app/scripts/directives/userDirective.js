'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('user', function() {
      return {
        restrict: 'E',
        scope: {
          user: '=',
          changerole: '&'
        },
        replace: true,
        templateUrl: 'views/_userDirective.html'
      };
    });

});
