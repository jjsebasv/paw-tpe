'use strict';

define(['frontend', 'services/documentService'], function(frontend) {
    frontend.directive('comment', ['documentService', function(documentService) {
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
              debugger;
              $scope.deleteComment = function(commentId, event) {
                event.stopPropagation();
                documentService.deleteComment(commentId).then(function(result) {
                    console.log(result);
                  }).catch(function (error) {
                    console.log(error.data);
                  });
              }
            }
        };
    }]);
    return frontend;
});
