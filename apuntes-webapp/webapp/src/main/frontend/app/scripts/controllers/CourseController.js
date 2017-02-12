'use strict';
define(['frontend', 'services/courseService', 'services/documentService',
 'directives/documentDirective', 'directives/searchDirective'], function(frontend) {

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
    }]);
});
