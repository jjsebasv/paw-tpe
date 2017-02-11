'use strict';
define(['frontend', 'services/courseService'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', '$routeParams',
      function(courseService, $routeParams) {
        var _this = this;
        courseService.getCourse($routeParams.courseId).then(function(result) {
          _this.course = result.data;
        });
    }]);
});
