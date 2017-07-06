'use strict';
define([
		'frontend',
		'directives/searchboxDirective',
		'directives/backDirective'
	],
	function(frontend) {

	frontend.controller('IndexCtrl',
		['$location', '$route', '$translate', 'localStorageService',
		function($location, $route, $translate, localStorageService) {

			this.client = localStorageService.get('client');

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
