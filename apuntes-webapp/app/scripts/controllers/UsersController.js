'use strict';
define(['frontend',
    'services/adminService',
    'services/spinnerService',
    'directives/userDirective',
    'directives/searchboxDirective',
    'services/errormodalService',
    'directives/backDirective'
  ],
  function(frontend) {

    frontend.controller('UsersController', [
      'adminService', '$routeParams', '$location', '$state', '$route', 'spinnerService', '$q', 'errormodalService', '$rootScope',
      function(adminService, $routeParams, $location, $state, $route, spinnerService, $q, errormodalService, $rootScope) {
        var _this = this;
        spinnerService.showSpinner();
        var promises = [];


        var getUsersPromise = adminService.getUsers().then(
          function(result) {
            _this.users = result.data.clientList;
              finishPromises();
          }).catch(
            function (error) {
              $rootScope.errors.push(error.data);
            });
        promises.push(getUsersPromise);

        this.changeRole = function(user) {
          user.role = user.role === 'ROLE_USER' ? 'ROLE_ADMIN' : 'ROLE_USER';
          var updateUserPromise = adminService.updateUser(user).then(
            function(result) {
              user = result.data;
              finishPromises();
            }).catch(
              function(error) {
                user.role = user.role === 'ROLE_USER' ? 'ROLE_ADMIN' : 'ROLE_USER';
                $rootScope.errors.push(error.data);
              });
          spinnerService.showSpinner();
          promises.push(updateUserPromise);
        };

          var finishPromises = function() {
              $q.all(promises).then(function() {
                  spinnerService.hideSpinner();
                  errormodalService.showErrorModal();
              });
          };


      }
    ]);

  });
