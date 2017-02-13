'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('sessionService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					login: function(username, password) {
						return httpRequestService.defaultRequest('POST', 'login?username=' + username + '&password=' + password);
					}
				};
		}]);

});
