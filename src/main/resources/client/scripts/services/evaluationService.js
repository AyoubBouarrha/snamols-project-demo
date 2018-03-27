angular.module('spiApp')
.factory('evaluationSvc', ['$http', function ($http) {
  var factory = {};


  factory.getEvaluations = function (callback) {
    var endPoint = "http://localhost:8090/evaluations"   
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  factory.getEvaluationsByEnseignant = function (noEnseignant,callback) {
    var endPoint = "http://localhost:8090/evaluations/getEvaByEnseignant/"+noEnseignant   
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }


  factory.saveEvaluation = function (evaluation, callback) {
    var endPoint = "http://localhost:8090/evaluations"
    $http.post(endPoint, evaluation).then(function (response) {
      callback(response.data);
    });
  }

  factory.getEvaluationById = function (idEvaluation, callback) {
    var endPoint = "http://localhost:8090/evaluations/" + idEvaluation
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }


  factory.deleteEvaluationById = function (idEvaluation, callback) {
    var endPoint = "http://localhost:8090/evaluations/" + idEvaluation
    $http.delete(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  factory.updateEvaluation = function (evaluation, callback) {
    var endPoint = "http://localhost:8090/evaluations"
    $http.put(endPoint, evaluation).then(function (response) {
      callback(response.data);
    });
  }



  return factory;

}])