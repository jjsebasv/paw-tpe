'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('sessionService', [
			'httpRequestService',
			function(httpRequestService) {
				return {
					login: function(username, password) {
						return httpRequestService.defaultRequest('POST', 'clients/login?username=' + username + '&password=' + password);
					},

          loginData: function(usern, pass) {
            var data = {
              'username': usern,
              'password': pass
            };
            return httpRequestService.defaultRequest('POST', 'clients/login', data);
          }
				};
		}]);

});
