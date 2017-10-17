'use strict';

angular.module('addGame').controller('submitGame', function($scope, $location, $window, addGameThroughHttpService){
    
    $scope.submit = function() {
        var absUrl = $location.absUrl().replace('.html', '');
        var gameToJson = {
            gameName: $scope.gameName,
            genre: $scope.genre,
            producer: $scope.producer,
            publisher: $scope.publisher,
            datePublished: $scope.datePublished,
            price: $scope.price,
            imagePath: "2"
        };
        addGameThroughHttpService.sendGame(gameToJson, absUrl);
        var completeUrl = "";
        window.location.replace(completeUrl);
    };
    
});