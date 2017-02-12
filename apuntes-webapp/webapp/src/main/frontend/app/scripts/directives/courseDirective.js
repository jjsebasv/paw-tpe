'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('course', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          code: '=',
          id: '='
        },
        templateUrl: '../../views/_courseDirective.html'
      };
    });

});
