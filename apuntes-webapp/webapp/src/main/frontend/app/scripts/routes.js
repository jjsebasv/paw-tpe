'use strict';

define([], function() {
    return {
        defaultRoutePath: '/',
        // Necesitamos cambiar el / y anteponer el nombre del grupo para que funcione cuando deploy
        routes: {
            '/': {
                templateUrl: '/views/home.html',
                controller: 'HomeController'
            },
            '/profile': {
                templateUrl: '/views/profile.html',
                controller: 'ProfileController'
            },
            '/course/:courseId': {
                templateUrl: '/views/course.html',
                controller: 'CourseController'
            },
            '/program': {
                templateUrl: '/views/program.html',
                controller: 'ProgramController'
            },
            '/university/:universityId': {
                templateUrl: '/views/university.html',
                controller: 'UniversityController'
            }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };
});
