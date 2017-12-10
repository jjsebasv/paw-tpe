'use strict';
define([
		'frontend',
		'directives/searchboxDirective',
		'directives/errormodalDirective',
		'directives/backDirective',
	  'services/spinnerService'
	],
	function(frontend) {

	frontend.controller('IndexCtrl',
		['$scope', '$location', '$route', '$translate', 'localStorageService', '$window', '$rootScope', 'spinnerService', 'errormodalDirective',
		function($scope, $location, $route, $translate, localStorageService, $window, $rootScope, spinnerService, errormodalDirective) {

			this.client = localStorageService.get('client');
			$rootScope.errors = [];

			this.dissmiss = function() {
				$rootScope.registered = false;
			};

			spinnerService.hideSpinner();

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

			this.changeLanguage = function(lang) {
				$translate.use(lang);
				$rootScope.lang = lang;
			};

			this.getBack = function() {
				var aux = $route.current.from;
				if (aux === null || aux === undefined) {
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
