'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('programService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getProgram: function(programId) {
						return httpRequestService.defaultRequest('GET', 'programs/' + programId, null);
					},
					getUniPrograms: function(universityId) {
						return httpRequestService.defaultRequest('GET', 'universities/' + universityId + '/programs', null);
					},
          getAllPrograms: function() {
            return httpRequestService.defaultRequest('GET', 'programs', null);
          },
					deleteProgram: function(programId) {
						return httpRequestService.tokenedRequest('DELETE', 'programs/' + programId, null);
					}
				};
		}]);

});
