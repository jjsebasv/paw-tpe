'use strict';
define([
    'frontend',
    'services/universityService',
    'services/sessionService',
    'directives/universityDirective',
    'directives/searchboxDirective'
  ],
  function(frontend) {

	frontend.controller('HomeController', [
		'universityService', 'sessionService', '$location',
		function(universityService, sessionService, $location) {
			var _this = this;
			universityService.getAllUnis().then(
				function(result) {
					_this.universities = result.data.universityList;
				});

      sessionService.getMe().then(
        function (response) {
          $location.path(`/profile/${response.data.clientId}`)
        });
	}]);
});
