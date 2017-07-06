'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('LoginController', [
      'sessionService', '$location', 'localStorageService',
      function(sessionService, $location, localStorageService) {
        var _this = this;

        this.login = function(redirectTo) {
          sessionService.login(_this.username, _this.password).then(
            function (response) {
              var path = angular.isDefined(redirectTo) ? redirectTo : '/';
              if (sessionService.saveToken(response.data.token, _this.username)) {
                sessionService.saveUser();
                debugger
                $location.path(path);
              }
            }).catch(
              function(error){
                console.log("some weird error");
                $location.path('/');
              });
        };
    }]);
});
