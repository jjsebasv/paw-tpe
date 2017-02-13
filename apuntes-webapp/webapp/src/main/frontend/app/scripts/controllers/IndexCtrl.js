'use strict';
define(['frontend', 'directives/searchboxDirective'], function(frontend) {

	frontend.controller('IndexCtrl', [
		'ngDialog',
		function(ngDialog) {
		console.log('index js');
    this.openLogin = function () {
			console.log('Open login');
      ngDialog.open({ template: '../views/_loginModal.html', className: 'ngdialog-theme-default' });
    };
	}]);
});
