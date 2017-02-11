'use strict';

define(['frontend'], function(frontend) {

    frontend.directive('program', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '='
        },
        templateUrl: '../../views/programDirective.html'
      };
    });

});
