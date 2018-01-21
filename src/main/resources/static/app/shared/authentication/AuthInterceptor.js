'use strict'

angular.module('shared').factory('AuthInterceptor', [function() {
        return {
            // Send the Authorization header with each request
            'request': function(config) {
                if ( sessionStorage.getItem("username" ) && sessionStorage.getItem("password" )  ){
                    console.log("Interceptor using: "  + sessionStorage.getItem("username" ) + " " + sessionStorage.getItem("password"));
                    config.headers = config.headers || {};
                    var encodedString = btoa( sessionStorage.getItem("username") + ':' + sessionStorage.getItem("password"));
                    config.headers.Authorization = 'Basic '+ encodedString;
                    return config;
                } else{
                    return config;
                }
            }
        };
    }]);


