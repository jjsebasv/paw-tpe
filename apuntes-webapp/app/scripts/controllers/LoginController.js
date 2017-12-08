'use strict';
define(['frontend', 'services/sessionService',
'services/spinnerService'], function(frontend) {

    frontend.controller('LoginController', [
      'sessionService', '$location', 'localStorageService', '$state', '$q', 'spinnerService',
      function(sessionService, $location, localStorageService, $state, $q, spinnerService) {
        var _this = this;

        spinnerService.hideSpinner();

        this.login = function(redirectTo) {
          var path = angular.isDefined(redirectTo) ? redirectTo : '/';
          var promises = [];
          var p = sessionService.login(_this.username, _this.password).then(
            function (response) {
              var tokenPromise = sessionService.saveToken(response.data.token, _this.username);
              promises.push(tokenPromise);
              $location.path(path);
            }).catch(
              function(error) {
                path = '/';
                $location.path(path);
              });

          promises.push(p);

        };
    }]);
});
