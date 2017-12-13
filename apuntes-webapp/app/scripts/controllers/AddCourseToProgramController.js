'use strict';
define(['frontend', 'services/adminService', 'services/programService', 'services/courseService', 'services/errormodalService', 'services/spinnerService'], function(frontend) {

  frontend.controller('AddCourseToProgramController', [
    'adminService', 'programService', 'courseService', '$routeParams', '$location', 'spinnerService', '$q', '$rootScope', 'errormodalService',
    function(adminService, programService, courseService, $routeParams, $location, spinnerService, $q, $rootScope, errormodalService) {
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

      programService.getAllPrograms().then(
        function(result) {
          _this.programs = result.data.programList;
        }).catch(
        function(error) {
          $rootScope.errors.push(error.data);
        }).finally(
        function() {
          spinnerService.hideSpinner();
          errormodalService.showErrorModal();
        });

      courseService.getAllCourses().then(
        function(result) {
          _this.courses = result.data.courseList;
        }).catch(
        function(error) {
          $rootScope.errors.push(error.data);
        }).finally(
        function() {
          spinnerService.hideSpinner();
          errormodalService.showErrorModal();
        });

      var submit = function() {
        spinnerService.showSpinner();
        var promise = adminService.postCourseProgramRelation(_this.selectedCourse, _this.selectedProgram, parseInt(_this.semester)).then(
          function(response) {
            postSuccess = true;
          }).catch(
          function(error) {
            console.log(error);
            _this.error = true;
            $rootScope.errors.push(error.data);
            spinnerService.hideSpinner();
          });
        promises.push(promise);
        finishPromises();
      };

        this.validate = function() {
            if (angular.isUndefined(_this.selectedCourse) || angular.isUndefined(_this.selectedProgram) || angular.isUndefined(_this.semester) ||
                _this.selectedCourse === '' || _this.selectedProgram === '' || _this.semester === ''){
                _this.canContinue = false;
            } else {
                _this.canContinue = true;
            }
        };

    }
  ]);
});
