'use strict';
define(['frontend', 'services/adminService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('AdminController', [
        'adminService', '$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
        function(adminService, $location, spinnerService, $q, $rootScope, errormodalService) {
            var _this = this;
            var passwordChanged = false;
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
                    if (passwordChanged) {
                        $location.path('/');
                    }
                });
            };

            var postUniversity = function() {
                var addUniversityPromise = adminService.postUniversity(_this.universityName, _this.universityDomain).then(
                    function (response) {
                        passwordChanged = true;
                        promises.push(addUniversityPromise);
                        finishPromises()
                    }).catch(
                    function (error) {
                        console.log(error);
                        _this.error = true;
                        $rootScope.errors.push(error.data);
                    });
            };
//fixme el boton next siempre se muestra disabled usando lo comentado.
            this.validate = function() {
                /*_this.canContinue = false;
                if (_this.universityName === '' && _this.universityDomain === '') {
                    _this.canContinue = true;
                }*/
                _this.canContinue = true;
            };
        }]);
});