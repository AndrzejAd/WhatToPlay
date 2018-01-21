'use strict'
/**
 * Created by Andrzej on 2017-12-04.
 */

angular.module('deleteGame').controller('DeleteGameCtrl', function($scope, $location, $window, DeleteGameThroughHttpService){
    var allGames;

    $scope.searchForGame = function() {
        if ( $scope.searchQuery.length > 0 ) {
            DeleteGameThroughHttpService.searchForGame($scope.searchQuery).then( function(games) {
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
        $scope.searchQuery = name;
        $scope.showDropdown = false;
    }

    $scope.deleteGame = function() {
        DeleteGameThroughHttpService.deleteGame($scope.searchQuery);
    };

});