angular.module('spiApp')
.factory('questionEvalSvc', ['$http', function ($http) {
  var factory = {};

  
  factory.saveQuestionEval = function (questionEval, callback) {
    var endPoint = "http://localhost:8090/questionEvaluation"
    $http.post(endPoint, questionEval).then(function (response) {
      callback(response.data);
    });
  }
  factory.getQuestionsEval = function (callback) {
    var endPoint = "http://localhost:8090/questionEvaluation"
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }


  factory.deleteQuestionEvalById = function (idQuestionEval, callback) {
    var endPoint = "http://localhost:8090/questionEvaluation/" + idQuestionEval 
    $http.delete(endPoint).then(function (response) {
      callback(response.data);
    });
  }


  factory.getAffectedQuestionsEval = function (idEvaluation,idRubrique , callback) {
    var endPoint = "http://localhost:8090/questionEvaluation/affectedQuestion/evaluation/"+idEvaluation+"/rubrique/"+idRubrique
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  factory.getNotAffectedQuestions = function (idEvaluation,idRubrique , callback) {
    var endPoint = "http://localhost:8090/questionEvaluation/notAffectedQuestion/evaluation/"+idEvaluation+"/rubrique/"+idRubrique
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  return factory;

}])