'use strict';
define(['frontend', 'services/adminService','services/universityService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('AddProgramController', [
        'adminService', 'universityService','$routeParams','$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
        function(adminService,universityService, $routeParams, $location, spinnerService, $q, $rootScope, errormodalService) {
            var _this = this;

            var promises = [];
            var path = $location.path();


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
                        $location.path('/');
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

            var postUniversity = function() {
                var addUniversityPromise = adminService.postUniversity(_this.programName, _this.selectedUniversity).then(
                    function (response) {
                        postSuccess = true;
                        promises.push(addUniversityPromise);
                        finishPromises()
                    }).catch(
                      function (error) {
                        console.log(error);
                        _this.error = true;
                        $rootScope.errors.push(error.data);
                    });
                promises.push(addUniversityPromise);
                finishPromises();
            };

            var submit = function(){
              debugger;
              adminService.postProgram(_this.programName, _this.selectedUniversity).then(
                  function (response) {
                      promises.push(saveProgram);
                      $location.path('/programs');
                  }).catch(
                  function (error) {
                      console.log(error);
                      _this.error = true;
                      $rootScope.errors.push(error.data);
                      spinnerService.hideSpinner();
                  });
            }

        }]);
});
