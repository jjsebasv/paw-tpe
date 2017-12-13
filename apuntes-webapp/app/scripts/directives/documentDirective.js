'use strict';
define(['frontend', 'services/documentService', 'services/errormodalService'], function(frontend) {

    frontend.directive('document', ['documentService', 'errormodalService', function(documentService, errormodalService) {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          author: '=',
          id: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: 'views/_documentDirective.html',
        link: function($scope, $element, $attrs) {
          $scope.deleteDocument = function(documentId, event) {
            event.stopPropagation();
            $scope.errors = [];
            documentService.deleteDocument(documentId).then(function(result) {
                console.log(result);
                location.reload();
              }).catch(function (error) {
                $scope.errors.push(error.data);
                errormodalService.showErrorModal();
                console.log(error.data);
              });
          };
        }
      };
    }]);
});
