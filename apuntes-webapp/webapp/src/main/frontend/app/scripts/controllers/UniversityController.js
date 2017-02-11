'use strict';
define(['frontend', 'services/programService', 'services/universityService'],
 function(frontend) {

    frontend.controller('UniversityController', [
    'programService', 'universityService', '$routeParams',
    function(programService, universityService, $routeParams) {
      var _this = this;
      programService.getUniPrograms($routeParams.universityId).then(
        function(result) {
            _this.programs = result.data.programList;
            debugger
        });
    }]);

});
