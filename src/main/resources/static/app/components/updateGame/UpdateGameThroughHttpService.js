'use strict'
/**
 * Created by Andrzej on 2017-11-28.
 */

angular.module('updateGame').service( 'UpdateGameThroughHttpService', function($http) {

    this.sendGame = function( game ) {
        var url = "/updateGame/";
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        $http.post( url, game, config)
            .then(
                function(response){
                    console.log("Successfully updated game.");
                },
                function(response){
                    console.log("Couldnt update game.");
                });
    }

    this.searchForGame = function( gameName ) {
        var url = "/getGamesByGameName/" + gameName;
        return $http.get(url)
            .then(
                function(response){

                    return response.data;
                },
                function(response){
                    console.log("Couldnt search for game.");
                });

    }


});