'use strict';
define(['frontend'], function(frontend) {

    frontend.controller('CourseController', [
      'CourseService', '$routeParams',
      function(CourseService, $routeParams) {
        console.log('course controller')
        CourseService.getCourse($routeParams.courseId).then(function(result) {
          this.course = result.data;
        });
    }]);

});
