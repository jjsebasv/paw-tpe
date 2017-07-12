'use strict';
define(['frontend', 'services/documentService', 'services/courseService', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('DocumentController', [
      'documentService', 'courseService', '$routeParams',
      function(documentService, courseService, $routeParams) {
        var _this = this;
        documentService.getDocument($routeParams.documentId).then(function(result) {
          _this.document = result.data;
          courseService.getCourse(_this.document.courseid).then(
            function (result) {
              _this.document.course = result.data.name;
            });
          _this.downloadPath = documentService.downloadFile(_this.document.documentId);
        });

        documentService.getComments($routeParams.documentId).then(function(result) {
          _this.comments = result.data;
        });

    }]);

});
