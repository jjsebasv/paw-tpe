'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('LoginController', [
      'sessionService', 'md5',
      function(sessionService, md5) {
        var _this = this;

        this.login = function () {
          var name = _this.loginForm.name;
          debugger;
        };

        this.loginData = function() {
          debugger
          console.log(md5.createHash(_this.password || ''));
          sessionService.loginData(_this.username, md5.createHash(_this.password || '')).then(
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
