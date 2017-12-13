'use strict';
define(['frontend', 'services/courseService', 'services/errormodalService'], function(frontend) {

    frontend.directive('course', ['courseService', 'errormodalService', function(courseService, errormodalService) {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          code: '=',
          id: '=',
          from: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: 'views/_courseDirective.html',
        link: function($scope, $element, $attrs) {
          $scope.deleteCourse = function(universityId, event) {
            $scope.errors = [];
            event.stopPropagation();
            courseService.deleteCourse(universityId).then(function(result) {
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
