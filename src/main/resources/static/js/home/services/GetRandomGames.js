
angular.module('home').service('GetRandomGames', function() {
    var randomGames;
    
    this.getRandomGames = function($http){ 
        return $http( {
            method: 'GET',
            url: 'getGames',
            accept: "application/json"
          }).then(function successCallback(response) {
              randomGames = response.data;
              return randomGames;
            }, function errorCallback(response) {
              window.alert("Fail!");
            });
    };

});