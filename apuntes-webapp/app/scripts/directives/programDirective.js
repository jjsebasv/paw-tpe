'use strict';

define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.directive('program', [ 'programService', function(programService) {
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
                console.log(error.data);
              });
          }
        }
      };
    });

});
