'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('LoginController', [
      'sessionService',
      function(sessionService) {
        var _this = this;
        this.login = function () {
          var name = _this.loginForm.name;
          debugger;
        };

        this.loginData = function() {
          debugger
          sessionService.loginData(_this.username, _this.password).then(
            function (response) {
              debugger
            }).catch(
              function(error){
                  debugger
              });

        }
        // this.login();
    }]);
});
