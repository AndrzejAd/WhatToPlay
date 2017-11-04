/**
 * Created by Andrzej on 2017-11-02.
 */

'use strict'

angular.module('shared').service('LogoutService', function(){


    this.logOut = function($http) {
        $http.post("/logout").then(
            function (response) {
                return response.status;
            },
            function (response) {
                return response.status;
            });
    }
});