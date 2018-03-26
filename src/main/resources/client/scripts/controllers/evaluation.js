angular.module('spiApp')
  .controller('evaluationCtrl', ['$scope', 'evaluationSvc', 'promotionSvc', 'ueSvc', 'NgTableParams', '$compile', 'rubriqueEvalSvc', function ($scope, evaluationSvc, promotionSvc, ueSvc, NgTableParams, $compile, rubriqueEvalSvc) {

    $scope.sujet = "une évaluation";
    $scope.editoption = "la modification";

    $scope.evaluations = [];
    $scope.editevaluation = {};

    $scope.cannotRemove = false;

    $scope.noEvaluationValid = true;

    var idRowaffectationOpened = 0;


    getAllAnneeUniv = function () {
      promotionSvc.getAllAnneeUniv(function (data) {
        $scope.anneeUnivs = data;
      });
    }

    getAllAnneeUniv();

    //Recuperation des evaluations
    getEvaluations = function () {
      evaluationSvc.getEvaluations(function (data) {
        console.log(data);
        $scope.evaluations = data;

        data.forEach(evaluation => {
          evaluation.debutReponse = new Date(evaluation.debutReponse);
          evaluation.finReponse = new Date(evaluation.finReponse);
        });

        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        $scope.tableParams
      });
    }
    getEvaluations();


    $scope.cancelEditing = function () {
      $('#form-collapse').collapse('hide');
      $scope.formevaluation.$setPristine();
      $scope.editevaluation = {};
      $(".form-groupe-info-eval").prop('hidden', true);
      $("#formation").prop('disabled', true);
      $("#ue").prop('disabled', true);
      $("#ec").prop('disabled', true);
      $scope.noEvaluationValid = true;
    }

    $scope.editSubmit = function () {
      if ($scope.editoption == "la modification") {
        console.log($scope.editoption);
        evaluationSvc.updateEvaluation($scope.editevaluation, function (data) {
          $('#form-collapse').collapse('hide');
          $scope.formevaluation.$setPristine();
          $scope.editEvaluation = {};
          $(".form-groupe-info-eval").prop('hidden', true);
          $("#formation").prop('disabled', true);
          $("#ue").prop('disabled', true);
          $("#ec").prop('disabled', true);
          getEvaluations();
        });
      }
      else {
        $scope.editevaluation.enseignant = {};
        $scope.editevaluation.enseignant.noEnseignant = 1;
        //$scope.editevaluation.noEvaluation = 1;
        $scope.editevaluation.promotion = {};
        $scope.editevaluation.promotion.id = {};
        $scope.editevaluation.promotion.id.anneeUniversitaire = $scope.editevaluation.anneeUniv;
        $scope.editevaluation.promotion.id.codeFormation = $scope.editevaluation.formation;
        console.log($scope.editevaluation);
        evaluationSvc.saveEvaluation($scope.editevaluation, function (data) {

          console.log(data);
          if (data != "null") {
            console.log(data);
            $scope.noEvaluationValid = true;
            $('#form-collapse').collapse('hide');
            $scope.formevaluation.$setPristine();
            $scope.editevaluation = {};
            $(".form-groupe-info-eval").prop('hidden', true);
            $("#formation").prop('disabled', true);
            $("#ue").prop('disabled', true);
            $("#ec").prop('disabled', true);
            getEvaluations();
          }
          else {
            $scope.noEvaluationValid = false;
          }

        });
      }

    }


    $scope.validateDelete = function () {
      evaluationSvc.deleteEvaluationById($scope.selectedEvaluation.idEvaluation, function (data) {
        if (data == true) {
          $scope.cannotRemove = false;
          $('#delete-modal').modal('hide');
          getEvaluations();
        }
        else {
          $scope.cannotRemove = true;
        }
      })
    }

    $scope.cancelDelete = function () {
      $('#delete-modal').modal('hide');
    }


    $scope.showInfo = function (evaluation) {
      $scope.selectedEvaluation = evaluation;
    }

    $scope.showDeleteBox = function (evaluation) {
      $scope.selectedEvaluation = evaluation;
      $scope.cannotRemove = false;
    }

    $scope.showUpdateBox = function (evaluation) {
      $scope.editoption = "la modification";
      $scope.editevaluation = evaluation;
      $scope.editevaluation.debutReponse = new Date($scope.editevaluation.debutReponse);
      $scope.editevaluation.finReponse = new Date($scope.editevaluation.finReponse);
      $scope.editevaluation.formation = evaluation.promotion.id.codeFormation;
      $scope.editevaluation.anneeUniv = evaluation.promotion.id.anneeUniversitaire;
      console.log(evaluation);
      $('#form-collapse').collapse('show');
      $(".form-groupe-key-eval").prop('hidden', true);
      $(".form-groupe-info-eval").prop('hidden', false);
      $("#noEvaluation").prop('disabled', true);

      $("#validateBtn").prop('disabled', false);
    }

    $scope.showAddBox = function () {
      $scope.editevaluation = {};
      $scope.formevaluation.$setPristine();
      $scope.editoption = "l\'ajout";
      $(".form-groupe-key-eval").prop('hidden', false);
      $("#noEvaluation").prop('disabled', false);
      $("#formation").prop('disabled', true);
      $('#form-collapse').collapse('show');

      $("#validateBtn").prop('disabled', true);
      $scope.noEvaluationValid = true;
    }



    $scope.onChangeAnneeUniv = function () {
      //traitement à faire avec le service

      promotionSvc.getCodeFormationsByAnneUniv($scope.editevaluation.anneeUniv, function (data) {
        console.log(data);
        $scope.formations = data;
        $("#formation").prop('disabled', false);
      });
    }

    $scope.onChangeFormation = function () {
      //traitement à faire avec le service

      console.log($scope.editevaluation.formation);
      ueSvc.getUEByFormation($scope.editevaluation.formation, function (data) {
        console.log(data);
        $scope.ues = data;
        $("#ue").prop('disabled', false);
      });


    }

    $scope.onChangeUE = function () {

      $(".form-groupe-info-eval").prop('hidden', false);
      $("#ec").prop('disabled', false);
      $("#validateBtn").prop('disabled', false);
    }


    $scope.showAffectationRubriqueRow = function (evaluation) {
      if (idRowaffectationOpened != 0)
        $('#subtr' + idRowaffectationOpened).remove();

      $scope.selectedEvaluation = evaluation;
      console.log("tr" + evaluation.idEvaluation);
     // var row = $(this).closest("#tr" + evaluation.idEvaluation);
      //console.log(row);
      var el = $compile('<tr  class="animated  fadeInUp " id="' + 'subtr' + evaluation.idEvaluation + '" style="background:#f1f1f1;"><td  colspan="7" >' +
        '<button ng-click="closeRow(' + evaluation.idEvaluation + ')"  title= "Fermer" class="btn btn-danger" style="float:right;">' +
        '<i class="fas fa-times-circle" ></i>' +
        '</button>' +
        '<div style="width:80%; margin:auto;">' +
        '<viewrubriqueeval></viewrubriqueeval>' +
        '</div>' +
        '</td></tr>')($scope);
      $("#tr" + evaluation.idEvaluation).after(el);
      idRowaffectationOpened = evaluation.idEvaluation;
    }

    $scope.closeRow = function (idEvaluation) {
      console.log("subtr" + idEvaluation);
      $('#subtr' + idEvaluation).remove();
      idRowaffectationOpened = 0;
    }

    $scope.validateDeleteRub = function () {
      rubriqueEvalSvc.deleteRubriquesEvalById($scope.selectedRubriqueEval.idRubriqueEvaluation, function (data) {
        console.log(data);
        if (data == true) {
          $scope.cannotRemove = false;
          $('#delete-modal-rub-eva').modal('hide');
          getRubriquesEvaluation();
        }
        else {
          $scope.cannotRemove = true;
        }
      })
    }

    $scope.showDeleteBoxRub = function (rubriqueEval) {
      $scope.selectedRubriqueEval = rubriqueEval;
      console.log(rubriqueEval);
      //$scope.cannotRemove = false;
    }


    $scope.cancelDeleteRub = function () {
      console.log("hide");
      $('#delete-modal-rub-eva').modal('hide');
    }


    $scope.showDeleteBoxQst = function (questionEval) {
      $scope.selectedQuestionEval = questionEval;
      console.log(questionEval);
      //$scope.cannotRemove = false;
    }


    $scope.cancelDeleteRub = function () {
      console.log("hide");
      $('#delete-modal-qst-eva').modal('hide');
    }

  }]);