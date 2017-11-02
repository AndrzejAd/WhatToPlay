
angular.module('gamesByGenre').controller('gamesByGenre', function($scope, $http, $window, GetGamesService ){

    var currentGameGenre = $window.sessionStorage.getItem("currentGameGenre");

    GetGamesService.getGamesByGenre( currentGameGenre, $http).then( function( gamesByGenre ) {
        $scope.gamesListByGenre = gamesByGenre;
    });    
    
});