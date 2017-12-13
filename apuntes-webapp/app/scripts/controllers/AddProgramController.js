'use strict';
define(['frontend', 'services/adminService','services/universityService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('AddProgramController', [
        'adminService', 'universityService','$routeParams','$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
        function(adminService,universityService, $routeParams, $location, spinnerService, $q, $rootScope, errormodalService) {
            var _this = this;

            var promises = [];
            var path = $location.path();
            var postSuccess = false;


            this.next = function() {
                this.error = false;
                spinnerService.showSpinner();
                submit();
            };

            var finishPromises = function() {
                $q.all(promises).then(function() {
                    spinnerService.hideSpinner();
                    errormodalService.showErrorModal();
                    if (postSuccess) {
                      $location.path('/programs');
                    }
                });
            };

            universityService.getAllUnis().then(
              function(result) {
                _this.universities = result.data.universityList;
              }).catch(
                function(error) {
                  $rootScope.errors.push(error.data);
                }).finally(
                  function() {
                    spinnerService.hideSpinner();
                    errormodalService.showErrorModal();
                });

            var submit = function(){
              spinnerService.showSpinner();
              var postProgramPromise = adminService.postProgram(_this.programName, _this.shortName, _this.selectedUniversity).then(
                  function (response) {
                      postSuccess = true;
                  }).catch(
                  function (error) {
                      console.log(error);
                      _this.error = true;
                      $rootScope.errors.push(error.data);
                      spinnerService.hideSpinner();
                  });
                promises.push(postProgramPromise);
                finishPromises();
            }

        }]);
});
