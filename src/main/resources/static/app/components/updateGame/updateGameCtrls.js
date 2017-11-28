'use strict'
/**
 * Created by Andrzej on 2017-11-28.
 */

angular.module('updateGame').controller('searchForGameCtrl', function($scope, $location, $document, $window, UpdateGameThroughHttpService){
    var allGames;

    $scope.searchForGame = function() {
        if ( $scope.searchQuery.length > 0 ) {
            UpdateGameThroughHttpService.searchForGame($scope.searchQuery).then( function(games) {
                allGames = games;
                var gameNames = [];
                angular.forEach(games, function(game){
                    gameNames.push(game.gameName);
                });
                $scope.gameNames = gameNames;
            });
            $scope.showDropdown = true;
        } else{
            $scope.showDropdown = false;
        }
    };

    $scope.updateInputWithGameData = function (name) {
        angular.forEach(allGames, function(game){
            if ( name === game.gameName ){
                $scope.gameId  = game.gameId;
                $scope.gameName = game.gameName;
                $scope.genre = game.genre;
                $scope.producer = game.producer;
                $scope.publisher = game.publisher;
                $scope.datePublished = game.datePublished;
                $scope.price = game.price;
                $scope.imagePath = game.imagePath;
                return;
            }
        });
    }

});

angular.module('updateGame').controller('updateGame', function($scope, $location, $window, UpdateGameThroughHttpService){

    $scope.updateGame = function() {
        var game = {
            gameId: $scope.gameId,
            gameName: $scope.gameName,
            genre: $scope.genre,
            producer: $scope.producer,
            publisher: $scope.publisher,
            datePublished: $scope.datePublished,
            price: $scope.price,
            imagePath: $scope.imagePath
        };
        UpdateGameThroughHttpService.sendGame(game);
    };

});