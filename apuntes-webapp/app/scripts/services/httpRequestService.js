'use strict';
angular.module('frontend').factory('httpRequestService',
  ['$http', 'localStorageService',
  function($http, localStorageService) {
    return {
      defaultRequest: function(requestMethod, requestUrl, requestData) {
        return $http({
          method: requestMethod,
          url: 'api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json'
          },
          data: requestData
        });
      },
      tokenedRequest: function(requestMethod, requestUrl, requestData) {
        return $http({
          method: requestMethod,
          url: 'api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json',
            'X-AUTH-TOKEN': localStorageService.get('sessionToken')
          },
          data: requestData
        });
      }
    };
  }]);
