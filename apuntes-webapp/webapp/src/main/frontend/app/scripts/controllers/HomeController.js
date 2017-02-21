'use strict';
define([
    'frontend',
    'services/universityService',
    'directives/universityDirective',
    'directives/searchboxDirective'
  ],
  function(frontend) {

	frontend.controller('HomeController', [
		'universityService', '$route',
		function(universityService, $route) {
			var _this = this;
			universityService.getAllUnis().then(
				function(result) {
					_this.universities = result.data.universityList;
				});
	}]);
});
