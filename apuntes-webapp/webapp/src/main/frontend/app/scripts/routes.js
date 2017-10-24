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
            '/profile/:userId': {
                templateUrl: '/views/profile.html',
                controller: 'ProfileController'
            },
            '/course/:courseId': {
                templateUrl: '/views/course.html',
                controller: 'CourseController'
            },
            '/program/:programId': {
                templateUrl: '/views/program.html',
                controller: 'ProgramController'
            },
            '/university/:universityId': {
                templateUrl: '/views/university.html',
                controller: 'UniversityController'
            },
            '/document/:documentId': {
                templateUrl: '/views/document.html',
                controller: 'DocumentController'
            },
            '/login': {
                templateUrl: '/views/login.html',
                controller: 'LoginController'
            },
            '/register': {
                templateUrl: '/views/register.html',
                controller: 'RegisterController'
            },
            '/upload': {
                templateUrl: '/views/upload.html',
                controller: 'UploadController'
            },
            '/upload/:courseId': {
                templateUrl: '/views/upload.html',
                controller: 'UploadController'
            },
            '/mail': {
              templateUrl: '/views/mail.html',
              controller: 'MailController'
            }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };
});
