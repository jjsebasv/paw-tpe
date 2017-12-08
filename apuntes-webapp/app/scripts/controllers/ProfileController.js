'use strict';

define(['frontend', 'services/profileService', 'directives/documentDirective', 'services/spinnerService'], function(frontend) {

    frontend.controller('ProfileController', [
      'profileService', 'localStorageService', '$location', 'spinnerService', '$q',
      function(profileService, localStorageService, $location, spinnerService, $q) {
        var _this = this;
        this.client = localStorageService.get('client');
        var promises = [];
        spinnerService.showSpinner();

        var getDocumentsPromise = profileService.getDocuments().then(
          function(response) {
            _this.files = response.data.documentList;
          });

        promises.push(getDocumentsPromise);

        var getReviewsPromise = profileService.getReviews().then(
          function(response) {
              _this.reviews = response.data.reviewList;
          });

        promises.push(getReviewsPromise);

        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
        });
    }]);

});
