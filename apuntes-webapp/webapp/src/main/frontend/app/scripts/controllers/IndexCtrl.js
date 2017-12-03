'use strict';
define([
		'frontend',
		'directives/searchboxDirective',
		'directives/backDirective'
	],
	function(frontend) {

	frontend.controller('IndexCtrl',
		['$location', '$route', '$translate', 'localStorageService', '$window', '$rootScope',
		function($location, $route, $translate, localStorageService, $window, $rootScope) {

			this.client = localStorageService.get('client');

			this.dissmiss = function() {
				$rootScope.registered = false;
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

			this.changeLanguage = function() {
				$translate.use('es');
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

			this.logOut = function() {
				localStorageService.clearAll();
				localStorageService.cookie.clearAll();
				$location.path('/');
				$window.location.reload();
			};

	}]);

});
