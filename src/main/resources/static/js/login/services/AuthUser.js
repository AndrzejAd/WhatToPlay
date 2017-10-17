'use strict';


angular.module('login').service('AuthUser', function( $http, $location ){
    
    this.authenticateUser = function( username, password, $location ){ 
        var absUrl = "/login/";
        var login = 'username=' + username + '&password=' + password;
        
        var config = {
                headers : {
                    'Content-type': 'application/x-www-form-urlencoded'
                }
        };
        
        return $http.post(absUrl, login, config)
            .then(
                function(response){
                    console.log("OK");
                    return 200;
                },
                function(response){
                    console.log("ERR");
                    return 401;
        });
        
    };
    
});