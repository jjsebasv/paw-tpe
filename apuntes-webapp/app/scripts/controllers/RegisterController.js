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
        if (angular.isDefined($rootScope.backupRegister) && $rootScope.backupRegister !== {}) {
          _this.name = $rootScope.backupRegister.name,
          _this.username = $rootScope.backupRegister.username,
          _this.mail = $rootScope.backupRegister.mail,
          _this.selectedUniversity = $rootScope.backupRegister.selectedUniversity,
          _this.selectedProgram = $rootScope.backupRegister.selectedProgram,
          _this.recoveryQuestion = $rootScope.backupRegister.recoveryQuestion
        };

        this.register = function () {
          debugger
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
              $rootScope.backupRegister = {};
              $location.path('/');
              console.log('success');
            }).catch(
              function (error) {
                $rootScope.reload = true;
                $rootScope.backupRegister = {
                  name: _this.name,
                  username: _this.username,
                  mail: _this.mail,
                  selectedUniversity: _this.selectedUniversity,
                  selectedProgram: _this.selectedProgram,
                  recoveryQuestion: _this.recoveryQuestion
                };
                $rootScope.backupRegister[error.data.field] = '';
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

          _this.programs =  null;

          programService.getUniPrograms(_this.selectedUniversity).then(
            function(result) {
              _this.programs = result.data.programList;
            });
        };

    }]);
});
