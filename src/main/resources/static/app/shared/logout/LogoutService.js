/**
 * Created by Andrzej on 2017-11-02.
 */

'use strict'

angular.module('shared').service('LogoutService', function(){

    this.logOut = function($http) {
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("password");
        return $http.post("/logout").then(
            function (response) {
                return response.status;
            },
            function (response) {
                return response.status;
            });
    }
});