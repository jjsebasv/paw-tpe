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

          uploadFile: function(file, courseid) {
            var body = {
              'courseid': courseid,
              'subject': 'holaa',
              'documentName': 'unNombre',
              'data': file,
              'description': 'estaEsLaDescripcion'
            };
            return httpRequestService.tokenedRequest('POST', 'documents/', body);
          }


				};
		}]);

});
