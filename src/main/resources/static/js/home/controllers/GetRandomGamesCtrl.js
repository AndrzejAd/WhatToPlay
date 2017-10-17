
angular.module('home').controller('GetRandomGamesCtrl', function($scope, $http, $location, GetRandomGames, GetImageForGame, getGameWithId ){
    
    GetRandomGames.getRandomGames($http).then( function(randomGames) { 
        angular.forEach( randomGames, function(game) {
            var completeUrl = "/getGamePhoto/" + game.imagePath;
            game.gameImage = completeUrl;
        });
        $scope.games = randomGames;
    });   
    
    $scope.getRandomGames = function(){
        GetRandomGames.getRandomGames($http).then( function(randomGames) { 
            angular.forEach( randomGames, function(game) {
                var promise =  GetImageForGame.getImageForGame(game.imagePath, $http, $location );
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
                var completeUrl = "/game/" + $scope.searchCriteria;
                window.location.replace(completeUrl);
                break;
                
            case "genre":
                var completeUrl = "/gamesByGenre/" + $scope.searchCriteria;
                window.location.replace(completeUrl);
                break;

            default:
                
        }
    }; 
    
    $scope.goToTheGameDetails = function(gameId){
        getGameWithId.getGameById($http).then( function() { 
            var absUrl = $location.absUrl().replace(/home.*$/, '');
            var completeUrl = absUrl + "game/" + gameId;
            window.location.replace(completeUrl);
        });    
    }; 
    
});