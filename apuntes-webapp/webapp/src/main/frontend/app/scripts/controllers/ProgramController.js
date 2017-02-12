'use strict';
define(['frontend', 'services/programService', 'services/courseService',
 'directives/courseDirective', 'directives/searchDirective'], function(frontend) {

    frontend.controller('ProgramController', [
      'programService', 'courseService', '$routeParams', '$location',
      function(programService, courseService, $routeParams, $location) {
        var _this = this;
        var programId = $routeParams.programId;

        programService.getProgram(programId).then(function(result) {
          _this.program = result.data;
        });

        courseService.getProgramCourses(programId).then(
          function(result) {
            _this.courses = result.data.courseList;
          });

    }]);
});
