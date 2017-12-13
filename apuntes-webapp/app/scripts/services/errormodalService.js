
'use strict';
define(['frontend'], function(frontend) {

  frontend.service('errormodalService', [
    '$rootScope',
    function($rootScope) {
      return {
        showErrorModal: function() {
          if ($rootScope.errors.length > 0 || $rootScope.registered) {
            $rootScope.showErrorModal = true;
          };
        },
        hideErrorModal: function() {
          $rootScope.errors = [];
          if ($rootScope.registered) {
            $rootScope.registered = false;
          };
          $rootScope.showErrorModal = false;
        }
      };
    }
  ]);

});
