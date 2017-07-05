'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('RegisterController', [
      'sessionService', 'md5',
      function(sessionService, md5) {
        var _this = this;

        this.register = function () {
          debugger;
          sessionService.register(_this.userName, _this.username, _this.password, _this.mail).then(
            function (response) {
              debugger
            }).catch(
              function (error) {
                debugger
              });
        };

        this.encodePass = function() {
          sessionService.encode(_this.password).then(
            function (response) {
                debugger
            }).catch(
            function (error) {
                debugger
            });
        }

    }]);
});
