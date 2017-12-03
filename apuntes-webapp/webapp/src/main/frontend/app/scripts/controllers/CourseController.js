'use strict';
define(['frontend', 'services/courseService', 'services/documentService', 'services/spinnerService',
 'directives/documentDirective', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', 'documentService', '$routeParams', 'Upload', '$scope', 'spinnerService', '$q',
      function(courseService, documentService, $routeParams, Upload, $scope, spinnerService, $q) {
        var _this = this;
        this.courseId = $routeParams.courseId;
        this.documents = [];
        var promises = [];
        spinnerService.showSpinner();

        var getCoursePromise = courseService.getCourse(this.courseId).then(function(result) {
          _this.course = result.data;
        });

        var getCourseDocumentsPromise = documentService.getCourseDocuments(this.courseId).then(
          function(result) {
            _this.documents = result.data.documentList;
          });

        promises.push(getCoursePromise);
        promises.push(getCourseDocumentsPromise);

        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
        });

    }]);
});
