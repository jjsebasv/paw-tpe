'use strict';

define(['frontend', 'services/httpRequestService', 'services/errormodalService'], function(frontend) {

    frontend.directive('program', ['programService', 'errormodalService', function(programService) {
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
            programService.deleteProgram(programId).then(function(result) {
                console.log(result);
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
