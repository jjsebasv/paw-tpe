'use strict';
define(['frontend', 'services/adminService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('AdminController', [
        'adminService', '$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
        function(adminService, $location, spinnerService, $q, $rootScope, errormodalService) {
            var _this = this;
            var uniAdded = false;
            var promises = [];

            this.next = function() {
                this.error = false;
                spinnerService.showSpinner();
                postUniversity();
            };

            var finishPromises = function() {
                $q.all(promises).then(function() {
                    spinnerService.hideSpinner();
                    errormodalService.showErrorModal();
                    if (uniAdded) {
                        $location.path('/');
                    }
                });
            };

            var postUniversity = function() {
                var addUniversityPromise = adminService.postUniversity(_this.universityName, _this.universityDomain).then(
                    function (response) {
                        uniAdded = true;
                    }).catch(
                      function (error) {
                        console.log(error);
                        _this.error = true;
                        $rootScope.errors.push(error.data);
                    });
                promises.push(addUniversityPromise);
                finishPromises();
            };

            this.validate = function() {
              if (angular.isUndefined(_this.universityName) || angular.isUndefined(_this.universityDomain) ||
                  _this.universityName === '' || _this.universityDomain === '') {
                    _this.canContinue = false;
                  } else {
                    _this.canContinue = true;
                  }
            };
        }]);
});
