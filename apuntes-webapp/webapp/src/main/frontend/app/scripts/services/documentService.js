'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('documentService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getDocument: function(documentId) {
						return httpRequestService.defaultRequest('GET', 'documents/' + documentId, null);
					},

          getCourseDocuments: function(courseId) {
						return httpRequestService.defaultRequest('GET', 'courses/' + courseId + '/documents', null);
					},

					getComments: function(documentId) {
						return httpRequestService.defaultRequest('GET', 'programs/' + documentId + '/courses', null);
					},

          uploadFile: function(file, name, description, courseId) {
            var data = {
              courseid: courseId,
              subject: '',
              documentName: name,
              data: file,
              description: description
            };
            return httpRequestService.tokenedRequest('POST', 'documents', data);
          }

				};
		}]);

});
