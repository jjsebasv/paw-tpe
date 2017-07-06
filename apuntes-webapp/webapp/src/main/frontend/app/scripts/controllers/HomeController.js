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
		'universityService', 'sessionService', '$route',
		function(universityService, sessionService, $route) {
			var _this = this;
			universityService.getAllUnis().then(
				function(result) {
					_this.universities = result.data.universityList;
				});

	}]);
});
