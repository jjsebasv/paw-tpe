'use strict';
define(['frontend', 'services/documentService', 'directives/searchDirective'], function(frontend) {

    frontend.controller('DocumentController', [
      'documentService', '$routeParams',
      function(documentService, $routeParams) {
        var _this = this;
        documentService.getCourse($routeParams.documentId).then(function(result) {
          _this.document = result.data;
        });

        documentService.getComments($routeParams.documentId).then(function(result) {
          _this.comments = result.data;
        });
    }]);

});
