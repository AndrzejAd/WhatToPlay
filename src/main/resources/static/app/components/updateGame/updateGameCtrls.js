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
                if ( games.length > 10 ){
                    for ( var i = 0; i < 10; i++ ) {
                        gameNames.push(games[i].name);
                    }
                } else{
                    for ( var i = 0; i < games.length; i++ ) {
                        gameNames.push(games[i].name);
                    }
                }
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
                $scope.gameId  = game.id;
                $scope.gameName = game.name;
                $scope.genre = game.slug;
                return;
            }
        });
        $scope.showDropdown = false;
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