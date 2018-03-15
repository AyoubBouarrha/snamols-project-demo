angular.module('spiApp')
.factory('questionSvc', ['$http', function ($http) {
  var factory = {};

  factory.getQuestions = function (callback) {
    var endPoint = "http://localhost:8090/questions"
    $http.get(endPoint).then(function (response) {
      callback(response.data);
    });
  }

  factory.getQualificatifById = function (idQualificatif, callback) {
    var endPoint = "http://localhost:8090/getqual/" + idQualificatif
    $http.get(endPoint).then(function (response) {
        console.log(response.data);
      callback(response.data);
    });
  }
  return factory;

}])