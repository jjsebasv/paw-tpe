'use strict';
define([
  'frontend',
  'services/documentService',
  'services/universityService',
  'services/programService',
  'services/courseService'
], function(frontend) {

    frontend.controller('UploadController', [
      'documentService', 'universityService', 'programService', 'courseService', 'Upload', '$scope',
      function(documentService, universityService, programService, courseService, Upload, $scope) {
        var _this = this;
  			universityService.getAllUnis().then(
  				function(result) {
  					_this.universities = result.data.universityList;
  				});

        this.getPrograms = function () {
          programService.getUniPrograms(_this.selectedUniversity).then(
            function(result) {
              _this.programs = result.data.programList;
            });
        };

        this.getCourses = function () {
          courseService.getProgramCourses(_this.selectedProgram).then(
            function(result) {
              _this.courses = result.data.courseList;
            });
        };

        this.chooseFile = function (element) {
          if (element.files.length) {
            _this.file = element.files[0];
          } else {
            _this.success = 0;
          }
        };

        this.upload = function () {
          var reader = new FileReader();
          reader.onload = function(e) {
            _this.encodedFile = btoa(e.target.result.toString());
            var ext = '.' + _this.file.name.split('.')[_this.file.name.split('.').length -1];
            documentService.uploadFile(_this.encodedFile, _this.fileName + ext, _this.fileDescription, _this.selectedCourse).then(
              function (response) {
                  _this.success = 1;
                }).catch(
                  function (error) {
                    _this.success = -1;
                  });
            var dataURL = reader.result;
          };
          reader.readAsBinaryString(_this.file);
        };
    }]);
});
