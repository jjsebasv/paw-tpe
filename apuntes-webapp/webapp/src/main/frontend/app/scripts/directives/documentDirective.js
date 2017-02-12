'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('document', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          author: '=',
          id: '='
        },
        templateUrl: '../../views/_documentDirective.html'
      };
    });

});
