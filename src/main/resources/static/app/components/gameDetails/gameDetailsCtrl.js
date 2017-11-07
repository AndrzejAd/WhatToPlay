
'use strict'

angular.module('gameDetails').controller('gameDetails', function($location, $window, $scope, $http, GetGamesService,
                                                                 CheckIfAuthenticatedService){

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

    $scope.searchForGame = function(){
        switch( $scope.selectSearchCriteria ) {
            case "id":
                $window.sessionStorage.setItem( "currentGame", $scope.searchCriteria);
                var completeUrl = "/app/components/gameDetails/gameDetails.html";
                window.location.replace(completeUrl);
                break;

            case "genre":
                $window.sessionStorage.setItem( "currentGameGenre", $scope.searchCriteria);
                var completeUrl = "/app/components/gamesByGenre/gamesByGenre.html";
                window.location.replace(completeUrl);
                break;

            default:

        }
    };

    $scope.isLogged = function(){
        return CheckIfAuthenticatedService.isLogged();
    }

});