'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('profileService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					getProfile: function(profileId) {
						return httpRequestService.tokenedRequest('GET', 'clients/' + profileId, null);
					},

          getDocuments: function() {
            return httpRequestService.tokenedRequest('GET', 'clients/me/documents', null);
          },

          getReviews: function() {
            return httpRequestService.tokenedRequest('GET', 'clients/me/reviews', null);
          }

				};
		}]);

});
