
angular.module('spiApp')
  .controller('enseignantAddCtrl', ['$scope', 'enseignantSvc', '$location' ,function ($scope, enseignantSvc, $location) {

    $scope.sujet = "un enseignant";
    $scope.editoption = "l\'ajout";

    $scope.editEnseignant = {};

    

    $scope.editSubmit = function () {
      enseignantSvc.saveEnseignant($scope.editEnseignant, function (data) {
        $location.path('enseignants');
      });
    }   

    $scope.cancelEditing = function () {
      $location.path('enseignants');
    }


  }]);