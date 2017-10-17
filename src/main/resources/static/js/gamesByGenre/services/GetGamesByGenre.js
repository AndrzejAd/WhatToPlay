
angular.module('gamesByGenre').service( 'getGamesByGenre', function($location) {
    
    this.getGamesByGenre = function( $http ) {
        var gamesByGenre;
        var absUrl = $location.absUrl().replace('gamesByGenre', 'getGameByGenre');
                
        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
          }).then(function successCallback(response) {
              gamesByGenre = response.data;
              return gamesByGenre;
            }, function errorCallback(response) {
              window.alert("Fail!");
            });
    };
    
});