'use strict';

define(['frontend', 'services/documentService', 'services/errormodalService'], function(frontend) {
    frontend.directive('comment', ['documentService', 'errormodalService', '$window', function(documentService, errormodalService, $window) {
        return {
            restrict: 'E',
      			scope: {
      				ranking: '=',
      				review: '=',
      				id: '='
      			},
            replace: true,
            templateUrl: 'views/_commentDirective.html',
            link: function($scope, $element, $attrs) {
              $scope.deleteComment = function(commentId, event) {
                event.stopPropagation();
                $scope.errors = [];
                documentService.deleteComment(commentId).then(function(result) {
                    console.log(result);
                    $window.location.reload();
                  }).catch(function (error) {
                    $scope.errors.push(error.data);
                    errormodalService.showErrorModal();
                    console.log(error.data);
                  });
              };
            }
        };
    }]);
    return frontend;
});
