angular.module('spiApp')
    .factory('loginSvc', ['$http', function ($http) {
        var factory = {};

        factory.authentification = function (user, callback) {
            var endPoint = "http://localhost:8090/auth"
            $http.post(endPoint, user).then(function (response) {
                callback(response.data);
            });
        }


        factory.getUserSession = function (callback) {
            var endPoint = "http://localhost:8090/user"
            $http.get(endPoint).then(function (response) {
                callback(response.data);
            });
        }

        return factory;

    }])