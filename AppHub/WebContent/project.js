angular.module('AppHub', ['ngRoute', 'Apphub.controllers', 'Apphub.services', 'Apphub.directives'])
.config(function($routeProvider) {
  $routeProvider
    .when('/', {
      controller:'AppCntrl',
      templateUrl:'html/AppHub.html'
    })
    .when('/login', {
      controller:'LoginCntrl',
      templateUrl:'html/LoginPage.html'
    })
    .when('/results', {
      controller:'AppSearchCntrl',
      templateUrl:'html/ResultsPage.html'
    })
    .when('/admin', {
      controller:'AppSearchCntrl',
      templateUrl:'html/AdminPage.html'
    })
    .when('/results/app/:id', {
      controller:'AppViewCntrl',
      templateUrl:'html/AppView.html'
    })
    .when('/addApp', {
      controller:'AppCntrl',
      templateUrl:'html/AddAppPage.html'
    })
    .otherwise({
      redirectTo:'/'
    });
});