'use strict';
define(['frontend'], function(frontend) {

  frontend.service('errormodalService', [
    '$rootScope',
    function($rootScope) {
      return {
        showErrorModal: function() {
          $rootScope.showErrorModal = true;
        },
        hideErrorModal: function() {
          $rootScope.errors = [];
          $rootScope.showErrorModal = false;
        }
      };
    }
  ]);

});
