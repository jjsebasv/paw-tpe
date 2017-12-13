'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('documentService', [
			'httpRequestService', 'localStorageService',
			function(httpRequestService, localStorageService) {
				return {
					getDocument: function(documentId) {
						return httpRequestService.defaultRequest('GET', 'documents/' + documentId, null);
					},

          getCourseDocuments: function(courseId) {
						return httpRequestService.defaultRequest('GET', 'courses/' + courseId + '/documents', null);
					},

					getComments: function(documentId) {
						return httpRequestService.defaultRequest('GET', 'documents/' + documentId + '/reviews', null);
					},
					deleteDocument: function(documentId) {
						return httpRequestService.tokenedRequest('DELETE', 'documents/' + documentId, null);
					},
					deleteComment: function(commentId) {
						return httpRequestService.tokenedRequest('DELETE', 'reviews/' + commentId, null);
					},
          postComment: function(documentId, userId, reviewText, rankingValue) {

            var data = {
              fileid: documentId,
              review: reviewText,
              ranking: rankingValue
            };
            return httpRequestService.tokenedRequest('POST', 'reviews/', data);
          },

          uploadFile: function(file, name, description, courseId) {
            var data = {
              courseid: courseId,
              subject: localStorageService.get('client').name,
              documentName: name,
              data: file,
              description: description
            };
            return httpRequestService.tokenedRequest('POST', 'documents', data);
          },

          downloadFile: function(documentId) {
            return 'api/v1/documents/' + documentId + '/download';
          },

          previewFile: function(documentId) {
            return 'api/v1/documents/' + documentId + '/open';
          }

				};
		}]);

});
