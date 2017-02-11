/* global paths */
'use strict';
require.config({
    baseUrl: '/scripts',
    paths: {
        affix: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/affix',
        alert: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/alert',
        angular: '../../webapp/src/main/frontend/bower_components/angular/angular',
        'angular-route': '../../webapp/src/main/frontend/bower_components/angular-route/angular-route',
        'angular-translate': '../../webapp/src/main/frontend/bower_components/angular-translate/angular-translate',
        button: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/button',
        bootstrap: '../../webapp/src/main/frontend/bower_components/bootstrap/dist/js/bootstrap',
        carousel: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/carousel',
        collapse: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/collapse',
        dropdown: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/dropdown',
        'es5-shim': '../../webapp/src/main/frontend/bower_components/es5-shim/es5-shim',
        jquery: '../../bower_components/jquery/dist/jquery',
        json3: '../../webapp/src/main/frontend/bower_components/json3/lib/json3',
        modal: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/modal',
        moment: '../../webapp/src/main/frontend/bower_components/moment/moment',
        popover: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/popover',
        requirejs: '../../webapp/src/main/frontend/bower_components/requirejs/require',
        scrollspy: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/scrollspy',
        tab: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tab',
        tooltip: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tooltip',
        transition: '../../webapp/src/main/frontend/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/transition',
        'jquery-ui': '../../webapp/src/main/frontend/bower_components/jquery-ui/jquery-ui'
    },
    shim: {
        angular: {
            deps: [
                'jquery'
            ]
        },
        'angular-route': {
            deps: [
                'angular'
            ]
        },
        bootstrap: {
            deps: [
                'jquery',
                'modal'
            ]
        },
        modal: {
            deps: [
                'jquery'
            ]
        },
        tooltip: {
            deps: [
                'jquery'
            ]
        },
        'angular-translate': {
            deps: [
                'angular'
            ]
        }
    },
    packages: [

    ]
});

if (paths) {
    require.config({
        paths: paths
    });
}

require([
        'angular',
        'frontend',
        'controllers/IndexCtrl'
    ],
    function() {
        angular.bootstrap(document, ['frontend']);
    }
);
