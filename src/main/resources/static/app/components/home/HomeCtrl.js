
'use strict'

angular.module('home').controller('HomeCtrl', function($scope, $http, $location, $window, GetGamesService, LogoutService ){

    GetGamesService.getRandomGames($http).then( function(randomGames) {
        angular.forEach( randomGames, function(game) {
            var completeUrl = "/getGamePhoto/" + game.imagePath;
            game.gameImage = completeUrl;
        });
        $scope.games = randomGames;
    });   
    
    $scope.getRandomGames = function(){
        GetGamesService.getRandomGames($http).then( function(randomGames) {
            angular.forEach( randomGames, function(game) {
                var promise =  GetGamesService.getImageForGame(game.imagePath, $http, $location );
                promise.then(
                    function(image){
                        var fileReader  = new FileReader();
                        fileReader.onloadend = function( event ){
                            game.gameImage = event.target.result;
                        };
                        fileReader.readAsDataURL(image.data);
                    },
                    function(){
                        console.log("Error");
                    }
                );
            });       
            $scope.games = randomGames;
        });    
    };
    
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
    
    $scope.goToTheGameDetails = function(gameId){
        $window.sessionStorage.setItem( "currentGame", JSON.stringify(gameId));

        GetGamesService.getGameById( gameId, $http).then( function() {
            var completeUrl = "/app/components/gameDetails/gameDetails.html";
            window.location.replace(completeUrl);
        });
    }; 

    $scope.logout = function(){
        LogoutService.logOut($http).then( function( code ){
            if ( code === 200 ){
                var completeUrl = "/app/components/home/home.html";
                window.location.replace(completeUrl);
            } else{
                console.log("Couldnt log out.");
            }
        });
    }

});