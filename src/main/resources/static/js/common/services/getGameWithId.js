
angular.module('common').service( 'getGameWithId', function($location) {
    
    this.getGameById = function( $http ) {
        var game;
        var absUrl = $location.absUrl().replace('game', 'getGame');
        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
          }).then(function successCallback(response) {
              game = response.data;
              return game;
            }, function errorCallback(response) {
                return response;
            });
    };
    
});