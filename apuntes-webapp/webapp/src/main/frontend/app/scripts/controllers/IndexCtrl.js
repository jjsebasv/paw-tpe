'use strict';
define([
		'frontend',
		'directives/searchboxDirective',
		'directives/backDirective'
	],
	function(frontend) {

	frontend.controller('IndexCtrl',
		['$location', '$route', '$translate',
		function($location, $route, $translate) {
			console.log('index js');
			this.test = 'test';
			this.openLogin = function () {
				console.log('Open login');
	      // ngDialog.open({ template: '../views/_loginModal.html', className: 'ngdialog-theme-default' });
			};
      this.goto = function(toType, toId, fromName) {
				if (fromName !== 'isBack') {
					var current = $route.current;
					var toTypeId = '/:' + toType + 'Id';
					var backPathVar = current.$$route.originalPath.split(':')[1];
					$route.routes['/' + toType + toTypeId].from = {
						backRoute: current.$$route.originalPath.replace(':' + backPathVar, current.pathParams[backPathVar]),
						name: fromName
					};
				}
				$location.path(toType + '/' + toId);
      };

			this.getBack = function() {
				var aux = $route.current.from;
				if (aux === null | aux === undefined) {
					return {
						backRoute: '/',
						name: $translate.instant('HOME')
					};
				}
				return aux;
			};
	}]);

});
