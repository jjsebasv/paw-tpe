'use strict';

define(['frontend', 'services/errormodalService'], function(frontend) {
    frontend.directive('errormodal', function(errormodalService) {
        return {
            restrict: 'E',
            scope: {
              errors: '='
            },
            replace: false,
            templateUrl: '../../views/_errormodalDirective.html',
            link: function($scope, element, attrs) {
              $scope.closeModal = function(){
                errormodalService.hideErrorModal();
              }
            }
        };
    });
    return frontend;
});
