'use strict';
define(['frontend', 'services/sessionService'], function(frontend) {

    frontend.controller('RegisterController', [
      'sessionService', 'md5',
      function(sessionService, md5) {
        var _this = this;

        this.register = function () {
          sessionService.register(_this.userName, _this.username, _this.password, _this.mail).then(
            function (response) {
              console.log('success');
            }).catch(
              function (error) {
                console.log('error');
              });
        };

        this.encodePass = function() {
          sessionService.encode(_this.password).then(
            function (response) {
              console.log('success');
            }).catch(
              function (error) {
                console.log('error');
              });
        };

    }]);
});
