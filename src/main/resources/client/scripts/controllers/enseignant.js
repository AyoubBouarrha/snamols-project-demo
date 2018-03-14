
angular.module('spiApp')
  .controller('enseignantCtrl', ['$scope', 'enseignantSvc', 'NgTableParams', function ($scope, enseignantSvc, NgTableParams) {

    $scope.sujet = "un enseignant";
    $scope.editoption = "la modification";

    $scope.enseignants = [];
    $scope.editEnseignant = {};

    $scope.cannotRemove = false;

    //Recuperation des enseignants
    getEnseignants = function () {
      enseignantSvc.getEnseignants(function (data) {
        console.log(data);
        $scope.enseignants = data;
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        $scope.tableParams
      });
    }

    getEnseignants();


    $scope.cancelEditing = function () {
      $('#form-collapse').collapse('hide');
      $scope.formenseignant.$setPristine();
      $scope.editEnseignant={};
      getEnseignants();
    }

    $scope.editSubmit = function () {
      console.log($scope.editoption);
      enseignantSvc.updateEnseignant($scope.editEnseignant, function (data) {
        $('#form-collapse').collapse('hide');
        $scope.formenseignant.$setPristine();
        getEnseignants();
      });
    }


    $scope.validateDelete = function () {
      enseignantSvc.deleteEnseignantById($scope.selectedEnseignant.noEnseignant, function (data) {
        if (data == true) {
          $scope.cannotRemove = false;
          $('#delete-modal').modal('hide');
          getEnseignants();
        }
        else {
          $scope.cannotRemove = true;
        }
      })
    }

    $scope.cancelDelete = function () {
      $('#delete-modal').modal('hide');
    }


    $scope.showInfo = function (enseignant) {
      $scope.selectedEnseignant = enseignant;
    }

    $scope.showDeleteBox = function (enseignant) {
      $scope.selectedEnseignant = enseignant;
      $scope.cannotRemove = false;
    }

    $scope.showUpdateBox = function (enseignant) {
      $scope.editoption = "la modification";
      $scope.editEnseignant = enseignant;
      $('#form-collapse').collapse('show');
    }

    $scope.showAddBox = function () {
      $scope.editEnseignant = {};
      $scope.formenseignant.$setPristine();
      $scope.editoption = "l\'ajout";
      $('#form-collapse').collapse('show');
    }



  }]);