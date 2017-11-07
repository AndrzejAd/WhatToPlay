
'use strict';

angular.module('login').controller('LoginCtrl', function($scope, $window, $location, AuthUser,
                                                         CheckIfAuthenticatedService ){
    
    $scope.login = function(){
        AuthUser.isLogged( $scope.username, $scope.password, $location ).then( function(response){
            if ( response.status === 200 ){
                console.log("Done!");
                var completeUrl = "/app/components/home/home.html";
                window.location.replace(completeUrl);
            } else{
                console.log("Wrong credentials, please try again!");
            }
        });
    };

    $scope.isLogged = function(){
        console.log('LOL');
        return CheckIfAuthenticatedService.isLogged();
    }

});