'use strict';
define(['frontend', 'services/courseService', 'directives/courseDirective'], function(frontend) {

    frontend.controller('CourseController', [
      'courseService', '$routeParams',
      function(courseService, $routeParams) {
        var _this = this;
        console.log('course controller');
        courseService.getCourse($routeParams.courseId).then(function(result) {
          _this.course = result.data;
        });
    }]);
});
