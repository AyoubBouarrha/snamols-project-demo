angular.module('spiApp')
  .controller('evaluationCtrl', ['$scope', 'evaluationSvc', 'promotionSvc', 'ueSvc', 'NgTableParams', function ($scope, evaluationSvc, promotionSvc, ueSvc, NgTableParams) {

    $scope.sujet = "une évaluation";
    $scope.editoption = "la modification";

    $scope.evaluations = [];
    $scope.editevaluation = {};

    $scope.cannotRemove = false;

    $scope.noEvaluationValid = true;


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
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        $scope.tableParams
      });
    }
    getEvaluations();


    $scope.cancelEditing = function () {
      $('#form-collapse').collapse('hide');
      $scope.formevaluation.$setPristine();
      $scope.editevaluation = {};
      getEvaluations();
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
          if (data == true) {
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
          else{
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



  }]);