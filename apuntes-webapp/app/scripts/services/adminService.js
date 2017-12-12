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
                    return httpRequestService.tokenedRequest('POST', 'universities/', data);
                }
            };
        }]);

});