'use strict';


angular.module('login').service('AuthUser', function( $http ){
    
    this.authenticateUser = function( username, password ){
        var absUrl = "/login";
        var login = 'username=' + username + '&password=' + password;
        
        var config = {
                headers : {
                    'Content-type': 'application/x-www-form-urlencoded'
                }
        };
        
        return $http.post(absUrl, login, config)
            .then(
                function(response){
                    sessionStorage.setItem("username", username );
                    sessionStorage.setItem("password", password );
                    return response;
                },
                function(response){
                    return response;
        });
        
    };
    
});