angular.module('spiApp')
.factory('rubriqueEvalSvc', ['$http', function ($http) {
  var factory = {};

  
  factory.saveRubrique = function (rubrique, callback) {
    var endPoint = "http://localhost:8090/rubrique-eval"
    $http.post(endPoint, rubrique).then(function (response) {
      //qConsole.log(response.data); 
      callback(response.data);
    });
  }
  factory.getRubriquesEval = function (callback) {
    var endPoint = "http://localhost:8090/rubrique-eval"
    $http.get(endPoint).then(function (response) {
      //qConsole.log(response.data); 
      callback(response.data);
    });
  }


  factory.deleteRubriquesEvalById = function (idRubriquesEval, callback) {
    var endPoint = "http://localhost:8090/rubrique-eval/" + idRubriquesEval 
    $http.delete(endPoint).then(function (response) {
      callback(response.data);
    });
  }


  factory.RubriquesEvalByIdEvaluation = function (idEvaluation, callback) {
    var endPoint = "http://localhost:8090/rubrique-eval/evaluation/" + idEvaluation 
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  factory.getNotAffectedRubriques = function (idEvaluation , callback) {
    var endPoint = "http://localhost:8090/rubrique-eval/notAffectedRubrique/evaluation/"+idEvaluation
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  return factory;

}])