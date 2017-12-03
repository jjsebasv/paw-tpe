'use strict';
define(['frontend',
    'services/programService',
    'services/universityService',
    'services/spinnerService',
    'directives/programDirective',
    'directives/searchboxDirective',
    'directives/backDirective'
  ],
  function(frontend) {

    frontend.controller('UniversityController', [
      'programService', 'universityService', '$routeParams', '$location', '$state', '$route', 'spinnerService', '$q',
      function(programService, universityService, $routeParams, $location, $state, $route, spinnerService, $q) {
        var _this = this;
        var uniId = $routeParams.universityId;
        spinnerService.showSpinner();
        var promises = [];


        var getUniversityPromise = universityService.getUniversity(uniId).then(
          function(result) {
            _this.university = result.data;
          });
        promises.push(getUniversityPromise);

        var getUniProgramsPromise = programService.getUniPrograms(uniId).then(
          function(result) {
            _this.programs = result.data.programList;
          });
        promises.push(getUniProgramsPromise)


        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
        });

      }
    ]);

  });
