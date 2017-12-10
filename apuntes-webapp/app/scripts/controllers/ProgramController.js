'use strict';
define([
  'frontend',
  'services/programService',
  'services/courseService',
  'directives/courseDirective',
  'directives/searchboxDirective',
  'services/errormodalService',
  'services/spinnerService'
], function(frontend) {

    frontend.controller('ProgramController', [
      'programService', 'courseService', '$routeParams', '$location', '$route', 'spinnerService', '$q', 'errormodalService', '$rootScope',
      function(programService, courseService, $routeParams, $location, $route, spinnerService, $q, errormodalService, $rootScope) {
        var _this = this;
        var programId = $routeParams.programId;
        var promises = [];

        spinnerService.showSpinner();

        var getProgramPromise = programService.getProgram(programId).then(function(result) {
          _this.program = result.data;
        }).catch(
          function (error) {
            $rootScope.errors.push(error.data);
          });
        promises.push(getProgramPromise);

        var getCoursesPromise = courseService.getProgramCourses(programId).then(
          function(result) {
            _this.courses = result.data.courseList;
          }).catch(
            function (error) {
              $rootScope.errors.push(error.data);
            });

        promises.push(getCoursesPromise);

        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
          errormodalService.showErrorModal();
        });

        this.route = $route;

    }]);
});
