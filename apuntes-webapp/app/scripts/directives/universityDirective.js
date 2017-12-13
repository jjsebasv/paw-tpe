'use strict';
define(['frontend', 'services/universityService', 'services/errormodalService'], function(frontend) {

    frontend.directive('university', ['universityService', 'errormodalService', function(universityService, errormodalService) {
      return {
        restrict: 'E',
        scope: {
          name: '=',
          id: '=',
          goto: '&',
          edit: '&',
        },
        replace: true,
        templateUrl: 'views/_universityDirective.html',
        link: function($scope, $element, $attrs) {
          $scope.deleteUniversity = function(universityId, event) {
            event.stopPropagation();
            $scope.errors = [];
            universityService.deleteUniversity(universityId).then(function(result) {
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
