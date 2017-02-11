'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('documentService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getDocument: function(documentId) {
						return httpRequestService.defaultRequest('GET', 'document/' + documentId, null);
					},

					getComments: function(documentId) {
						return httpRequestService.defaultRequest('GET', 'programs/' + documentId + '/courses', null);
					}
				};
		}]);

});
