
'use strict'

angular.module('shared').service( 'GetGamesService', function() {

    this.getGameById = function( gameId, $http ) {
        var game;
        var absUrl = '/getGame/' + gameId;
        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
          }).then(function successCallback(response) {
              game = response.data;
              return game;
            }, function errorCallback(response) {
                return response;
            });
    };

    this.getGamesByGenre = function( gameGenre, $http ) {
        var gamesByGenre;
        var absUrl = "/getGameByGenre/" + gameGenre;

        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
        }).then(function successCallback(response) {
            gamesByGenre = response.data;
            return gamesByGenre;
        }, function errorCallback(response) {
            window.alert("Fail!");
        });
    };

    this.getRandomGames = function( $http){
        return $http( {
            method: 'GET',
            url: '/getGames',
            accept: "application/json"
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });
    };

    this.getImageForGame = function( imagePath, $http, $location ){
        var completeUrl = "/getGamePhoto/" + imagePath;
        return $http( {
            method: 'GET',
            url: completeUrl,
            responseType: "blob"
        });
    };


});