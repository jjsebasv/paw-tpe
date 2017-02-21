'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('LoginController', [
      'sessionService',
      function(sessionService) {
        var _this = this;
        _this.login = function () {
          var name = _this.loginForm.name;
        };

        _this.login();
    }]);
});
