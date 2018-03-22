angular.module('spiApp')
  .factory('ueSvc', ['$http', function ($http) {
    var factory = {};


    factory.getUEByFormation = function (codeFormation,callback){
      var endPoint = "http://localhost:8090/ue/formation/"+codeFormation;
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    return factory;

  }])