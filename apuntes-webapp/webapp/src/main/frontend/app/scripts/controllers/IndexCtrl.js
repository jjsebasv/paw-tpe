'use strict';
define([
		'frontend',
		'directives/searchboxDirective'
	],
	function(frontend) {

	frontend.controller('IndexCtrl',
		['$location',
		function($location) {
			console.log('index js');
	    this.openLogin = function () {
				console.log('Open login');
	      //ngDialog.open({ template: '../views/_loginModal.html', className: 'ngdialog-theme-default' });
	    };
      this.goto = function(path) {
        $location.path(path);
      };
	}]);
});
