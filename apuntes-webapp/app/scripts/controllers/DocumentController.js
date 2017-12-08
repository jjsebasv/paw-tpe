'use strict';
define(['frontend', 'services/documentService', 'services/courseService', 'directives/searchboxDirective','services/spinnerService'], function(frontend) {

    frontend.controller('DocumentController', [
      'documentService', 'courseService', '$routeParams', 'localStorageService', 'spinnerService', '$q',
      function(documentService, courseService, $routeParams, localStorageService, spinnerService, $q) {
        var _this = this;
        this.requestSent = false;
        this.alreadyUploaded = false;
        spinnerService.showSpinner();
        var promises = [];

        var finishPromises = function() {
          $q.all(promises).then(function() {
            spinnerService.hideSpinner();
          });
        };

        var getDocumentPromise = documentService.getDocument($routeParams.documentId).then(function(result) {
          _this.document = result.data;
          var getCoursePromise = courseService.getCourse(_this.document.courseid).then(
            function (result) {
              _this.document.course = result.data.name;
            });
          var downloadFilePromise = _this.downloadPath = documentService.downloadFile(_this.document.documentId);
          promises.push(getCoursePromise);
          promises.push(downloadFilePromise);
          finishPromises();

          _this.downloadPath = documentService.downloadFile(_this.document.documentId);
          _this.previewPath = documentService.previewFile(_this.document.documentId);
        });

        var getComments = function() {
          spinnerService.showSpinner();
          var getCommentsPromise = documentService.getComments($routeParams.documentId).then(function(result) {
            _this.comments = result.data.reviewList;
            angular.forEach(_this.comments, function(value, key) {
              if (value.userid === localStorageService.get('client').clientId) {
                _this.alreadyUploaded = true;
                return;
              }
            });
          });
          promises.push(getCommentsPromise);
          finishPromises();
        };

        getComments();

        this.postComment = function() {
          _this.requestSent = true;
          spinnerService.showSpinner();
          var postCommentPromise = documentService.postComment(_this.document.documentId, _this.document.userid, _this.reviewText, _this.reviewRanking).then(
            function (response) {
              getComments();
              _this.reviewRanking = null;
              _this.reviewText = null;
            });
          promises.push(postCommentPromise);
          finishPromises();
        };

        this.checkOwnDocument = function() {
          if (angular.isDefined(_this.document)) {
            return _this.document.userid === localStorageService.get('client').clientId;
          }
          return false;
        };

    }]);

});
