'use strict';

define(['frontend'], function(frontend) {

    frontend.directive('search-box', function() {
      return {
        restrict: 'E',
        scope: {
          'filter-model': '=',
          placehorlder: '='
        },
        templateUrl: '../../views/_searchBoxDirective.html'
      };
    });

});
