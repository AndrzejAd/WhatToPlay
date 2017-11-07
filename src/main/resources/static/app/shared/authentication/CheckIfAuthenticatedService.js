'use strict'
/**
 * Created by Andrzej on 2017-11-06.
 */

angular.module('shared').service( 'CheckIfAuthenticatedService', function() {

    this.isLogged = function(){
        if ( sessionStorage.getItem("username" ) && sessionStorage.getItem("password" ) ){
            return true;
        } else{
            return false;
        }
    }

});