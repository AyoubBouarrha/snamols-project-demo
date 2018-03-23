angular.module('spiApp')
  .controller('rubriqueEvalCtrl', ['$scope', 'rubriqueEvalSvc', 'rubriqueSvc', 'NgTableParams', function ($scope, rubriqueEvalSvc, rubriqueSvc, NgTableParams) {

    $scope.rubriques = [];
    $scope.sujet = "rubrique";

    

    $scope.editSubmit = function () {

      Rubrique_evaluation = {};
      Rubrique_evaluation.evaluation = {};
      Rubrique_evaluation.evaluation.idEvaluation = $scope.selectedEvaluation.idEvaluation;//1
      Rubrique_evaluation.rubrique = {};
      Rubrique_evaluation.rubrique.idRubrique = $scope.addRubrique.idRubrique;
      Rubrique_evaluation.ordre = $scope.addRubrique.ordre;
      Rubrique_evaluation.designation = $scope.addRubrique.designation;

      console.log(Rubrique_evaluation);
      rubriqueEvalSvc.saveRubrique(Rubrique_evaluation, function (data) {
        getRubriquesEvaluation();

      });

    }


    $scope.showAddBox = function () {
      $scope.Rubrique_evaluation = {};
      //$scope.formRubrique.$setPristine();
      $scope.editoption = "l\'ajout";
      $('#form-rub-collapse').collapse('show');
    }
    

    $scope.cancelEditing = function () {
      $('#form-rub-collapse').collapse('hide');
      //$scope.formRubrique.$setPristine();
      $scope.Rubrique_evaluation = {};
    }


    $scope.getRubriqueById = function (idRubrique) {

      rubriqueSvc.getRubriqueById(idRubrique, function (data) {
        return data.designation;
      });

    }

    getRubriquesEvaluation = function () {
      console.log($scope.selectedEvaluation.idEvaluation);
      rubriqueEvalSvc.RubriquesEvalByIdEvaluation($scope.selectedEvaluation.idEvaluation,function (data) {
        console.log(data);
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        $scope.tableParams
      });
    }

    getRubriquesEvaluation();


    $scope.newValue = function (value) {
      if (value === "oui") {        
        $("#newDesignation").prop('disabled', false);
        console.log("oui" + value);
      }
      else {
        console.log("non" + value);
        $("#newDesignation").prop('disabled', true);
      }
    }




    $scope.rubriques = [];
  

    // $scope.validateDeleteRub = function () {
    //   rubriqueEvalSvc.deleteRubriquesEvalById($scope.selectedRubriqueEval.idRubriqueEvaluation, function (data) {
    //     console.log(data);
    //     if (data == true) {
    //       $scope.cannotRemove = false;
    //       $('#delete-modal-rub-eva').modal('hide');
    //       getRubriquesEvaluation();
    //     }
    //     else {
    //       $scope.cannotRemove = true;
    //     }
    //   })
    // }
  
  
    // $scope.cancelDeleteRub = function () {
    //   console.log("hide");
    //   $('#delete-modal-rub-eva').modal('hide');
    // }
  
  
    $scope.showInfo = function (rubrique) {
      $scope.selectedRubrique = rubrique;
    }
  
    // $scope.showDeleteBoxRub = function (rubriqueEval) {
    //   $scope.selectedRubriqueEval = rubriqueEval;
    //   console.log(rubriqueEval) ; 
    //   //$scope.cannotRemove = false;
    // }

  
    getRubriques = function (){
      rubriqueSvc.getRubriques(function (data){
  
          $scope.rubriques = data ;
          console.log(data);
    
      });   
  }
  
  getRubriques() ;
    
  
  }]);




