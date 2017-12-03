'use strict';
define([
  'frontend'
  ],
  function(frontend) {
    frontend.controller('MailController',
      ['$scope', 'Mandrill',
      function($scope, Mandrill) {

        var data = {
            'message': {
              'html': '<p>Your body text here</p>',
              'text': 'Body text',
              'subject': 'Este es el asunto',
              'from_email': 'apuntes.paw@gmail.com',
              'from_name': 'Apuntes PAW',
              'to': [
                {
                  name: 'Example name',
                  email: 'jvera@itba.edu.ar',
                  'type': 'to'
                }
              ],
              'headers': {
                'Reply-To': 'noreply@mail.com'
              }
            }
          };

          Mandrill.messages.send(data).success(function(response) {
            console.log('succesfully sent');
            console.log(response);
          }).error(function(response) {
            console.log('error');
          });
    }]);

});
