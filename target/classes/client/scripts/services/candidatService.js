angular.module('spiApp')
  .factory('candidatSvc', ['$http', function ($http) {
    var factory = {};

    factory.getCandidatsCount = function (callback) {
      var endPoint = "http://localhost:8090/enseignants/count"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    return factory;

  }])