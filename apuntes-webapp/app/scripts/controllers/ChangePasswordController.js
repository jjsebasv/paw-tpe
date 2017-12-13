'use strict';
define(['frontend', 'services/sessionService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('ChangePasswordController', [
      'sessionService', '$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
      function(sessionService, $location, spinnerService, $q, $rootScope, errormodalService) {
        var _this = this;
        var passwordChanged = false;
        var promises = [];

        this.next = function() {
          this.error = false;
          spinnerService.showSpinner();
          changePassword();
        };

        var changePassword = function() {
          if (_this.password !== _this.repassword) {
            spinnerService.hideSpinner();
            $rootScope.errors.push({
              field: 'repassword',
              code: 27
            });
            errormodalService.showErrorModal();
          } else {
            var changePasswordPromise = sessionService.changePassword(_this.password).then(
              function () {
                passwordChanged = true;
              }).catch(
                function (error) {
                  console.log(error);
                  _this.error = true;
                  $rootScope.errors.push(error.data);
                });
                promises.push(changePasswordPromise);
                $q.all(promises).then(function() {
                  spinnerService.hideSpinner();
                  errormodalService.showErrorModal();
                  if (passwordChanged) {
                    $location.path('/profile');
                  }
                });
          }
        };

        this.validate = function() {
          if (angular.isDefined(_this.password) && angular.isDefined(_this.repassword) &&
              _this.password !== '' && _this.repassword !== '') {
            _this.canContinue = true;
          } else {
            _this.canContinue = false;
          };
          console.log(_this.canContinue)
        };
    }]);
});
