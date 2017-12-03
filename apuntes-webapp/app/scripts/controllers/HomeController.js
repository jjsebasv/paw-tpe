'use strict';
define([
    'frontend',
    'services/universityService',
    'services/sessionService',
    'services/spinnerService',
    'directives/universityDirective',
    'directives/searchboxDirective'
  ],
  function(frontend) {

	frontend.controller('HomeController', [
		'universityService', 'sessionService', '$route', 'spinnerService',
		function(universityService, sessionService, $route, spinnerService) {
			var _this = this;
      spinnerService.showSpinner();
			universityService.getAllUnis().then(
				function(result) {
					_this.universities = result.data.universityList;
          spinnerService.hideSpinner();
				});

	}]);
});
