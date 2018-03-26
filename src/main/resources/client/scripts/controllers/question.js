angular.module('spiApp')
  .controller('questionCtrl', ['$scope', 'questionSvc', 'cqSvc', 'NgTableParams', function ($scope, questionSvc, cqSvc, NgTableParams) {

    $scope.sujet = "une question";
    $scope.editoption = "la modification";

    $scope.questions = [];
    $scope.editQuestion = {};

    $scope.cannotRemove = false;

    //Recuperation des enseignants
    getQuestions = function () {
      questionSvc.getQuestions(function (data) {
        console.log(data);

        $scope.questions = data;
        data.forEach(question => {
          cqSvc.getQualificatifById(question.idQualificatif, function (data) {
            question.quaminimal = data.minimal;
            question.quamaximal = data.maximal;
          });
        });
        $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
        $scope.tableParams
      });
    }

    getQuestions();



    $scope.validateDelete = function () {
      questionSvc.deleteQuestionById($scope.selectedQuestion.idQuestion, function (data) {
        if (data == true) {
          $scope.cannotRemove = false;
          $('#delete-modal').modal('hide');
          getQuestions();
        }
        else {
          $scope.cannotRemove = true;
        }
      })
    }

    $scope.cancelDelete = function () {
      $('#delete-modal').modal('hide');
    }


    $scope.showInfo = function (question) {
      $scope.selectedQuestion = question;
    }

    $scope.showDeleteBox = function (question) {
      $scope.selectedQuestion = question;
      $scope.cannotRemove = false;
    }

    $scope.showUpdateBox = function (question) {
      $scope.editoption = "la modification";
      $('#form-collapse').collapse('show');
      console.log(question.intitule);
      $scope.addQuestion = question;
    }

    $scope.showAddBox = function () {
      $scope.editQuestion = {};
      $scope.formQuestion.$setPristine();
      $scope.editoption = "l\'ajout";
      $('#form-collapse').collapse('show');
    }

    $scope.editSubmit = function () {
      console.log($scope.editoption);
      if ($scope.editoption == "l\'ajout") {
        $scope.addQuestion.type = "QUS";
        console.log($scope.addQuestion);
        questionSvc.saveQuestion($scope.addQuestion, function (data) {
          getQuestions();
          $('#form-collapse').collapse('hide');
          $scope.formQuestion.$setPristine();
          $scope.addQuestion = {};
        });
      }
      else {
        $scope.addQuestion.type = "QUS";
        console.log($scope.addQuestion);
        questionSvc.UpdateQuestion($scope.addQuestion, function (data) {
          getQuestions();
          $('#form-collapse').collapse('hide');
          $scope.formQuestion.$setPristine();
          $scope.addQuestion = {};
        });
      }

    }

    $scope.cancelEditing = function () {
      $('#form-collapse').collapse('hide');
      $scope.formQuestion.$setPristine();
      $scope.editQuestion = {};
      $scope.addQuestion = {};
      getQuestions();
    }


    $scope.getQualificatifById = function (idQualificatif) {
      //var valeur = "";
      cqSvc.getQualificatifById(idQualificatif, function (data) {
        console.log(data.minimal + " -- " + data.maximal);
        return data.minimal + " -- " + data.maximal;
      });
      //return valeur;
    }

    getQualificatif = function () {
      cqSvc.getCq(function (data) {

        $scope.Qualificatifs = data;
        console.log(data);
      });
    }

    getQualificatif();


    console.log($scope.getQualificatifById(1));


  }]);