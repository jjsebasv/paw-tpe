'use strict';

define(['frontend'], function(frontend) {

    frontend.controller('ApplicationController', [
      '$location',
      function($location) {

        this.goto = function(path) {
          $location.path(path);
        };

    }]);

});
