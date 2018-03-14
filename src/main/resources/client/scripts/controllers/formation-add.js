angular.module('spiApp')
    .controller('formationAddCtrl', ['$scope', 'formationSvc', '$location', function ($scope, formationSvc, $location) {

        $scope.sujet = "une formation";
        $scope.editoption = "l\'ajout";

        $scope.editEnseignant = {};



        $scope.editSubmit = function () {
            formationSvc.saveFormation($scope.editFormation, function (data) {
                if (data == "") {
                    $scope.cannotAdd = true;
                }
                else {
                    $location.path('formations');
                    $scope.cannotAdd = false;
                }
            });
        }

        $scope.cancelEditing = function () {
            $scope.cannotAdd = false;
            $location.path('formations');
        }


    }]);