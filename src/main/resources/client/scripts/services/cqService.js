angular.module('spiApp')
  .factory('cqSvc', ['$http', function ($http) {
    var factory = {};

    factory.getCq = function (callback) {
      var endPoint = "http://localhost:8090/qualificatifs"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    factory.saveCq = function (cq, callback) {
      var endPoint = "http://localhost:8090/qualificatifs";
      console.log(cq);
      $http.post(endPoint, cq).then(function (response) {
        callback(response.data);
      });
    }
  
  
    factory.updateCq = function (cq, callback) {
      var endPoint = "http://localhost:8090/qualificatifs";
      $http.put(endPoint, cq).then(function (response) {
        callback(response.data);
      });
    }


    factory.deleteCqById = function (idqualificatif, callback) {
      var endPoint = "http://localhost:8090/qualificatifs/" + idqualificatif;
      $http.delete(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    factory.getQualificatifById = function (idQualificatif, callback) {
      var endPoint = "http://localhost:8090/qualificatifs/" + idQualificatif
      $http.get(endPoint).then(function (response) {
          console.log(response.data);
        callback(response.data);
      });
    }


    return factory;
  
  }])