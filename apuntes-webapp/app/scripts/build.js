/* global paths */
'use strict';
require.config({
    baseUrl: 'scripts',
    paths: {
        affix: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/affix',
        alert: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/alert',
        angular: '../../bower_components/angular/angular',
        'angular-route': '../../bower_components/angular-route/angular-route',
        'angular-translate': '../../bower_components/angular-translate/angular-translate',
        'angular-ui-router': '../../bower_components/angular-ui-router/release/angular-ui-router',
        LocalStorageModule: '../../bower_components/angular-local-storage/dist/angular-local-storage',
        button: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/button',
        bootstrap: '../../bower_components/bootstrap/dist/js/bootstrap',
        carousel: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/carousel',
        collapse: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/collapse',
        dropdown: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/dropdown',
        'es5-shim': '../../bower_components/es5-shim/es5-shim',
        jquery: '../../bower_components/jquery/dist/jquery',
        json3: '../../bower_components/json3/lib/json3',
        modal: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/modal',
        moment: '../../bower_components/moment/moment',
        popover: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/popover',
        requirejs: '../../bower_components/requirejs/require',
        scrollspy: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/scrollspy',
        tab: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tab',
        tooltip: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tooltip',
        transition: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/transition',
        ngDialog: '../../bower_components/ng-dialog/js/ngDialog',
        'ng-dialog': '../../bower_components/ng-dialog/js/ngDialog',
        'angular-md5': '../../bower_components/angular-md5/angular-md5',
        md5: '../../bower_components/angular-md5/angular-md5',
        'angular-local-storage': '../../bower_components/angular-local-storage/dist/angular-local-storage',
        angularFileUpload: '../../bower_components/angular-file-upload/dist/angular-file-upload',
        'angular-file-upload': '../../bower_components/angular-file-upload/dist/angular-file-upload.min',
        'ng.model': '../../bower_components/ng-model/dist/ng-model-1.0.1.min',
        'ng-model': '../../bower_components/ng-model/dist/ng-model-1.0.1.min',
        ngFileUpload: '../../bower_components/ng-file-upload/ng-file-upload',
        'ng-file-upload': '../../bower_components/ng-file-upload/ng-file-upload',
        'angular-ui-select': '../../bower_components/angular-ui-select/dist/select',
        chosen: '../../bower_components/chosen/chosen.jquery',
        localytics: '../../bower_components/angular-chosen-localytics/dist/angular-chosen',
        'angular-chosen-localytics': '../../bower_components/angular-chosen-localytics/dist/angular-chosen',
        rt: '../../bower_components/angular-select2/dist/angular-select2',
        'angular-select2': '../../bower_components/angular-select2/dist/angular-select2',
        'angular-mandrill': '../../bower_components/angular-mandrill/dist/angular-mandrill'
    },
    shim: {
        angular: {
            deps: [
                'jquery'
            ],
            exports: 'angular'
        },
        'angular-ui-router': {
            deps: [
                'angular'
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
        },
        ngDialog: {
            deps: [
                'angular'
            ]
        },
        'angular-md5': {
            deps: [
                'angular'
            ]
        },
        LocalStorageModule: {
            deps: [
                'angular'
            ]
        },
        angularFileUpload: {
            deps: [
                'angular'
            ]
        },
        ngFileUpload: {
            deps: [
                'angular'
            ]
        },
        'ng.model': {
            deps: [
                'angular'
            ]
        },
        rt: {
            deps: [
                'angular'
            ]
        },
        chosen: {
            deps: [
                'jquery'
            ]
        },
        localytics: {
            deps: [
                'chosen',
                'angular',
                'jquery'
            ]
        },
        'angular-mandrill': {
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
        'controllers/IndexCtrl',
        'mailConfig'
    ],
    function() {
        angular.bootstrap(document, ['frontend']);
    }
);
