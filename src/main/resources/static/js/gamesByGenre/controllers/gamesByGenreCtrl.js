
angular.module('gamesByGenre').controller('gamesByGenre', function($scope, $http, getGamesByGenre ){
    
    getGamesByGenre.getGamesByGenre( $http).then( function( gamesByGenre ) {    
        $scope.gamesListByGenre = gamesByGenre;
    });    
    
});