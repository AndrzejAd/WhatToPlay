'use strict';

angular.module('addGame').service( 'addGameThroughHttpService', function($http) {
    
    this.sendGame = function( gameToJson, absUrl ) {
        var game = JSON.stringify(gameToJson);  
        var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        };
        $http.post(absUrl, game, config)
            .then(
                function(response){
                    console.log("Done");
                },
                function(response){
                    console.log("Couldnt post game!");
        });
    };
    
});