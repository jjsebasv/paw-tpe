'use strict';

define(['frontend', 'services/errormodalService'], function(frontend) {
    frontend.directive('errormodal', function(errormodalService) {
        return {
            restrict: 'E',
            scope: {
              errors: '='
            },
            replace: false,
            templateUrl: 'views/_errormodalDirective.html',
            link: function($scope, element, attrs) {
              var notFoundTypes = [
                'Client',
                'Course',
                'Relationship',
                'Program',
                'Document',
                'Review',
                'University'
              ];

              $scope.closeModal = function() {
                errormodalService.hideErrorModal();
              };

              $scope.identifyError = function(error) {
                if (error.status === 403) {
                  return 'E_UNAUTHORIZED';
                } else if (error.status === 404) {
                  if (error.message.includes('[EMPTY]')) {
                    return 'E_NO_' + error.message.split(' ')[1].toUpperCase();
                  } else {
                    var errorCode = notFoundTypes.indexOf(error.message.split(' ')[0]);
                    if (errorCode >= 0) {
                      return 'E_404_' + errorCode;
                    } else {
                      return 'E_404';
                    }
                  }
                } else if (error.code) {
                  return 'E_' + error.code;
                } else {
                  return 'ERROR';
                }
              };
            }
        };
    });
    return frontend;
});
