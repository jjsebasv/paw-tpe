'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('sessionService', [
			'httpRequestService', 'localStorageService',
			function(httpRequestService, localStorageService) {
        var user;
				return {

          saveToken: function(token, username) {
            localStorageService.set('sessionToken', token);
            httpRequestService.tokenedRequest('GET', 'clients/me', null).then(
              function (response) {
                localStorageService.set('client', response.data);
              });
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
          }

				};
		}]);

});
