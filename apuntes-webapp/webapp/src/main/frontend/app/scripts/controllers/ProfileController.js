'use strict';

define(['frontend', 'services/profileService', 'directives/documentDirective'], function(frontend) {

    frontend.controller('ProfileController', [
      'profileService', 'localStorageService', '$location',
      function(profileService, localStorageService, $location) {
        var _this = this;
        this.client = localStorageService.get('client');

        profileService.getDocuments().then(
          function(response) {
            _this.files = response.data.documentList;
          });

        profileService.getReviews().then(
          function(response) {
              _this.reviews = response.data.reviewList;
          });

    }]);

});
