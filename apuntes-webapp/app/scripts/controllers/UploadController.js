'use strict';
define([
  'frontend',
  'services/documentService',
  'services/universityService',
  'services/spinnerService',
  'services/programService',
  'services/errormodalService',
  'services/courseService'
], function(frontend) {

    frontend.controller('UploadController', [
      'documentService', 'universityService', 'programService', 'courseService', 'Upload', '$scope', '$routeParams', 'spinnerService', '$q', 'errormodalService', '$rootScope', '$location',
      function(documentService, universityService, programService, courseService, Upload, $scope, $routeParams, spinnerService, $q, errormodalService, $rootScope, $location) {
        var _this = this;
        this.courseid = $routeParams.courseId;
        spinnerService.showSpinner();

        var finishPromises = function() {
          $q.all(promises).then(function() {
              spinnerService.hideSpinner();
              errormodalService.showErrorModal();
              if (_this.success === 1) {
                $location.path('/course/' + _this.selectedCourse.courseid);
              }
          });
        };

        var promises = [];
        if (!this.courseid) {
          var allUnisPromise = universityService.getAllUnis().then(
            function(result) {
              _this.universities = result.data.universityList;
            }).catch(
              function (error) {
                $rootScope.errors.push(error.data);
              });
          promises.push(allUnisPromise);
          finishPromises();
        } else {
          var coursePromise = courseService.getCourse(this.courseid).then(
            function(result) {
              _this.selectedCourse = result.data;
            }).catch(
              function (error) {
                $rootScope.errors.push(error.data);
              });
          promises.push(coursePromise);
          finishPromises();
        };

        this.getPrograms = function () {
          spinnerService.showSpinner();
          var uniProgramPromise = programService.getUniPrograms(_this.selectedUniversity).then(
            function(result) {
              _this.programs = result.data.programList;
            }).catch(
              function (error) {
                $rootScope.errors.push(error.data);
              });
          promises.push(uniProgramPromise);
          finishPromises();
        };

        this.getCourses = function () {
          spinnerService.showSpinner();
          var programCoursePromise = courseService.getProgramCourses(_this.selectedProgram).then(
            function(result) {
              _this.courses = result.data.courseList;
            }).catch(
              function (error) {
                $rootScope.errors.push(error.data);
              });
          promises.push(programCoursePromise);
          finishPromises();
        };

        this.chooseFile = function (element) {
          if (element.files.length) {
            _this.file = element.files[0];
            $scope.$apply();
          } else {
            _this.success = 0;
          }
        };

        this.upload = function () {
          var reader = new FileReader();
          reader.onload = function(e) {
            _this.encodedFile = btoa(e.target.result.toString());
            var ext = '.' + _this.file.name.split('.')[_this.file.name.split('.').length - 1];
            spinnerService.showSpinner();
            var uploadFilePromise = documentService.uploadFile(_this.encodedFile, _this.fileName + ext, _this.fileDescription, _this.selectedCourse.courseid).then(
              function (response) {
                  _this.success = 1;
                }).catch(
                  function (error) {
                    $rootScope.errors.push(error.data);
                    _this.success = -1;
                  });
            var dataURL = reader.result;
            promises.push(uploadFilePromise);
            finishPromises();
          };
          reader.readAsBinaryString(_this.file);
        };

    }]);
});
