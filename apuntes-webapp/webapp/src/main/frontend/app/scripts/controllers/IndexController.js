'use strict';
define(['frontend'], function(frontend) {

	frontend.controller('IndexController', [
		'$location',
		function($location) {
			this.goto = function(path, id) {
			debugger;
			$location.path(path+id);
		};
	}]);
});
