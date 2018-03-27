angular.module('spiApp')
    .controller('userCtrl', ['$scope', 'userSvc', 'enseignantSvc', '$location', function ($scope, userSvc, enseignantSvc, $location) {
        $scope.statusAuthentification = true;

        $scope.loginSubmit = function () {
            //console.log($scope.editLogin);
            userSvc.authentification($scope.editLogin, function (data) {
                $scope.statusAuthentification = data;
                if (data == true) {
                    $location.path('home');
                    $('.login-container').css('height', '280px');
                }
                else {
                    $('.login-container').css('height', '350px');
                }

            });
        }

        $scope.disconnect = function () {
            userSvc.disconnect(function (data) {
                $location.path('login');
            })
        }

        $scope.getCurrentUser = function () {
            userSvc.getUserSession(function (data) {
                console.log(data);
                if (data == "") {
                    $location.path('login');
                }
                else {
                    $scope.currentuser = data;
                    if ($scope.currentuser.role == "Prof") {
                        $scope.currentuser.enseignant = {};
                        enseignantSvc.getEnseignantById(data.noEnseignant, function (data) {
                            $scope.currentuser.enseignant = data;
                            $location.path('evaluations');
                        });

                        console.log($scope.currentuser);
                    }

                }
            })
        }

        $scope.getCurrentUser();



        function initScope() {
            console.log("changed");
            $scope.getCurrentUser();
        }

        initScope();
        $scope.$on('$routeChangeSuccess', initScope);

    }]);