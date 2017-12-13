'use strict';
define(['frontend', 'services/documentService'], function(frontend) {

    frontend.directive('document', [ 'documentService', function(documentService) {
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
            documentService.deleteDocument(documentId).then(function(result) {
                console.log(result);
              }).catch(function (error) {
                console.log(error.data);
              });
          }
        }
      };
    }]);
});
