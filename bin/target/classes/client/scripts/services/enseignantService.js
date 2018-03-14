angular.module('spiApp')
  .factory('enseignantSvc', ['$http', function ($http) {
    var factory = {};

    factory.getEnseignants = function (callback) {
      var endPoint = "http://localhost:8090/ens"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }


    factory.saveEnseignant = function (enseignant, callback) {
      var endPoint = "http://localhost:8090/ens"
      $http.post(endPoint, enseignant).then(function (response) {
        callback(response.data);
      });
    }

    factory.getEnseignantById = function (idEnseignant, callback) {
      var endPoint = "http://localhost:8090/ens/" + idEnseignant
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }


    factory.deleteEnseignantById = function (idEnseignant, callback) {
      var endPoint = "http://localhost:8090/ens/" + idEnseignant
      $http.delete(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    factory.updateEnseignant = function (enseignant, callback) {
      var endPoint = "http://localhost:8090/ens/"
      $http.put(endPoint, enseignant).then(function (response) {
        callback(response.data);
      });
    }

    factory.getEnseignantsCount = function (callback) {
      var endPoint = "http://localhost:8090/ens/count"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    return factory;

  }])