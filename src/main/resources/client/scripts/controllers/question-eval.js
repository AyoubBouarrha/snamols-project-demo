angular.module('spiApp')
  .controller('questionEvalCtrl', ['$scope', 'questionEvalSvc', 'questionSvc','cqSvc', 'NgTableParams' , '$compile' , function ($scope, questionEvalSvc, questionSvc, cqSvc,NgTableParams, $compile) {

    $scope.questions = [];
    $scope.sujet = "question";


    var idQuestionRowaffectationOpened = 0;

    $scope.editSubmit = function () {

      editquestion = {};
      editquestion.evaluation = {};
      editquestion.evaluation.idEvaluation = $scope.selectedEvaluation.idEvaluation;//1
      editquestion.rubrique = {};
      editquestion.rubrique.idRubrique = $scope.addRubrique.idRubrique;
      editquestion.ordre = $scope.addRubrique.ordre;
      editquestion.designation = $scope.addRubrique.designation;

      console.log(editquestion);
      questionEvalSvc.saveRubrique(editquestion, function (data) {
        getRubriquesEvaluation();
        $('#form-rub-collapse').collapse('hide');
      });

    }


    $scope.showAddBox = function () {
      $scope.editquestion = {};
      //$scope.formRubrique.$setPristine();
      $scope.editoption = "l\'ajout";
      $('#form-rub-collapse').collapse('show');
    }


    $scope.cancelEditing = function () {
      $('#form-rub-collapse').collapse('hide');
      //$scope.formRubrique.$setPristine();
      $scope.editquestion = {};
    }



    getAffectedQuestionsEvaluation = function () {
      questionEvalSvc.getAffectedQuestionsEval($scope.selectedIdEvaluation,$scope.selectedIdRubrique, function (data) {
        console.log(data);
        data.forEach(questionEval => {
            cqSvc.getQualificatifById(questionEval.question.idQualificatif, function (data) {
                questionEval.quaminimal = data.minimal;
                questionEval.quamaximal = data.maximal;
            });
          });
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
      });
    }

    getAffectedQuestionsEvaluation();




  }]);




