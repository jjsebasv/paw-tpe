'use strict';
define(['frontend'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', '$routeParams',
      function(courseService, $routeParams) {
        console.log('course controller');
        courseService.getCourse($routeParams.courseId).then(function(result) {
          this.course = result.data;
        });
    }]);
});
