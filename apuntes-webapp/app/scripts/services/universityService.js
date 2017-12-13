'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('universityService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getUniversity: function(universityId) {
						return httpRequestService.defaultRequest('GET', 'universities/' + universityId, null);
					},
					deleteUniversity: function(universityId) {
						return httpRequestService.tokenedRequest('DELETE', 'universities/' + universityId, null);
					},
          			getAllUnis: function() {
            			return httpRequestService.defaultRequest('GET', 'universities', null);
          			},
					updateUniversity: function(universityId, universityName, universityDomain) {
                        var data = {
                            name: universityName,
                            domain: universityDomain
                        };
                        return httpRequestService.tokenedRequest('PUT', 'universities/'+universityId, data);
                    }

                };
		}]);

});
