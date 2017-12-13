'use strict';

define(['frontend', 'services/httpRequestService', 'services/errormodalService'], function(frontend) {

    frontend.directive('program', ['programService', 'errormodalService', '$window', function(programService, errormodalService, $window) {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '=',
          from: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: 'views/_programDirective.html',
        link: function($scope, $element, $attrs) {
          $scope.deleteProgram = function(programId, event) {
            event.stopPropagation();
            $scope.errors = [];
            programService.deleteProgram(programId).then(function(result) {
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

});
