'use strict';
define(['frontend', 'services/documentService', 'services/errormodalService'], function(frontend) {

    frontend.directive('document', ['documentService', 'errormodalService', '$window', function(documentService, errormodalService, $window) {
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
                $window.location.reload();
                debugger;
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
