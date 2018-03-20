angular.module('spiApp')
    .controller('userCtrl', ['$scope', 'userSvc', '$location', function ($scope, userSvc, $location) {
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
                    $('.login-container').css('height', '330px');
                }

            });
        }

        $scope.disconnect = function (){
            userSvc.disconnect(function (data){                
                $location.path('login');
            })
        }

        $scope.getCurrentUser = function (){       
            userSvc.getUserSession(function (data){       
                console.log(data);
                if(data==""){           
                    //$location.path('login'); 
                }
                else {
                    $scope.currentuser = data;       
                }                    
            })
        }
        
        $scope.getCurrentUser();



        /*function initScope() {
           console.log("changed");
           $scope.getCurrentUser();
        }
    
        initScope();
        $scope.$on('$routeChangeSuccess', initScope);*/

    }]);