'use strict';
define(['frontend', 'services/courseService', 'services/documentService',
 'directives/documentDirective', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', 'documentService', '$routeParams', 'Upload', '$scope',
      function(courseService, documentService, $routeParams, Upload, $scope) {
        var _this = this;
        this.courseId = $routeParams.courseId;
        this.documents = [];

        courseService.getCourse(this.courseId).then(function(result) {
          _this.course = result.data;
        });

        documentService.getCourseDocuments(this.courseId).then(
          function(result) {
            _this.documents = result.data.documentList;
            debugger
          });

        $scope.chooseFile = function (element) {
          _this.theFile = element.files[0];
        };

        this.upload = function () {
          debugger
          var reader = new FileReader();
          reader.onload = function(e){
            console.log("about to encode");
            _this.encoded_file = btoa(e.target.result.toString());
            debugger
            documentService.uploadFile(_this.encoded_file, _this.courseId).then(
              function (response) {
                  debugger
                });
            var dataURL = reader.result;
          };
          reader.readAsBinaryString(_this.theFile);
        };
    }]);
});
