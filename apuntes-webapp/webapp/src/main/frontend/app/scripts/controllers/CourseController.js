'use strict';
define(['frontend', 'services/courseService', 'services/documentService',
 'directives/documentDirective', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', 'documentService', '$routeParams',
      function(courseService, documentService, $routeParams) {
        var _this = this;
        var courseId = $routeParams.courseId;
        this.documents = [];

        courseService.getCourse(courseId).then(function(result) {
          _this.course = result.data;
        });

        documentService.getCourseDocuments(courseId).then(
          function(result) {
            _this.documents = result.data.documentList;
          });

        this.add = function() {
          var f = document.getElementById('file').files[0],
          r = new FileReader();

          r.onloadend = function(e) {
            var data = e.target.result;
            debugger
            courseService.uploadFile(data, courseId).then(
              function (response) {
                debugger
              });
            //send your binary data via $http or $resource or do anything else with it
          }

          r.readAsArrayBuffer(f);
        };
    }]);
});
