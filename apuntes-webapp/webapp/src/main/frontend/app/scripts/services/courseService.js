'use strict';
define(['frontend'], function(frontend) {

    frontend.service('courseService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getCourse: function(courseId) {
						return httpRequestService.defaultRequest('GET', 'courses/' + courseId, null);
					}
				};
		}]);

});
