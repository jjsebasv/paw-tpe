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
          });

    }]);
});
