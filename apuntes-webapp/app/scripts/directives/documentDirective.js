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
        link: function($scope, $element, $attrs) {
          $scope.deleteDocument = function(documentId, event) {
            event.stopPropagation();
            universityService.deleteDocument(documentId).then(function(result) {
                console.log(result);
              }).catch(function (error) {
                console.log(error.data);
              });
          }
        }
      };
    });

});
