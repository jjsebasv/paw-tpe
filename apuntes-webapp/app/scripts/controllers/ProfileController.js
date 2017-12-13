'use strict';

define([
  'frontend',
  'services/profileService',
  'directives/documentDirective',
  'services/errormodalService',
  'services/spinnerService',
  'directives/commentDirective'
], function(frontend) {

    frontend.controller('ProfileController', [
      'profileService', 'localStorageService', '$location', 'spinnerService', '$q', 'errormodalService', '$rootScope',
      function(profileService, localStorageService, $location, spinnerService, $q, errormodalService, $rootScope) {
        var _this = this;
        this.client = localStorageService.get('client');
        var promises = [];
        spinnerService.showSpinner();

        var getDocumentsPromise = profileService.getDocuments().then(
          function(response) {
            _this.files = response.data.documentList;
            console.log(response.data.documentList);
          }).catch(
            function (error) {
              $rootScope.errors.push(error.data);
            });

        promises.push(getDocumentsPromise);

        var getReviewsPromise = profileService.getReviews().then(
          function(response) {
              _this.reviews = response.data.reviewList;
              console.log(response.data.reviewList)
          }).catch(
            function (error) {
              $rootScope.errors.push(error.data);
            });

        promises.push(getReviewsPromise);

        $q.all(promises).then(function() {
          spinnerService.hideSpinner();
          errormodalService.showErrorModal();
        });
    }]);

});
