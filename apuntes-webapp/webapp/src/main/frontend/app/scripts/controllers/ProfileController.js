'use strict';

define(['frontend', 'services/profileService', 'directives/documentDirective', 'services/spinnerService'], function(frontend) {

    frontend.controller('ProfileController', [
      'profileService', 'localStorageService', '$location', 'spinnerService', '$q',
      function(profileService, localStorageService, $location, spinnerService, $q) {
        var _this = this;
        this.client = localStorageService.get('client');
        const promises = [];
        spinnerService.showSpinner();

        const getDocumentsPromise = profileService.getDocuments().then(
          function(response) {
            _this.files = response.data.documentList;
          });

        const getReviewsPromise = profileService.getReviews().then(
          function(response) {
              _this.reviews = response.data.reviewList;
          });

        promises.push(getDocumentsPromise);
        promises.push(getReviewsPromise);

        $q.all(promises).then(() => {
          spinnerService.hideSpinner();
        });
    }]);

});
