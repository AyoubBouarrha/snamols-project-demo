angular.module('spiApp')
.factory('questionSvc', ['$http' , function ($http) {
  var factory = {};

  factory.getQuestions = function (callback) {
    var endPoint = "http://localhost:8090/questions"
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  
  factory.deleteQuestionById = function (idQuestion, callback) {
    var endPoint = "http://localhost:8090/questions/" + idQuestion 
    $http.delete(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  factory.saveQuestion = function (question, callback) {
    var endPoint = "http://localhost:8090/questions"
    $http.post(endPoint, question).then(function (response) {
      callback(response.data);
    });
  }

  factory.UpdateQuestion = function (question, callback) {
    var endPoint = "http://localhost:8090/questions"
    $http.put(endPoint, question).then(function (response) {
      callback(response.data);
    });
  }


  return factory;

}])