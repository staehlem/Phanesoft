'use strict';

/* Directives */

var AppHubDirectives = angular.module('Apphub.directives', []);

AppHubDirectives.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                    scope.getFile();
                });
            });
        }
    };
}]);
AppHubDirectives.directive('loginValidation', function(){

	  return{
	    require:'ngModel',
	    link: function(scope, elem, attrs, ctrl){
	      ctrl.$parsers.unshift(validate);
	      ctrl.$formatters.unshift(validate);
	      scope.$watch("failedLogin", function(value) {
	    	  validate();
	      });

	      function validate(viewValue){
	        if (scope.failedLogin == false) {
	          ctrl.$setValidity('valid',true);
	        }
	        else{
	          ctrl.$setValidity('invalid', false);
	        }
	        return viewValue;
	      }
	    }
	  };
	});