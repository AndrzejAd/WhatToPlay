'use strict';

angular.module('signup').controller('SignUpCtrl', function($scope, $window, $location, SendUserDataService ){
    
    $scope.signup = function(){
        
        var userToJson = {
            username: $scope.username,
            password: $scope.password,
            firstName: '',
            lastName: '',
            email: $scope.email
        };
        
        var msg = SendUserDataService.validateUserData( $scope.username, $scope.password );
        
        if ( msg === "correct"){
            SendUserDataService.sendUserData(userToJson, $location);
        } else{
            if ( msg === "username length error" ){
                $scope.wrongUsername = true;
                console.log($scope.wrongUsername);
            } else{
                $scope.wrongPassword = true;
            }
            
        }
    }; 
    
});