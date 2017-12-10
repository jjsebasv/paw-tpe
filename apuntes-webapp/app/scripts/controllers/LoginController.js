'use strict';
define([
  'frontend',
  'services/sessionService',
  'services/spinnerService',
  'services/errormodalService'
], function(frontend) {

    frontend.controller('LoginController', [
      'sessionService', '$location', 'localStorageService', '$state', '$q', 'spinnerService', 'errormodalService', '$rootScope',
      function(sessionService, $location, localStorageService, $state, $q, spinnerService, errormodalService, $rootScope) {
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
                $rootScope.errors.push(error.data);
                errormodalService.showErrorModal();
                //path = '/';
                //$location.path(path);
              });

          promises.push(p);

        };
    }]);
});
