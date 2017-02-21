'use strict';
define(['frontend', 'services/programService', 'services/courseService',
 'directives/courseDirective', 'directives/searchboxDirective'], function(frontend) {

    frontend.controller('ProgramController', [
      'programService', 'courseService', '$routeParams', '$location', '$route',
      function(programService, courseService, $routeParams, $location, $route) {
        var _this = this;
        var programId = $routeParams.programId;

        programService.getProgram(programId).then(function(result) {
          _this.program = result.data;
        });

        courseService.getProgramCourses(programId).then(
          function(result) {
            _this.courses = result.data.courseList;
          });

        this.route = $route;

    }]);
});
