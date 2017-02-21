'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('university', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: '../../views/_universityDirective.html'
      };
    });

});
