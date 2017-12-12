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

          register: function(userName, user, pass, mail, university, program, question, answer) {
            var data = {
              idName: userName,
              name: user,
              password: pass,
              email: mail,
              programId: program,
              universityId: university,
              recoveryQuestion: question,
              secretAnswer: answer
            };
            return httpRequestService.defaultRequest('POST', 'clients/register', data);
          },

          getQuestion: function(user) {
            var data = {
              name: user
            };
            return httpRequestService.defaultRequest('POST', 'clients/reset_password/question', data);
          },

          resetPassword: function(user, answer, newPassword) {
            var data = {
              name: user,
              secretAnswer: answer,
              password: newPassword
            };
            return httpRequestService.defaultRequest('POST', 'clients/reset_password', data);
          },

          changePassword: function(newPassword) {
            var data = {
              password: newPassword
            };
            return httpRequestService.tokenedRequest('POST', 'clients/me/change_password', data);
          }

				};
		}]);

});
