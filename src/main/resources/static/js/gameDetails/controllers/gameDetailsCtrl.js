
angular.module('gameDetails').controller('gameDetails', function($location, $scope, $http, getGameWithId ){
    
    getGameWithId.getGameById($http).then( function( response ) {  
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