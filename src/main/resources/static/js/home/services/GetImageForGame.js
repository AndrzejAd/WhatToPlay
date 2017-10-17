
angular.module('home').service('GetImageForGame', function() {

    this.getImageForGame = function( imagePath, $http, $location ){
        var completeUrl = "/getGamePhoto/" + game.imagePath;
        return $http( {
            method: 'GET',
            url: completeUrl,
            responseType: "blob"
        });
    };
    
});