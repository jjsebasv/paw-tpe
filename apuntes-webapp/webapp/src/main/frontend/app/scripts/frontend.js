'use strict';
define(['routes',
	'services/dependencyResolverFor',
	'i18n/i18nLoader!',
	'angular',
	'angular-ui-router',
	'LocalStorageModule',
	'angular-route',
	'bootstrap',
	'ngDialog',
	'angular-md5',
	'angular-translate',
	'angularFileUpload',
	'ng.model',
	'ngFileUpload'],
	function(config, dependencyResolverFor, i18n) {
		var frontend = angular.module('frontend', [
			'ngRoute',
			'LocalStorageModule',
			'pascalprecht.translate',
			'ngDialog',
			'ui.router',
			'angular-md5',
			'angularFileUpload',
			'ng.model',
			'ngFileUpload'
		]);
		frontend
			.config(
				['$routeProvider',
				'$controllerProvider',
				'$compileProvider',
				'$filterProvider',
				'$provide',
				'$translateProvider',
				function($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $translateProvider) {

					frontend.controller = $controllerProvider.register;
					frontend.directive = $compileProvider.directive;
					frontend.filter = $filterProvider.register;
					frontend.factory = $provide.factory;
					frontend.service = $provide.service;

					if (config.routes !== undefined) {
						angular.forEach(config.routes, function(route, path) {
							$routeProvider.when(path, {templateUrl: route.templateUrl, resolve: dependencyResolverFor(['controllers/' + route.controller]), controller: route.controller, gaPageTitle: route.gaPageTitle});
						});
					}
					if (config.defaultRoutePath !== undefined) {
						$routeProvider.otherwise({redirectTo: config.defaultRoutePath});
					}

					$translateProvider.translations('preferredLanguage', i18n);
					$translateProvider.preferredLanguage('preferredLanguage');
				}]);
		frontend
			.run(
				['$rootScope',
				'localStorageService',
				function($rootScope, localStorageService) {
					var requireLogin = function(path) {
						return path.includes('profile');
					};

					var comesFromLogin = function(path) {
						return path.includes('login');
					};

					$rootScope.$on('$locationChangeStart',
						function (event, next, current) {
							var requiresLogin = requireLogin(next);
							var sessionAvailable = localStorageService.get('username');
							if (!comesFromLogin(current) && !sessionAvailable && requiresLogin) {
								event.preventDefault();
							}
						}
					);
			}]);
		return frontend;
	}
);
