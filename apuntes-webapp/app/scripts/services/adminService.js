'use strict';
define(['frontend', 'services/httpRequestService'], function(frontend) {

    frontend.service('adminService', [
        'httpRequestService',
        function(httpRequestService) {
            return {
                postUniversity: function(universityName, universityDomain) {
                    var data = {
                        name: universityName,
                        domain: universityDomain
                    };
                    return httpRequestService.tokenedRequest('POST', 'universities', data);
                },

                getUsers: function() {
                  return httpRequestService.tokenedRequest('GET', 'clients', null);
                },

                updateUser: function(user) {
                  var data = {
                    name: user.name,
                    email: user.email,
                    programId: user.programId,
                    recoveryQuestion: user.recoveryQuestion,
                    role: user.role
                  };
                  return httpRequestService.tokenedRequest('POST', 'clients/' + user.clientId, data);
                },

                postCourseProgramRelation: function(course, program, semester) {
                  var data = {
                    courseId: course,
                    programId: program,
                    semester: semester
                  };
                  return httpRequestService.tokenedRequest('POST', 'c2p', data);
                },
                postProgram: function(programName, shortName, selectedUniversity) {
                    var data = {
                        name: programName,
                        shortName: shortName,
                        universityId: selectedUniversity,
                        group: 'g'
                    };
                    return httpRequestService.tokenedRequest('POST', 'programs', data);
                },
            };
        }]);

});
