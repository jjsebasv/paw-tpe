'use strict';
define(['frontend'], function(frontend) {

  frontend.service('spinnerService', [
    '$rootScope',
    function($rootScope) {
      return {
        showSpinner: function() {
          $rootScope.showSpinner = true;
        },
        hideSpinner: function() {
          $rootScope.showSpinner = false;
        }
      };
    }
  ]);

});
