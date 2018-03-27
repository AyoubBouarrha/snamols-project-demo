angular.module('spiApp')
  .controller('rubriqueEvalCtrl', ['$scope', 'rubriqueEvalSvc', 'rubriqueSvc', 'NgTableParams' , '$compile' , function ($scope, rubriqueEvalSvc, rubriqueSvc, NgTableParams, $compile) {

    $scope.rubriques = [];
    $scope.sujet = "rubrique";


    var idQuestionRowaffectationOpened = 0;

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
        $('#form-rub-collapse').collapse('hide');
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
      rubriqueEvalSvc.RubriquesEvalByIdEvaluation($scope.selectedEvaluation.idEvaluation, function (data) {
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


    getRubriques = function () {
       
       //$scope.selectedEvaluation =  $rootScope.selectedEvaluation

      rubriqueSvc.getRubriques(function (data) {

        $scope.rubriques = data;
        console.log(data);

      });
    }

    getRubriques();


    $scope.showAffectationQuestionRow = function (idEvaluation, idRubrique , idRubriqueEvaluation) {
      if (idQuestionRowaffectationOpened != 0)
        $('#subrubtr' + idQuestionRowaffectationOpened).remove();

      $scope.selectedIdEvaluation = idEvaluation;
      $scope.selectedIdRubrique = idRubrique;
      $scope.selectedIdRubriqueEvaluation = idRubriqueEvaluation;
      //$scope.selectedIdRubriqueEvaluation = ;
      console.log("tr" + idRubrique);
      var row = $(this).closest("#tr" + idRubrique);
      var el = $compile('<tr  id="' + 'subrubtr' + idRubrique + '" style="background:#bbbbbb;margin-left : 5px;">'+
        '<td  class="qst-eval-container" style="background:#cccccc;" colspan="4">' +
        '<button ng-click="closeQuestionRow(' + idRubrique + ')"  title= "Fermer" class="btn btn-danger" style="float:right;">' +
        '<i class="fas fa-times-circle" ></i>' +
        '</button>' +
        '<div style="width:80%; margin:auto;">' +
        /*'<h5>Test idEvaluation : {{selectedIdEvaluation}} - idRubrique : {{selectedIdRubrique}}<h5>' +*/
        '<viewquestioneval></viewquestioneval>'+
        '</div>' +
        '</td></tr>')($scope);
      $("#rubtr" + idRubrique).after(el);
      idQuestionRowaffectationOpened = idRubrique;
    }

    $scope.closeQuestionRow = function (idRubrique) {
      console.log("subrubtr" + idRubrique);
      $('#subrubtr' + idRubrique).remove();
      idQuestionRowaffectationOpened = 0;
    }


  }]);




