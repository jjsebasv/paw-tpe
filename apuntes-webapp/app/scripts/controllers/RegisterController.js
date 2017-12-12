'use strict';
define([
  'frontend',
  'services/sessionService',
  'services/spinnerService',
  'services/universityService',
  'services/errormodalService',
  'services/programService'
], function(frontend) {

    frontend.controller('RegisterController', [
      'sessionService', 'md5', '$location', 'universityService', 'programService', 'spinnerService', 'errormodalService', '$rootScope',
      function(sessionService, md5, $location, universityService, programService, spinnerService, errormodalService, $rootScope) {
        var _this = this;

        this.register = function () {
          sessionService.register(
            _this.name,
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
              $rootScope.registered = true;
              errormodalService.showErrorModal();
              $location.path('/');
              console.log('success');
            }).catch(
              function (error) {
                $rootScope.errors.push(error.data);
                errormodalService.showErrorModal();
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

          _this.programs = null;

          programService.getUniPrograms(_this.selectedUniversity).then(
            function(result) {
              _this.programs = result.data.programList;
            });
        };

        this.validate = function() {
          if (_this.name &&
              _this.username &&
              _this.password &&
              _this.repassword &&
              _this.mail &&
              _this.selectedUniversity &&
              _this.selectedProgram &&
              _this.recoveryQuestion &&
              _this.recoveryAnswer &&
              _this.password === _this.repassword) {
                console.log(true);
                _this.canContinue = true;
              } else {
                console.log(false);
                _this.canContinue = false;
              }
        };

    }]);
});
