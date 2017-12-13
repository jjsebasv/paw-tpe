'use strict';
define(['frontend', 'services/adminService','services/universityService','services/errormodalService','services/spinnerService'], function(frontend) {

    frontend.controller('AdminController', [
        'adminService', 'universityService','$routeParams','$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
        function(adminService,universityService, $routeParams, $location, spinnerService, $q, $rootScope, errormodalService) {
            var _this = this;
            var uniAdded = false;
            var postSuccess = false;
            var uniId = $routeParams.universityId;

            var promises = [];
            var path = $location.path();
            this.update = path.includes('edit');
            this.addCourse = path.includes('/courses/add');
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

            if(this.update === false && this.addCourse === false){
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
            }

            var postUniversity = function() {
                var addUniversityPromise = adminService.postUniversity(_this.universityName, _this.universityDomain).then(
                    function (response) {
                        postSuccess = true;
                        promises.push(addUniversityPromise);
                        finishPromises();
                    }).catch(
                      function (error) {
                        console.log(error);
                        _this.error = true;
                        $rootScope.errors.push(error.data);
                    });
                promises.push(addUniversityPromise);
                finishPromises();
            };

            var updateUniversity = function() {
                postSuccess = false;
                var addUniversityPromise = universityService.updateUniversity(uniId,_this.universityName, _this.universityDomain).then(
                    function (response) {
                        postSuccess = true;
                        promises.push(addUniversityPromise);
                        finishPromises();
                    }).catch(
                    function (error) {
                        console.log(error);
                        _this.error = true;
                        $rootScope.errors.push(error.data);
                    });
                    promises.push(addUniversityPromise);
                    finishPromises();

            };

            var postCourse = function() {
                var postCoursePromise = adminService.postCourse(_this.courseCode, _this.courseName).then(
                    function (response) {
                        postSuccess = true;
                        promises.push(postCoursePromise);
                        finishPromises();
                    }).catch(
                    function (error) {
                        console.log(error);
                        _this.error = true;
                        $rootScope.errors.push(error.data);
                    });
                promises.push(postCoursePromise);
                finishPromises();
            };

            var submit = function(){
                if(_this.update){
                    updateUniversity()
                } else if(_this.addCourse){
                    postCourse()
                }
                else {
                    postUniversity()
                }
            }

            if(this.update){
                var getUniversityPromise = universityService.getUniversity(uniId).then(
                    function(result) {
                        populate(result.data.name, result.data.domain);
                        promises.push(getUniversityPromise);

                    }).catch(
                    function (error) {
                        $rootScope.errors.push(error.data);
                    });
            }

            var populate = function(name, domain){
                    _this.universityName = name;
                    _this.universityDomain = domain;
            };

            this.validate = function() {
              if (!_this.addCourse && (angular.isUndefined(_this.universityName) || angular.isUndefined(_this.universityDomain) ||
                  _this.universityName === '' || _this.universityDomain === '')){
                    _this.canContinue = false;
              } else if (_this.addCourse && (angular.isUndefined(_this.courseCode) || angular.isUndefined(_this.courseName) ||
                _this.courseCode === '' || _this.courseName === '')) {
                _this.canContinue = false;
            } else {
                _this.canContinue = true;
            }
            };
        }]);
});
