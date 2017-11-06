'use strict';

angular.module('addGame').controller('submitGame', function($scope, $location, $window, PostGameThroughHttpService){
    
    $scope.submit = function() {
        var game = {
            gameName: $scope.gameName,
            genre: $scope.genre,
            producer: $scope.producer,
            publisher: $scope.publisher,
            datePublished: $scope.datePublished,
            price: $scope.price,
            imagePath: "2"
        };
        PostGameThroughHttpService.sendGame(game);

    };
    
});