var BASE_URL = "http://localhost:8080/Uniprot/";

if (typeof String.prototype.startsWith != 'function') {
  // see below for better implementation!
  String.prototype.startsWith = function (str){
    return this.indexOf(str) === 0;
  };
}

(function(angular) {
  'use strict';

  angular.module('abcam', ['restangular', 'ngRoute'])
  .config(['$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider
				.when('/', {
					templateUrl: 'views/select.html',
					controller: 'selectController',
					controllerAs: 'home'
				})
		$locationProvider.html5Mode(true);
  }])
  .config( 
	function(RestangularProvider) {
		RestangularProvider.setBaseUrl( BASE_URL );
		RestangularProvider.setRequestSuffix('/');
   })
  .config([
            '$httpProvider', 'fileUploadProvider',
            function ($httpProvider, fileUploadProvider) {
                delete $httpProvider.defaults.headers.common['X-Requested-With'];
                fileUploadProvider.defaults.redirect = window.location.href.replace(
                    /\/[^\/]*$/,
                    '/cors/result.html?%s'
                );
                angular.extend(fileUploadProvider.defaults, {
                	disableImageResize: /Android(?!.*Chrome)|Opera/
                    	.test(window.navigator.userAgent),
                	maxFileSize: 5000000,
                	acceptFileTypes: /(\.|\/)(gif|jpe?g|png|pdf|PDF)$/i,
                	sequentialUploads : true
                });
            }])
  .run(function($rootScope, Restangular) {
	Restangular.addRequestInterceptor(function(element) {
		$("#wait-loading").css("visibility", "visible");

	});
	Restangular.addResponseInterceptor(function(data) {
		$("#wait-loading").css("visibility", "hidden");
		return data;
	});
  })
  .controller('MainCtrl', ['$route', '$routeParams', '$location',
	  function($route, $routeParams, $location) {
	    this.$route = $route;
	    this.$location = $location;
	    this.$routeParams = $routeParams;
	}])