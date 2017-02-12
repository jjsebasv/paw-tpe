'use strict';
define(['frontend', 'services/universityService',
 'directives/universityDirective', 'directives/searchDirective'], function(frontend) {

	frontend.controller('HomeController', [
		'universityService',
		function(universityService) {
			var _this = this;
			universityService.getAllUnis().then(
				function(result) {
					_this.universities = result.data.universityList;
				});
	}]);
});
