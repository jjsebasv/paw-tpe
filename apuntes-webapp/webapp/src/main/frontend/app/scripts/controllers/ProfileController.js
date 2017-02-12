'use strict';

define(['frontend', 'services/profileService'], function(frontend) {

    frontend.controller('ProfileController', [
      'profileService', '$routeParams', '$location',
      function(profileService, $routeParams, $location) {
        var _this = this;
        var profileId = $routeParams.userId;
        this.unauthorized = false;

        profileService.getProfile(profileId).then(
          function(response) {
            _this.profie = response.client;
          }).catch(
          function(error) {
            if (error.status === 403) {
              // FIXME redirect to login
              _this.unauthorized = true;
              $location.path('/#/');
            }
          });

        // FIXME Check this when user login is allowed
        profileService.getDocuments().then(
          function(response) {
              _this.files = response.documentList;
          });

        profileService.getReviews().then(
          function(response) {
              _this.reviews = response.reviewList;
          });


    }]);

});
