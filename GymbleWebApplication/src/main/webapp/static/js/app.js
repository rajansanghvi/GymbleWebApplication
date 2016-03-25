'use strict';

var App = angular.module('myApp',['ngRoute']);


//App.config(function($routeProvider){
//	$routeProvider.
//	when('/',{templateUrl:'WEB-INF/views/registeration.jsp',controller:'StudentController'}).
//	when('/masters/activity',{templateUrl:'/masters/activity',controller:'activityController'}).
//	otherwise({redirectTo:'/'})
//});
App.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/masters/activity', {
			templateUrl: '/masters/activity',
			controller : "activityController",
		})
}]);

