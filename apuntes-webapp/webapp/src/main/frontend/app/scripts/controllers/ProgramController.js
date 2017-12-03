'use strict';
define(['frontend', 'services/programService', 'services/courseService',
 'directives/courseDirective', 'directives/searchboxDirective', 'services/spinnerService'], function(frontend) {

    frontend.controller('ProgramController', [
      'programService', 'courseService', '$routeParams', '$location', '$route', 'spinnerService', '$q',
      function(programService, courseService, $routeParams, $location, $route, spinnerService, $q) {
        var _this = this;
        var programId = $routeParams.programId;
        var promises = [];

        spinnerService.showSpinner();

        var getProgramPromise = programService.getProgram(programId).then(function(result) {
          _this.program = result.data;
        });
        promises.push(getProgramPromise);

        var getCoursesPromise = courseService.getProgramCourses(programId).then(
          function(result) {
            _this.courses = result.data.courseList;
          });

        promises.push(getCoursesPromise);

        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
        });

        this.route = $route;

    }]);
});
