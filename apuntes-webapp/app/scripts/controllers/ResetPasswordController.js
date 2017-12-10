'use strict';
define([
  'frontend',
  'services/sessionService',
  'services/errormodalService',
  'services/spinnerService'
], function(frontend) {

    frontend.controller('ResetPasswordController', [
      'sessionService', '$location', 'localStorageService', '$state', 'spinnerService', '$q', 'errormodalService', '$rootScope',
      function(sessionService, $location, localStorageService, $state, spinnerService, $q, errormodalService, $rootScope) {
        var _this = this;
        this.validUser = false;
        this.notValidUser = false;
        this.canContinue = false;
        var passwordChanged = false;
        var promises = [];

        this.next = function() {
          spinnerService.showSpinner();
          if (!_this.validUser) {
            _this.notValidUser = false;
            getQuestion();
          } else {
            resetPassword();
          }
        };

        var getQuestion = function() {
          var getQuestionPromise = sessionService.getQuestion(_this.username).then(
            function(response) {
                if (response.data.recoveryQuestion) {
                  _this.validUser = true;
                  _this.question = response.data.recoveryQuestion;
                } else {
                  _this.notValidUser = true;
                }
            }).catch(
              function(error) {
                console.log(error);
                _this.notValidUser = true;
                $rootScope.errors.push(error.data);
              });
          promises.push(getQuestionPromise);
          $q.all(promises).then(function() {
            spinnerService.hideSpinner();
            errormodalService.showErrorModal();
          });
        };

        var resetPassword = function() {
          var resetPasswordPromise = sessionService.resetPassword(_this.username, _this.answer, _this.password).then(
            function () {
              passwordChanged = true;
            }).catch(
              function (error) {
                console.log(error);
                _this.error = true;
                $rootScope.errors.push(error.data);
              });
          promises.push(resetPasswordPromise);
          $q.all(promises).then(function() {
            spinnerService.hideSpinner();
            if (passwordChanged) {
              $location.path('/login');
            };
            errormodalService.showErrorModal();
          });
        };

        this.validate = function() {
          if (_this.username === '') {
            _this.canContinue = false;
          } else if (_this.validUser && (_this.answer === '' || _this.password === '' || _this.password !== _this.repeatPassword)) {
            _this.canContinue = false;
            if (_this.password !== _this.repeatPassword) {
              _this.samePassword = false;
            } else {
              _this.samePassword = true;
            }
          } else {
            _this.canContinue = true;
          };
        };
    }]);
});
