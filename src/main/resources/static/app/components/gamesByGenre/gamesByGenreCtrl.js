'use strict';

angular.module('gamesByGenre').controller('gamesByGenre', function($location, $scope, $http, $window, GetGamesService,
                                                                   CheckIfAuthenticatedService ){

    var currentGameGenre = $window.sessionStorage.getItem("currentGameGenre");

    GetGamesService.getGamesByGenre( currentGameGenre, $http).then( function( gamesByGenre ) {
        $scope.gamesListByGenre = gamesByGenre;
    });

    $scope.searchForGame = function(){
        console.log("ASAAS")
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
    };


});