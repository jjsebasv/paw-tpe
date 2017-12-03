'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('course', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          code: '=',
          id: '=',
          from: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: '../../views/_courseDirective.html'
      };
    });

});
