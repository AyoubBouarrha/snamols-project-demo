angular.module('spiApp')
  .factory('elementConstitutifSvc', ['$http', function ($http) {
    var factory = {};

    factory.getElementConstitutifCount = function (callback) {
      var endPoint = "http://localhost:8090/Elementconstitutif/count"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    return factory;

  }])