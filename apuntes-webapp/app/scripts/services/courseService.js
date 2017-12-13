'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('courseService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getCourse: function(courseId) {
						return httpRequestService.defaultRequest('GET', 'courses/' + courseId, null);
					},

					getProgramCourses: function(programId) {
						return httpRequestService.defaultRequest('GET', 'programs/' + programId + '/courses', null);
					},

					deleteCourse: function(documentId) {
						return httpRequestService.tokenedRequest('DELETE', 'programs/' + documentId, null);
					},

				};
		}]);

});
