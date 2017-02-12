'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('profileService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getProfile: function(profileId) {
						return httpRequestService.defaultRequest('GET', 'clients/' + profileId, null);
					},

          getDocuments: function() {
            return httpRequestService.defaultRequest('GET', 'clients/me/documents', null);
          },

          getReviews: function() {
            return httpRequestService.defaultRequest('GET', 'clients/me/reviews', null);
          }

				};
		}]);

});
