'use strict';

/* Controllers */

var AppHubControllers = angular.module('Apphub.controllers', []);

AppHubControllers.controller('AppCntrl', ['$scope', 'App', '$location', 'SearchCriteria', 'fileUpload', 'fileReader', 'LogedInUser',
                                          function($scope, App, $location, SearchCriteria, fileUpload, fileReader, LogedInUser) {
	$scope.searchCriteria = SearchCriteria;
	$scope.LogedInUser = LogedInUser;
	$scope.searchCriteria.value = null;
	$scope.newApp = {};
	$scope.url = null;
	$scope.getAllApps = function() {
		$location.path( '/results' );
	};
	
	$scope.uploadFile = function(fileReader){
		
        var file = $scope.myFile;
        console.log('file is ' + JSON.stringify(file));
        var uploadUrl = "http://localhost:8080/com.AppHub.jersy/rest/UploadFileService";
        fileUpload.uploadFileToUrl(file, uploadUrl).success(function(data, status, headers, config, statusText) {
        	createApp(data.message);
        });
    };
    
    var createApp = function(url) {
    	$scope.newApp.appApproved = false;
		$scope.newApp.appComments = null;
		$scope.newApp.appLocalImage = url;
		App.createApp.post($scope.newApp, function(app) {
			console.log(JSON.stringify(app));
			$location.path( '/');
		});
    };
    
    $scope.getFile = function () {
        fileReader.readAsDataUrl($scope.myFile, $scope)
                      .then(function(result) {
                          $scope.imageSrc = result;
                      });
    };
}]);
AppHubControllers.controller('LoginCntrl', ['$scope', 'LoginLogoutService', '$location', 'User', '$http', 'LogedInUser',
                                            function($scope, LoginLogoutService, $location, User, $http, LogedInUser) {
	$scope.user = {};
	$scope.newUser = {};
	$scope.LogedInUser = LogedInUser;
	$scope.failedLogin = false;
	$scope.login = function() {
		var auth = btoa($scope.user.userName + ":" + $scope.user.password);
		$http.defaults.headers.common.Authorization = "Basic "+auth;
		LoginLogoutService.login.get({}, function() {
			$location.path('/');
		
			$scope.failedLogin = false;
			User.getUser.get({userId:$scope.user.userName},function(user) {
				LogedInUser.user = user;
			});
			LogedInUser.isAuthenticated = true;
		}, function() {
			$http.defaults.headers.common.Authorization = undefined;
			LogedInUser.user = {};
			LogedInUser.isAuthenticated = false;
			$scope.failedLogin = true;
		});
	};
	
	$scope.createNewUser = function() {
		$scope.newUser.first = $scope.newUser.userName;
		$scope.newUser.last = $scope.newUser.userName;
		$scope.newUser.email = $scope.newUser.userName;
		$scope.newUser.userType = "General User";
		console.log(JSON.stringify($scope.newUser));
		User.createUser.save($scope.newUser,function() {
			$scope.user = $scope.newUser;
			$scope.newUser = {};
			$scope.login();
		});
	};
	
}]);
AppHubControllers.controller('AppSearchCntrl', ['$scope', 'App', 'SearchCriteria', '$location', 'LogedInUser', 
                                          function($scope, App, SearchCriteria, $location, LogedInUser) {
	$scope.searchCriteria = SearchCriteria;
	$scope.LogedInUser = LogedInUser;
	$scope.apps = [];
	App.getAllApps.get({}, function(Apps) {
		console.log(JSON.stringify(Apps));
		$scope.apps = Apps;
	});
	
	$scope.viewApp = function(id) {
		$location.path('/results/app/' + id);
	};
    
}]);
AppHubControllers.controller('AppViewCntrl', ['$scope', 'App', '$location', '$routeParams', '$window',
                                              function($scope, App, $location, $routeParams, $window) {
	App.getApp.get({appId:$routeParams.id}, function(app) {
		$scope.app = app;
		console.log(JSON.stringify(app));
	});
	
	$scope.changeWindow = function() {
		$window.location.href = 'http://' + $scope.app.appUrl+'';
	};
}]);