'use strict';
define([
  'frontend',
  'services/sessionService',
  'services/spinnerService',
  'services/universityService',
  'services/programService'
], function(frontend) {

    frontend.controller('RegisterController', [
      'sessionService', 'md5', '$location', '$rootScope', 'universityService', 'programService', 'spinnerService',
      function(sessionService, md5, $location, $rootScope, universityService, programService, spinnerService) {
        var _this = this;

        this.register = function () {
          sessionService.register(
            _this.userName,
            _this.username,
            _this.password,
            _this.mail,
            _this.selectedUniversity,
            _this.selectedProgram,
            _this.recoveryQuestion,
            _this.recoveryAnswer
          ).then(
            function (response) {
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

          _this.programs =  null;

          programService.getUniPrograms(_this.selectedUniversity).then(
            function(result) {
              debugger;
              _this.programs = result.data.programList;
            });
        };

    }]);
});
