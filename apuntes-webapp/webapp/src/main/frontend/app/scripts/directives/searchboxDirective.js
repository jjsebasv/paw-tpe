'use strict';

define(['frontend'], function(frontend) {

    frontend.directive('searchbox', function() {
      return {
        restrict: 'E',
        scope: {
          filter: '=',
          placeholder: '='
        },
        templateUrl: '../../views/_searchboxDirective.html'
      };
    });

});
