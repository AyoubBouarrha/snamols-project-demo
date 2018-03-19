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
        cqSvc.getQualificatifById(question.idQualificatif,function (data) {
          question.quaminimal=data.minimal;
          question.quamaximal=data.maximal;
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
    $scope.editQuestion = question;
    $('#form-collapse').collapse('show');
  }

  $scope.showAddBox = function () {
    $scope.editQuestion= {};
    $scope.formquestion.$setPristine();
    $scope.editoption = "l\'ajout";
    $('#form-collapse').collapse('show');
  }


  $scope.getQualificatifById= function(idQualificatif){
    //var valeur = "";
    cqSvc.getQualificatifById(idQualificatif,function (data) {
      console.log(data.minimal +" -- "+data.maximal);
      return data.minimal +" -- "+data.maximal;
    });
    //return valeur;
  }


  console.log($scope.getQualificatifById(1));


}]);