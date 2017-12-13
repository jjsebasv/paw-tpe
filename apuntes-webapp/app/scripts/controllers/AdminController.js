'use strict';
define(['frontend', 'services/adminService','services/universityService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('AdminController', [
        'adminService', 'universityService','$routeParams','$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
        function(adminService,universityService, $routeParams, $location, spinnerService, $q, $rootScope, errormodalService) {
            var _this = this;
            var uniAdded = false;
            var postSuccess = false;
            var update = false;
            var uniId = $routeParams.universityId;

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
                var addUniversityPromise = adminService.postUniversity(_this.universityName, _this.universityDomain).then(
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
                if(update){
                    updateUniversity()
                } else {
                    postUniversity()
                }
            }

                var getUniversityPromise = universityService.getUniversity(uniId).then(
                    function(result) {
                        shouldUpdateUniversity(result.data.name, result.data.domain);
                        promises.push(getUniversityPromise);

                    }).catch(
                    function (error) {
                        $rootScope.errors.push(error.data);
                    });


            var shouldUpdateUniversity = function(name, domain){
                if (name ==! null || name ==! undefined){
                    _this.universityName = name;
                    _this.universityDomain = domain;
                    update = true;
                }
            };

            var saveProgram = function() {
                var saveProgram = adminService.saveProgram(_this.newProgram).then(
                    function (response) {
                        promises.push(saveProgram);
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
              if (angular.isUndefined(_this.universityName) || angular.isUndefined(_this.universityDomain) ||
                  _this.universityName === '' || _this.universityDomain === '') {
                    _this.canContinue = false;
                  } else {
                    _this.canContinue = true;
                  }
            };
        }]);
});
