'use strict';
define(['frontend', 'services/documentService', 'services/courseService', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('DocumentController', [
      'documentService', 'courseService', '$routeParams', 'localStorageService',
      function(documentService, courseService, $routeParams, localStorageService) {
        var _this = this;
        this.alreadyUploaded = false;
        documentService.getDocument($routeParams.documentId).then(function(result) {
          _this.document = result.data;
          courseService.getCourse(_this.document.courseid).then(
            function (result) {
              _this.document.course = result.data.name;
            });
          _this.downloadPath = documentService.downloadFile(_this.document.documentId);
        });

        var getComments = function() {
          documentService.getComments($routeParams.documentId).then(function(result) {
            _this.comments = result.data.reviewList;
            angular.forEach(_this.comments, function(value, key) {
              if (value.userid === localStorageService.get('client').clientId) {
                _this.alreadyUploaded = true;
                return;
              }
            });
          });
        };

        getComments();

        this.postComment = function() {
          documentService.postComment(_this.document.documentId, _this.document.userid, _this.reviewText, _this.reviewRanking).then(
            function (response) {
              getComments();
              _this.reviewRanking = null;
              _this.reviewText = null;
            });
        };

        this.checkOwnDocument = function() {
          if (angular.isDefined(_this.document)) {
            return _this.document.userid === localStorageService.get('client').clientId;
          }
          return false;
        };

    }]);

});
