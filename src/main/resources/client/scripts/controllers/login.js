angular.module('spiApp')
    .controller('loginCtrl', ['$scope', 'loginSvc', '$location', function ($scope, loginSvc, $location) {
        $scope.statusAuthentification = true;

        $scope.loginSubmit = function () {
            console.log($scope.editLogin);
            loginSvc.authentification($scope.editLogin, function (data) {
                $scope.statusAuthentification = data;
                if (data == true) {
                    $location.path('home');
                    $('.login-container').css('height', '280px');
                }
                else {
                    $('.login-container').css('height', '330px');
                }

            });
        }

    }]);