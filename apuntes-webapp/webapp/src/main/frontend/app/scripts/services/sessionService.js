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
              username: usern,
              password: pass
            };
            return httpRequestService.defaultRequest('POST', 'clients/login', data);
          },

          register: function(userName, user, pass, mail) {
            var data = {
              name: userName,
              username: user,
              password: pass,
              email: mail,
              programId: 1,
              universityId: 1
            };
            return httpRequestService.defaultRequest('POST', 'clients/register', data);
          }
				};
		}]);

});
