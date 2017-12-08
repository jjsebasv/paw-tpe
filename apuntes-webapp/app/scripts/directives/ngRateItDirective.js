'use strict';
define(['frontend'], function(frontend) {

  frontend.directive('rateit', function() {
    return {
      restrict: 'E',
      require: 'ngModel',
      replace: true,
      scope: {
        ngModel: '=',
        min: '=?min',
        max: '=?max',
        step: '=?step',
        readOnly: '&?readOnly',
        pristine: '=?pristine',
        resetable: '&?resetable',
        starWidth: '=?starWidth',
        starHeight: '=?starHeight',
        canelWidth: '=?canelWidth',
        cancelHeight: '=?cancelHeight',
        rated: '&?rated',
        reset: '&?reset',
        beforeRated: '&?beforeRated',
        beforeReset: '&?beforeReset'
      },
      templateUrl: 'views/_ngRateItDirective.html',
      link: function($scope, $element, $attrs) {
        debugger;
        if (!$attrs.readOnly) {
          $scope.readOnly = function() {
            return false;
          };
        }

        if (!$attrs.resetable) {
          $scope.resetable = function() {
            return true;
          };
        }

        if (!$attrs.beforeRated) {
          $scope.beforeRated = function() {
            var d = $q.defer();
            d.resolve();
            return d.promise;
          };
        }

        if (!$attrs.rated) {
          $scope.rated = function() {
            return;
          };
        }

        if (!$attrs.beforeReset) {
          $scope.beforeReset = function() {
            var d = $q.defer();
            d.resolve();
            return d.promise;
          };
        }

        if (!$attrs.reset) {
          $scope.reset = function() {
            return;
          };
        }

      },
      controller: function($scope, $timeout) {

        $scope.isTouch = !!window.hasOwnProperty("ontouchstart") || window.navigator.msMaxTouchPoints > 0;
        $scope.orgValue = angular.copy($scope.ngModel);

        $scope.min = $scope.min || 0;
        $scope.max = $scope.max || 5;
        $scope.step = $scope.step || 0.5;

        $scope.pristine = $scope.orgValue === $scope.ngModel;

        $scope.starWidth = $scope.starWidth || 16;
        $scope.starPartWidth = $scope.starWidth * $scope.step;
        $scope.starHeight = $scope.starHeight || 16;
        $scope.canelWidth = $scope.canelWidth || $scope.starWidth;
        $scope.cancelHeight = $scope.cancelHeight || $scope.starHeight;

        var diff = $scope.max - $scope.min,
          steps = diff / $scope.step,
          garbage = $scope.$watch('ngModel', function() {
            $scope.pristine = $scope.orgValue === $scope.ngModel;
          }),

          getValue = function(index) {
            return (index + 1) / steps * diff;
          };

        $scope.getStartParts = function() {
          return new Array(steps);
        };

        $scope.getStarOffset = function(index) {
          var ratio = 1 / $scope.step,
            offset = -($scope.starWidth / ratio) * (index % ratio);
          return offset;
        };

        $scope.isSelected = function(index) {
          return getValue(index) <= $scope.ngModel - $scope.min;
        };

        $scope.removeRating = function() {
          if ($scope.resetable() && !$scope.readOnly()) {
            $scope.beforeReset({
              rating: $scope.ngModel
            }).then(function() {
              $scope.ngModel = $scope.min;
              $scope.reset({
                rating: $scope.ngModel
              });
            });
          }
        };

        $scope.setValue = function(index) {
          if (!$scope.readOnly()) {
            var tmpValue = angular.copy($scope.min + getValue(index));

            $scope.beforeRated({
              rating: tmpValue
            }).then(function() {
              $scope.ngModel = tmpValue;
              $timeout(function() {
                $scope.rated({
                  rating: $scope.ngModel
                });
              });
            });
          }
        };

        $scope.$on('$destroy', function() {
          garbage();
        });

      }
    };
  });

});
