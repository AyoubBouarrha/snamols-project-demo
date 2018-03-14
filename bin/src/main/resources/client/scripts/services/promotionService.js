angular.module('spiApp')
  .factory('promotionSvc', ['$http', function ($http) {
    var factory = {};

    factory.getPromotionsCount = function (callback) {
      var endPoint = "http://localhost:8090/promotions/count"
      $http.get(endPoint).then(function (response) {
        callback(response.data);
      });
    }

    return factory;

  }])