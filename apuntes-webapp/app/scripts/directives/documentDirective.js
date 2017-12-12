'use strict';
define(['frontend'], function(frontend) {

    frontend.directive('document', function() {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          author: '=',
          id: '=',
          goto: '&'
        },
        replace: true,
      };
    });

});
