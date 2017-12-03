'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('sessionService', [
			'httpRequestService', 'localStorageService', '$rootScope',
			function(httpRequestService, localStorageService, $rootScope) {
        var user;
				return {

          saveToken: function(token, username) {
            localStorageService.set('sessionToken', token);
            return httpRequestService.tokenedRequest('GET', 'clients/me', null).then(
              function (response) {
                localStorageService.set('client', response.data);
                $rootScope.client = response.data;
              });
          },

          login: function(usern, pass) {
            var data = {
              username: usern,
              password: pass
            };
            return httpRequestService.defaultRequest('POST', 'clients/login', data);
          },

          register: function(userName, user, pass, mail, university, program) {
            var data = {
              name: userName,
              username: user,
              password: pass,
              email: mail,
              programId: program,
              universityId: university
            };
            return httpRequestService.defaultRequest('POST', 'clients/register', data);
          }

				};
		}]);

});
