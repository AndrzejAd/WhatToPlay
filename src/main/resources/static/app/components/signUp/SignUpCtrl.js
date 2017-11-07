'use strict';

angular.module('signup').controller('SignUpCtrl', function ($scope, $window, $location, SendUserDataService,
                                                            CheckIfAuthenticatedService) {
    $scope.signup = function () {
        var userToJson = {
            username: $scope.username,
            password: $scope.password,
            firstName: '',
            lastName: '',
            email: $scope.email
        };
        var msg = SendUserDataService.validateUserData($scope.username, $scope.password);

        if (msg === "correct") {
            SendUserDataService.sendUserData(userToJson, $location).then(
                function (response) {
                    if (response.status == 409) {
                        console.log(response.data);
                    } else {
                        var completeUrl = "/app/components/home/home.html";
                        window.location.replace(completeUrl);
                    }
                }
            );
        } else {
            if (msg === "username length error") {
                $scope.wrongUsername = true;
                console.log($scope.wrongUsername);
            } else {
                $scope.wrongPassword = true;
            }

        }
    };

    $scope.isLogged = function () {
        return CheckIfAuthenticatedService.isLogged();
    }

});