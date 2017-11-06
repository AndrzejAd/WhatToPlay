'use strict'

angular.module('shared', ['ngRoute']);

var App = angular.module('shared',[]);

App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);
