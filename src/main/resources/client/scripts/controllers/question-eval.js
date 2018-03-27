angular.module('spiApp')
  .controller('questionEvalCtrl', ['$scope', 'questionEvalSvc', 'questionSvc', 'cqSvc', 'NgTableParams', '$compile', function ($scope, questionEvalSvc, questionSvc, cqSvc, NgTableParams, $compile) {

    $scope.questionsNotAffected = [];
    $scope.sujet = "question";


    var idQuestionRowaffectationOpened = 0;

    $scope.editSubmit = function () {

      editquestioneval = {};
      editquestioneval.question = {};
      editquestioneval.question.idQuestion = $scope.addQuestion.idQuestion;
      editquestioneval.rubriqueEvaluation = {};
      editquestioneval.rubriqueEvaluation.idRubriqueEvaluation = $scope.selectedIdRubriqueEvaluation;
      editquestioneval.ordre = $scope.addQuestion.ordre;
      editquestioneval.intitule = $scope.addQuestion.intitule;
      editquestioneval.idQualificatif = $scope.addQuestion.idQualificatif;

      console.log(editquestioneval);
      questionEvalSvc.saveQuestionEval(editquestioneval, function (data) {
        getAffectedQuestionsEvaluation();
        $('#form-qst-collapse').collapse('hide');
      });

    }


    getQualificatifs = function () {
      cqSvc.getCq(function (data) {

        $scope.qualificatifs = data;
        console.log(data);
      });
    }

    getQualificatifs();

    var getQuestionNotAffected = function (idEvaluation, idRubrique) {
      questionEvalSvc.getNotAffectedQuestions($scope.selectedIdEvaluation, $scope.selectedIdRubrique, function (data) {
        console.log(data);
        $scope.questionsNotAffected = data;
        //$scope.questionsNotAffected = data;
      });
    }

    $scope.showAddBox = function () {
      console.log("opened");
      $scope.addQuestion = {};
      $scope.formQuestion.$setPristine();
      getQuestionNotAffected();
      $scope.editoption = "l\'ajout";
      $('#form-qst-collapse').collapse('show');

    }


    $scope.cancelEditing = function () {
      $('#form-qst-collapse').collapse('hide');
      $scope.formQuestion.$setPristine();
      $scope.editquestion = {};
    }


    $scope.newValue = function (value) {
      if (value === "oui") {
        $("#newIntitule").prop('disabled', false);
        console.log("oui" + value);
      }
      else {
        console.log("non" + value);
        $scope.addQuestion.intitule = null;
        $("#newIntitule").prop('disabled', true);
      }
    }



    getAffectedQuestionsEvaluation = function () {
      questionEvalSvc.getAffectedQuestionsEval($scope.selectedIdEvaluation, $scope.selectedIdRubrique, function (data) {

        data.forEach(questionEval => {
          var idQual = 0;
          if (questionEval.idQualificatif != undefined) {
            idQual = questionEval.idQualificatif;
          }
          else {
             idQual = questionEval.question.idQualificatif;
          }
          console.log("idQual"+idQual);
          cqSvc.getQualificatifById(idQual, function (data) {
            questionEval.quaminimal = data.minimal;
            questionEval.quamaximal = data.maximal;
          });
        });
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        console.log(data);
      });
    }

    getAffectedQuestionsEvaluation();




  }]);




