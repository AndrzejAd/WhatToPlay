'use strict'
/**
 * Created by Andrzej on 2017-12-04.
 */

angular.module('deleteGame').service( 'DeleteGameThroughHttpService', function($http) {

    this.deleteGame = function( gameName ) {
        var url = "/deleteGame/" + gameName;
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        $http.delete( url, config)
            .then(
                function(response){
                    console.log("Successfully deleted game.");
                },
                function(response){
                    console.log("Couldnt delete game.");
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