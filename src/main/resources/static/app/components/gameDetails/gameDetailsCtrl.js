
'use strict'

angular.module('gameDetails').controller('gameDetails', function($location, $window, $scope, $http, GetGamesService ){

    var currentGameId = $window.sessionStorage.getItem("currentGame");

    GetGamesService.getGameById( currentGameId, $http).then( function( response ) {

        if ( response.status != 404 ){
            var game = response;
            var completeUrl = "/getGamePhoto/" + game.imagePath;
            game.gameImageUrl = completeUrl;
            $scope.x = game;
        } else{
            console.log("No games found!");
        }
        
    });    
    
});