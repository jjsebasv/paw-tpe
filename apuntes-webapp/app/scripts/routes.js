'use strict';

define([], function() {
    return {
        defaultRoutePath: '/',
        // Necesitamos cambiar el / y anteponer el nombre del grupo para que funcione cuando deploy
        routes: {
            '/': {
                templateUrl: 'views/home.html',
                controller: 'HomeController'
            },
            '/profile': {
                templateUrl: 'views/profile.html',
                controller: 'ProfileController'
            },
            '/users/edit': {
                templateUrl: 'views/users.html',
                controller: 'UsersController'
            },
            '/course/:courseId': {
                templateUrl: 'views/course.html',
                controller: 'CourseController'
            },
            '/program/:programId': {
                templateUrl: 'views/program.html',
                controller: 'ProgramController'
            },
            '/university/:universityId': {
                templateUrl: 'views/university.html',
                controller: 'UniversityController'
            },
            '/document/:documentId': {
                templateUrl: 'views/document.html',
                controller: 'DocumentController'
            },
            '/login': {
                templateUrl: 'views/login.html',
                controller: 'LoginController'
            },
            '/register': {
                templateUrl: 'views/register.html',
                controller: 'RegisterController'
            },
            '/upload': {
                templateUrl: 'views/upload.html',
                controller: 'UploadController'
            },
            '/upload/:courseId': {
                templateUrl: 'views/upload.html',
                controller: 'UploadController'
            },
            '/mail': {
              templateUrl: 'views/mail.html',
              controller: 'MailController'
            },
            '/reset-password': {
              templateUrl: 'views/reset.html',
              controller: 'ResetPasswordController'
            },
            '/change-password': {
              templateUrl: 'views/change.html',
              controller: 'ChangePasswordController'
            },
            '/universities/add': {
                templateUrl: 'views/adminUniversity.html',
                controller: 'AdminController'
            },
            '/universities/edit/:universityId': {
                templateUrl: 'views/adminUniversity.html',
                controller: 'AdminController'
            },
            '/programs/add': {
                templateUrl: 'views/addProgram.html',
                controller: 'AddProgramController'
            }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };
});
