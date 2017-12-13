'use strict';
define(['frontend', 'services/universityService'], function(frontend) {

    frontend.directive('university', ['universityService', function(universityService) {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '=',
          goto: '&'
        },
        replace: true,
        templateUrl: 'views/_universityDirective.html',
        link: function($scope, $element, $attrs) {
          $scope.deleteUniversity = function(universityId, event) {
            event.stopPropagation();
            universityService.deleteUniversity(universityId).then(
              function(result) {
                console.log(result);
              }).catch(
                function (error) {
                  console.log(error.data);
              });
          }
        }
      };
    }]);

});
