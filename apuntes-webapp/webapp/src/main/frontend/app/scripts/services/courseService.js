'use strict';
define(['frontend'], function(frontend) {

	frontend.service('CourseService', [
		'$http',
		function($http) {
			return {
				getCourse: function(courseId) {
					var url = 'apiURL';
					return $http.get(url);
				}
			};
	}]);
});
