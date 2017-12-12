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
            debugger
            _this.users = result.data.clientList;
          }).catch(
            function (error) {
              $rootScope.errors.push(error.data);
            });
        promises.push(getUsersPromise);


        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
          errormodalService.showErrorModal();
        });

      }
    ]);

  });
