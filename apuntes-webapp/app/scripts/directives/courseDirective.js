'use strict';
define(['frontend', 'services/courseService'], function(frontend) {

    frontend.directive('course', ['courseService', function(courseService) {
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
            event.stopPropagation();
            universityService.deleteCourse(universityId).then(function(result) {
                console.log(result);
              }).catch(function (error) {
                console.log(error.data);
              });
          }
        }
      };
    });

});
