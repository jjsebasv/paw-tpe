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
    'ngFileUpload',
    'localytics',
    'rt',
    'angular-mandrill'
  ],
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
      'ngFileUpload',
      'localytics.directives',
      'rt.select2',
      'angular-mandrill'
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
                $routeProvider.when(path, {
                  templateUrl: route.templateUrl,
                  resolve: dependencyResolverFor(['controllers/' + route.controller]),
                  controller: route.controller,
                  gaPageTitle: route.gaPageTitle
                });
              });
            }
            if (config.defaultRoutePath !== undefined) {
              $routeProvider.otherwise({
                redirectTo: config.defaultRoutePath
              });
            }

            $translateProvider.translations('es', i18n.es);
            $translateProvider.translations('en', i18n.en);
            $translateProvider.preferredLanguage('es');
          }
        ]);
    frontend
      .run(
        ['$rootScope',
          'localStorageService', '$translate',
          function($rootScope, localStorageService, $translate) {
            if ($rootScope.client !== localStorageService.get('client')) {
              $rootScope.client = localStorageService.get('client');
            };

            $rootScope.lang = $translate.proposedLanguage();

            var requireLogin = function(path) {
              return path.includes('profile') || path.includes('upload');
            };

            var comesFromLogin = function(path) {
              return path.includes('login');
            };

            var alreadyLoggedIn = function(path) {
              return (path.includes('login') || path.includes('register')) && ($rootScope.client !== null);
            };

            $rootScope.$on('$locationChangeStart',
              function(event, next, current) {
                var requiresLogin = requireLogin(next);
                var sessionAvailable = localStorageService.get('client');
                var loginTrue = alreadyLoggedIn(next);
                if ((!comesFromLogin(current) && !sessionAvailable && requiresLogin) || loginTrue) {
                  event.preventDefault();
                }
              }
            );
          }
        ]);
    return frontend;
  }
);
