'use strict';
define([
  'frontend',
  'services/sessionService',
  'services/universityService',
  'services/programService'
], function(frontend) {

    frontend.controller('RegisterController', [
      'sessionService', 'md5', '$location', '$rootScope', 'universityService', 'programService',
      function(sessionService, md5, $location, $rootScope, universityService, programService) {
        var _this = this;

        this.register = function () {
          sessionService.register(_this.userName, _this.username, _this.password, _this.mail).then(
            function (response) {
              debugger
              sessionService.saveToken(response.data.token, _this.username);
              $location.path('/');
              $rootScope.registered = true;
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

        universityService.getAllUnis().then(
          function(result) {
            _this.universities = result.data.universityList;
          });

        this.getPrograms = function () {
          debugger
          programService.getUniPrograms(_this.selectedUniversity).then(
            function(result) {
              _this.programs = result.data.programList;
            });
        };

    }]);
});
