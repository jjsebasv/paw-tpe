'use strict';
define(['frontend', 'services/programService', 'services/universityService', 'directives/programDirective'],
 function(frontend) {

    frontend.controller('UniversityController', [
    'programService', 'universityService', '$routeParams', '$location',
    function(programService, universityService, $routeParams, $location) {
      var _this = this;
      var uniId = $routeParams.universityId;

      universityService.getUniversity(uniId).then(
        function(result) {
          _this.university = result.data;
      });

      programService.getUniPrograms(uniId).then(
        function(result) {
            _this.programs = result.data.programList;
        });
    }]);

});
