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
        this.canContinue = false;
        this.step = 1;
        var passwordChanged = false;
        var promises = [];

        this.next = function() {
          spinnerService.showSpinner();
          if (_this.step === 1) {
            getQuestion();
          } else {
            resetPassword();
          }
        };

        var getQuestion = function() {
          var getQuestionPromise = sessionService.getQuestion(_this.username).then(
            function(response) {
              _this.step = 2;
              _this.question = response.data.recoveryQuestion;
            }).catch(
              function(error) {
                console.log(error);
                $rootScope.errors.push(error.data);
              });
          promises.push(getQuestionPromise);
          $q.all(promises).then(function() {
            spinnerService.hideSpinner();
            errormodalService.showErrorModal();
          });
        };

        var resetPassword = function() {
          if (_this.password !== _this.repassword) {
            $rootScope.errors.push({
              field: 'repassword',
              code: 27
            });
            errormodalService.showErrorModal();
          } else {
            var resetPasswordPromise = sessionService.resetPassword(_this.username, _this.answer, _this.password).then(
              function () {
                passwordChanged = true;
              }).catch(
                function (error) {
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
          }
        };

        this.validate = function() {
          if (_this.step === 1 && (angular.isUndefined(_this.username) || _this.username === '')) {
            _this.canContinue = false;
          } else if (_this.step === 2 &&
            (angular.isUndefined(_this.answer) || _this.answer === '' || 
              angular.isUndefined(_this.password) || angular.isUndefined(_this.repassword) ||
            _this.password === '' || _this.repassword === '')) {
              _this.canContinue = false;
            } else {
              _this.canContinue = true;
            }
        };

    }]);
});
