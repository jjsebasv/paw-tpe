'use strict';
define(['frontend', 'directives/searchDirective'], function(frontend) {

	frontend.controller('IndexCtrl',
		function() {
		console.log('index js');
    this.openLogin = function () {
			console.log('Open login');
      // ngDialog.open({ template: '../views/_loginModal.html', className: 'ngdialog-theme-default' });
    };
	});
});
