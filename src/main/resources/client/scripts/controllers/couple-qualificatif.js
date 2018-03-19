angular.module('spiApp')
  .controller('cqCtrl', ['$scope', 'cqSvc', 'NgTableParams', function ($scope, cqSvc, NgTableParams) {

    $scope.sujet = "un couple qualificatif";  
    $scope.editoption = "la modification";

    $scope.cq = [];
    $scope.editcq = {};

    $scope.cannotRemove = false;

    //Recuperation des couples qualifs
    getCq = function () {
        cqSvc.getCq(function (data) {
        console.log(data);
        $scope.cq = data;
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        $scope.tableParams
      });
    }

    getCq();


    $scope.cancelEditing = function () {
      $('#form-collapse').collapse('hide');
      $scope.formcq.$setPristine();
      $scope.editcq={};
      getCq();
    }

    $scope.editSubmit = function () {
      console.log($scope.editoption);
      if( $scope.editoption == "la modification"){
        cqSvc.updateCq($scope.editcq, function (data) {
          $('#form-collapse').collapse('hide');
          $scope.formcq.$setPristine();
          getCq();
        });
      }
      else{
        console.log($scope.editcq);
        cqSvc.saveCq($scope.editcq, function (data) {
          getCq();
        });
      }
     
    }


    $scope.validateDelete = function () {
      cqSvc.deleteCqById($scope.selectedCq.idQualificatif, function (data) {
        if (data == true) {
          $scope.cannotRemove = false;
          $('#delete-modal').modal('hide');
          getCq();
        }
        else {
          $scope.cannotRemove = true;
        }
      })
    }

    $scope.cancelDelete = function () {
      $('#delete-modal').modal('hide');
    }

    $scope.showInfo = function (cq) {
      $scope.selectedCq = cq;
    }


    $scope.showDeleteBox = function (cq) {
      $scope.selectedCq = cq;
      $scope.cannotRemove = false;
    }

    $scope.showUpdateBox = function (cq) {
      $scope.editoption = "la modification";
      $scope.editcq = cq;
      $('#form-collapse').collapse('show');
    }

    $scope.showAddBox = function () {
      $scope.editcq = {};
      console.log("fffff");
      $scope.formcq.$setPristine();
      $scope.editoption = "l\'ajout";
      $('#form-collapse').collapse('show');
    }



  }]);
