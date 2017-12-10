'use strict';
define(['frontend'], function(frontend) {

  frontend.service('errormodalService', [
    '$rootScope',
    function($rootScope) {
      return {
        showErrorModal: function() {
          if($rootScope.errors.length > 0) {
            $rootScope.showErrorModal = true;
          };
        },
        hideErrorModal: function() {
          $rootScope.errors = [];
          $rootScope.showErrorModal = false;
        }
      };
    }
  ]);

});
