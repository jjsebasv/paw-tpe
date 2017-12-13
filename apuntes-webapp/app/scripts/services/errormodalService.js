
'use strict';
define(['frontend'], function(frontend) {

  frontend.service('errormodalService', [
    '$rootScope',
    function($rootScope) {
      return {
        showErrorModal: function() {
          if ($rootScope.errors.length > 0 || $rootScope.registered) {
            var output = [];
            var keys = [];
            angular.forEach($rootScope.errors,
              function(item) {
                var key = item.code;
                if(keys.indexOf(key) === -1) {
                    keys.push(key);
                    output.push(item);
                }
            });
            $rootScope.errors = output;
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
