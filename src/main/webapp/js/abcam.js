var BASE_URL = "http://localhost:8080/Uniprot/rest/getProtein/";

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
  .controller('selectController', ['$scope', '$route', '$routeParams', '$location', 'Restangular',
	  function($scope, $route, $routeParams, $location, Restangular) {
	    $scope.call = function() {
	    	Restangular.one($scope.accessionNumber.toUpperCase()).get().then(function(response) {
	    		if (response != undefined) {
	    			$scope.uan = $scope.accessionNumber.toUpperCase();
	    			
	    			var an = "";
	    			for (var i = 0; i < response.value.entry[0].accession.length; i++) {
	    				an = an + response.value.entry[0].accession[i] + ",";
	    			}
	    			if (an != "") {
	    				$scope.an = an.substring(0, an.length - 1).toUpperCase();
	    			}
	    			$scope.reccomendedNames = response.value.entry[0].protein.recommendedName;
	    			$scope.organism = response.value.entry[0].organism.name;
	    			$scope.sequence = response.value.entry[0].sequence.value;
	    		} else {
	    			$scope.uan = null;
	    			$scope.sequence = null;
	    			$scope.reccomendedNames = null;
	    			$scope.organism = null;
	    			$scope.an = null;
	    		}
	    		$scope.error = "";
	    	}, function(response) {
	    		$scope.uan = null;
	    		$scope.sequence = null;
    			$scope.reccomendedNames = null;
    			$scope.organism = null;
    			$scope.an = null;
	    	    $scope.error = "Invalid Accession Number... error code: " + response.status;
	    	});
	    }
	}])
  .directive('buttonDirective', function() {
		  return {
		    replace: true,
		    template: '<button class="">Run</button>'
		  };
		});	
})(window.angular);