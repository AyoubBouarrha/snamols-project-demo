angular.module('spiApp')
  .factory('formationSvc', ['$http', function ($http) {
    var factory = {};

    factory.getFormations = function (callback) {
      var endPoint = "http://localhost:8090/formations"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }


    factory.saveFormation = function (formation, callback) {
      var endPoint = "http://localhost:8090/formations"
      $http.post(endPoint, formation).then(function (response) {
        callback(response.data);
      });
    }

    factory.getFormationById = function (idFormation, callback) {
      var endPoint = "http://localhost:8090/formations/" + idFormation
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }


    factory.deleteFormationById = function (idFormation, callback) {
      var endPoint = "http://localhost:8090/formations/" + idFormation
      $http.delete(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    factory.updateFormation = function (formation, callback) {
      var endPoint = "http://localhost:8090/formations/"
      $http.put(endPoint, formation).then(function (response) {
        callback(response.data);
      });
    }

    factory.getFormationsCount = function (callback) {
      var endPoint = "http://localhost:8090/formations/count"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    return factory;

  }])