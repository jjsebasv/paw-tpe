'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('university', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '='
        },
        templateUrl: '../../views/_universityDirective.html'
      };
    });

});
