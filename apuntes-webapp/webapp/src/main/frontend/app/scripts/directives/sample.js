'use strict';
define(['apuntes-webapp-frontend'], function(apuntes-webapp-frontend) {

	apuntes-webapp-frontend.directive('sample', function() {
		return {
			restrict: 'E',
			template: '<span>Sample</span>'
		};
	});
});
