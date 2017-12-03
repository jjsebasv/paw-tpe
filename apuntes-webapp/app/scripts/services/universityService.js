'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('universityService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getUniversity: function(universityId) {
						return httpRequestService.defaultRequest('GET', 'universities/' + universityId, null);
					},

          getAllUnis: function() {
            return httpRequestService.defaultRequest('GET', 'universities', null);
          }

				};
		}]);

});
