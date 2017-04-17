'use strict';
angular.module('frontend').factory('httpRequestService',
  ['$http',
  function($http) {
    return {
      defaultRequest: function(requestMethod, requestUrl, requestData) {
        return $http({
          method: requestMethod,
          // change this to general
          url: 'http://localhost:8080/webapp/api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/json'
          },
          data: requestData
        });
      },
      encodedRequest: function(requestMethod, requestUrl, requestData) {
        return $http({
          method: requestMethod,
          // change this to general
          url: 'http://localhost:8080/webapp/api/v1/' + requestUrl,
          headers: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data: requestData
        });
      }
    };
  }]);
