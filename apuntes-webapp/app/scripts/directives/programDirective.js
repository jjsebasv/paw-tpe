'use strict';

define(['frontend'], function(frontend) {

    frontend.directive('program', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '=',
          from: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: 'views/_programDirective.html'
      };
    });

});
