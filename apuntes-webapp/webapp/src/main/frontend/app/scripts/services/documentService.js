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
            return 'http://localhost:8080/webapp/api/v1/documents/' + documentId + '/download';
          }

				};
		}]);

});
