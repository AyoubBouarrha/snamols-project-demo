angular.module('spiApp')
    .controller('userCtrl', ['$scope', 'userSvc', '$location', function ($scope, userSvc, $location) {
        $scope.statusAuthentification = true;

        $scope.loginSubmit = function () {
            console.log($scope.editLogin);
            userSvc.authentification($scope.editLogin, function (data) {
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

        $scope.disconnect = function (){
            userSvc.disconnect(function (data){                
                $location.path('login');
            })
        }

    }]);