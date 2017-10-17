
'use strict';

angular.module('login').controller('LoginCtrl', function($scope, $window, $location, AuthUser ){
    
    $scope.login = function(){
        AuthUser.authenticateUser( $scope.username, $scope.password, $location ).then( function(code){
            if ( code === 200 ){
                console.log("AAA");
            } else{
                window.alert("Wrong credentials, please try again!");
            }
        });
        
    }; 
    
});