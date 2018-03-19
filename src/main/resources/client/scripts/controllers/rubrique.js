
angular.module('spiApp')
.controller('rubriqueCtrl', ['$scope', 'rubriqueSvc', 'NgTableParams', function ($scope, rubriqueSvc, NgTableParams) {

  $scope.sujet = "une rubrique";
  $scope.editoption = "la modification";

  $scope.rubriques = [];
  $scope.editRubrique = {};

  $scope.cannotRemove = false;

  //Recuperation des rubriques
  getRubriques = function () {
    rubriqueSvc.getRubriques(function (data) {
      console.log(data);
      $scope.rubriques = data;
      $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
      $scope.tableParams
    });
  }
  getRubriques();

  $scope.cancelEditing = function () {
    $('#form-collapse').collapse('hide');
    $scope.formrubrique.$setPristine();
    $scope.editRubrique={};
    getRubriques();
  }

  $scope.editSubmit = function () {
    if($scope.editoption == "la modification"){
      console.log($scope.editoption);
      rubriqueSvc.updateRubrique($scope.editRubrique, function (data) {
        $('#form-collapse').collapse('hide');
        $scope.formrubrique.$setPristine();
        $scope.editRubrique={};
        getRubriques();
      });
    }
    else {
      rubriqueSvc.saveRubrique($scope.editRubrique, function (data) {
        $('#form-collapse').collapse('hide');
        $scope.formrubrique.$setPristine();
        $scope.editRubrique={};
        getRubriques();
      });
    }
   
  }


  $scope.validateDelete = function () {
    rubriqueSvc.deleteRubriqueById($scope.selectedRubrique.idRubrique, function (data) {
      if (data == true) {
        $scope.cannotRemove = false;
        $('#delete-modal').modal('hide');
        getRubriques();
      }
      else {
        $scope.cannotRemove = true;
      }
    })
  }

  $scope.cancelDelete = function () {
    $('#delete-modal').modal('hide');
  }


  $scope.showInfo = function (rubrique) {
    $scope.selectedRubrique = rubrique;
  }

  $scope.showDeleteBox = function (rubrique) {
    $scope.selectedRubrique = rubrique;
    $scope.cannotRemove = false;
  }

  $scope.showUpdateBox = function (rubrique) {
    $scope.editoption = "la modification";
    $scope.editRubrique = rubrique;
    $('#form-collapse').collapse('show');
  }

  $scope.showAddBox = function () {
    $scope.editRubrique = {};
    $scope.formrubrique.$setPristine();
    $scope.editoption = "l\'ajout";
    $('#form-collapse').collapse('show');
  }



}]);