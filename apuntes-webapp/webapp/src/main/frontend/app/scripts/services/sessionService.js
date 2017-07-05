'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('sessionService', [
			'httpRequestService', 'localStorageService',
			function(httpRequestService, localStorageService) {
				return {

          saveToken: function(token, username) {
            return localStorageService.set('sessionToken', token) &&
              localStorageService.set('username', username);
          },

          login: function(usern, pass) {
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
          },

          getMe: function() {
            return httpRequestService.tokenedRequest('GET', 'clients/me', null);
          }
				};
		}]);

});
