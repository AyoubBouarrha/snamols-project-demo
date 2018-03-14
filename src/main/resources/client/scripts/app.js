angular
  .module('spiApp', [
    'ngRoute',
    'ngTable'
  ])
  
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/home.html',
        controller: 'homeCtrl',
        controllerAs: 'home'
      })
      .when('/enseignants', {
        templateUrl: 'views/enseignants.html',
        controller: 'enseignantCtrl',
        controllerAs: 'enseignant'
      })
      .when('/enseignant-detail/:id', {
        templateUrl: 'views/enseignant-detail.html',
        controller: 'enseignantDetailCtrl',
        controllerAs: 'enseignantDetail'
      })
      .when('/enseignant-add', {
        templateUrl: 'views/enseignant-add.html',
        controller: 'enseignantAddCtrl',
        controllerAs: 'enseignantAdd'
      })
      .when('/formations', {
        templateUrl: 'views/formations.html',
        controller: 'formationCtrl',
        controllerAs: 'formation'
      })
      .when('/formation-add', {
        templateUrl: 'views/formation-add.html',
        controller: 'formationAddCtrl',
        controllerAs: 'formationAdd'
      })
      .when('/formation-detail/:id', {
        templateUrl: 'views/formation-detail.html',
        controller: 'formationDetailCtrl',
        controllerAs: 'formationDetail'
      })
      .otherwise({
        redirectTo: '/'
      });
  });