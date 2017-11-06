'use strict';

angular.module('addGame').service( 'PostGameThroughHttpService', function($http, CheckIfAuthenticatedService) {
    
    this.sendGame = function( game ) {
        if ( CheckIfAuthenticatedService.authenticateUser() ){
            var url = "/addGame/";
            var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };
            $http.post( url, game, config)
                .then(
                    function(response){
                        console.log("Successfully added game.");
                    },
                    function(response){
                        console.log("Couldnt add game.");
                });
        };
        console.log('shit')
    }



});