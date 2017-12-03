'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('ResetPasswordController', [
      'sessionService', '$location', 'localStorageService', '$state', '$q',
      function(sessionService, $location, localStorageService, $state, $q) {
        var _this = this;
        this.validUser = false;
        console.log(this.validUser)

        this.next = function() {
          if (!_this.validUser) {
            getQuestion();
          } else {

          }
        };

        const getQuestion = function() {
          sessionService.getQuestion(_this.username).then(
            (response) => {
                debugger
            });

        };
    }]);
});
