angular.module('spiApp')
    .controller('formationDetailCtrl', ['$scope', 'formationSvc', '$routeParams', function ($scope, formationSvc, $routeParams) {

        $scope.formation = {};

        //Recuperation d'formation
        getFormation = function () {
            formationSvc.getFormationById($routeParams.id, function (data) {
                $scope.formation = data;
            });
        }

        getFormation();

    }]);
