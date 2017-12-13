'use strict';
define(['frontend', 'services/courseService', 'services/documentService','services/errormodalService','services/spinnerService',
 'directives/documentDirective', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', 'documentService', '$routeParams', 'Upload', '$scope', 'spinnerService', '$q', 'errormodalService', '$rootScope',
      function(courseService, documentService, $routeParams, Upload, $scope, spinnerService, $q, errormodalService, $rootScope) {
        var _this = this;
        this.courseId = $routeParams.courseId;
        this.documents = [];
        var promises = [];
        spinnerService.showSpinner();
        debugger;
        var getCoursePromise = courseService.getCourse(this.courseId).then(function(result) {
          _this.course = result.data;
        }).catch(
          function (error) {
            $rootScope.errors.push(error.data);
          });

        var getCourseDocumentsPromise = documentService.getCourseDocuments(this.courseId).then(
          function(result) {
            _this.documents = result.data.documentList;
          }).catch(
            function (error) {
              $rootScope.errors.push(error.data);
            });

        promises.push(getCoursePromise);
        promises.push(getCourseDocumentsPromise);

        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
          errormodalService.showErrorModal();
        });

    }]);
});
