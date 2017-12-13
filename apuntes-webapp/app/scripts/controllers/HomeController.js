'use strict';
define([
    'frontend',
    'services/universityService',
    'services/sessionService',
    'services/spinnerService',
    'services/errormodalService',
    'directives/universityDirective',
    'directives/errormodalDirective',
    'directives/searchboxDirective'
  ],
  function(frontend) {

	frontend.controller('HomeController', [
		'universityService', 'sessionService', '$route', 'spinnerService', 'errormodalService', '$rootScope','$location',
		function(universityService, sessionService, $route, spinnerService, errormodalService, $rootScope, $location) {
			var _this = this;
      spinnerService.showSpinner();

			universityService.getAllUnis().then(
				function(result) {
					_this.universities = result.data.universityList;
          spinnerService.hideSpinner();
				}).catch(
          function (error) {
            $rootScope.errors.push(error.data);
            errormodalService.showErrorModal();
          });

        this.editUniversity = function(id) {
            $location.path('/universities/edit/' +id);
        };

	}]);
});
