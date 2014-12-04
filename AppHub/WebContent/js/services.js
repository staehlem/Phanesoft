'use strict';

/* Services */


// Demonstrate how to register services

var AppHubServices = angular.module('Apphub.services', ['ngResource']);
AppHubServices.factory('SearchCriteria',function() {
	return {};
});
AppHubServices.factory('LogedInUser', ['$http', 'LoginLogoutService','$location',
                                       function($http, LoginLogoutService, $location) {
	return {
		user: {},
		isAuthenticated: false,
		logout: function() {
			this.user = {};
			this.isAuthenticated = false;
			$http.defaults.headers.common.Authorization = undefined;
			LoginLogoutService.logout.get({}, function() {}, function() {
				$location.path('/login');
			});
		}
	};
}]);
AppHubServices.factory('LoginLogoutService', ['$resource', function($resource) {
	return {
		login: $resource('http://localhost:8080/com.AppHub.jersy/rest/EmptyLoginLogoutService/login', null, {
        	get: {method:'GET'}
        }),
        logout: $resource('http://localhost:8080/com.AppHub.jersy/rest/EmptyLoginLogoutService/logout', null, {
        	get: {method:'GET'}
        })
	};
}]);
AppHubServices.factory('User', ['$resource', function($resource){
    return {
        createUser: $resource('http://localhost:8080/com.AppHub.jersy/rest/UserService/create', null, {
        }),
        deleteUser: $resource('http://localhost:8080/com.AppHub.jersy/rest/UserService/remove/:userId', {}, {
        	remove: {method:'DELETE',params:{userId:'@userId'}},
        }),
        updateUser: $resource('http://localhost:8080/com.AppHub.jersy/rest/UserService/update',{},{
            update: {method:'PUT'}
        }),
        getUser: $resource('http://localhost:8080/com.AppHub.jersy/rest/UserService/:userId',{},{
            get: {method:'GET', params:{userId:'@userId'}, isArray:false}
        }),
        getAllUsers: $resource('http://localhost:8080/com.AppHub.jersy/rest/UserService/',{},{
            get: {method:'GET', isArray:true}
        })
    };
}]);
AppHubServices.factory('App', ['$resource', function($resource){
	return {
        createApp: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppService/create', null, {
        	post: {method:'POST'}
        }),
        deleteApp: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppService/remove/:appId', {}, {
        	remove: {method:'DELETE',params:{userId:'@appId'}},
        }),
        updateApp: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppService/update',{},{
            update: {method:'PUT'}
        }),
        getApp: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppService/:appId',{},{
            get: {method:'GET', params:{appId:'@appId'}, isArray:false}
        }),
        getAllApps: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppService/',{},{
            get: {method:'GET', isArray:true}
        })
	};
}]);
AppHubServices.factory('AppComment', ['$resource', function($resource) {
	return {
        createAppComment: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppCommentsService/create', null, {
        	post: {method:'POST'}
        }),
        deleteAppComment: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppCommentsService/remove/:commentId', {}, {
        	remove: {method:'DELETE',params:{commentId:'@commentId'}},
        }),
        updateAppComment: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppCommentsService/update',{},{
            update: {method:'PUT'}
        }),
        getAllAppComments: $resource('http://localhost:8080/com.AppHub.jersy/rest/AppCommentsService/',{},{
            get: {method:'GET', isArray:true}
        })
	};
}]);
AppHubServices.factory('Platform', ['$resource', function($resource){
	return {
        createPlatform: $resource('http://localhost:8080/com.AppHub.jersy/rest/PlatformService/create', null, {
        	post: {method:'POST'}
        }),
        deletePlatform: $resource('http://localhost:8080/com.AppHub.jersy/rest/PlatformService/remove/:platformId', {}, {
        	remove: {method:'DELETE',params:{platformId:'@platformId'}},
        }),
        updatePlatform: $resource('http://localhost:8080/com.AppHub.jersy/rest/PlatformService/update',{},{
            update: {method:'PUT'}
        }),
        getPlatform: $resource('http://localhost:8080/com.AppHub.jersy/rest/PlatformService/:platformId',{},{
            get: {method:'GET', params:{platformId:'@platformId'}, isArray:false}
        }),
        getAllPlatforms: $resource('http://localhost:8080/com.AppHub.jersy/rest/PlatformService/',{},{
            get: {method:'GET', isArray:true}
        })
	};
}]);

AppHubServices.service("fileReader", [ "$q", "$log", function($q, $log) {
	var onLoad = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.resolve(reader.result);
			});
		};
	};

	var onError = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.reject(reader.result);
			});
		};
	};

	var onProgress = function(reader, scope) {
		return function(event) {
			scope.$broadcast("fileProgress", {
				total : event.total,
				loaded : event.loaded
			});
		};
	};

	var getReader = function(deferred, scope) {
		var reader = new FileReader();
		reader.onload = onLoad(reader, deferred, scope);
		reader.onerror = onError(reader, deferred, scope);
		reader.onprogress = onProgress(reader, scope);
		return reader;
	};

	var readAsDataURL = function(file, scope) {
		var deferred = $q.defer();

		var reader = getReader(deferred, scope);
		reader.readAsDataURL(file);

		return deferred.promise;
	};

	return {
		readAsDataUrl : readAsDataURL
	};
} ]);

AppHubServices.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, uploadUrl) {
		var fd = new FormData();
		fd.append('file', file);
		return $http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			},
			responseType : "json"
		}).success(function(data, status, headers, config, statusText) {
			console.log('Success!');
			return data.message;
		}).error(function() {
		});
	};
} ]);

