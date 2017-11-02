'use strict';

angular.module('signup').service('SendUserDataService', function( $http ){
    
    this.sendUserData = function( user ){
        var completeUrl = "/addUser/";
        
        var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        };
        
        return $http.post(completeUrl, user, config)
            .then(
                function(response){
                    return response;
                },
                function(response){
                    return response;
        });
    };
    
    this.validateUserData = function( username, password ){ 
        if ( ! ( username.length > 2 && username.length <= 32 ) ) return  "username length error";
        if ( ! ( password.length >= 6 && password.length <= 32 ) )   return "password length error";
        return "correct";
    };
    
});