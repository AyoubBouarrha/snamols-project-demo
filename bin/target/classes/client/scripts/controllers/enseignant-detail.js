angular.module('spiApp')
    .controller('enseignantDetailCtrl', ['$scope', 'enseignantSvc', '$routeParams', function ($scope, enseignantSvc, $routeParams) {

        $scope.enseignant = {};

        //Recuperation d'enseignant
        getEnseignant = function () {
            enseignantSvc.getEnseignantById($routeParams.id, function (data) {
                $scope.enseignant = data;
            });
        }

        getEnseignant();

    }]);
