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
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'userCtrl',
        controllerAs: 'user'
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
      .when('/questions', {
        templateUrl: 'views/questions.html',
        controller: 'questionCtrl',
        controllerAs: 'question'
      })
      .when('/rubriques', {
        templateUrl: 'views/rubriques.html',
        controller: 'rubriqueCtrl',
        controllerAs: 'rubrique'
      })
      .when('/couple-qualificatif/', {
        templateUrl: 'views/couple-qualificatif.html',
        controller: 'cqCtrl',
        controllerAs: 'cq'
      })
      .when('/evaluations', {
        templateUrl: 'views/evaluations.html',
        controller: 'evaluationCtrl',
        controllerAs: 'evaluation'
      })
      .when('/rubrique-eval/', {
        templateUrl: 'views/rubrique-eval.html',
        controller: 'rubriqueEvalCtrl',
        controllerAs: 'rubrique-eval'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  
  
  .run(function ($rootScope) {    
    //propriete publique à pour maintenir la listes des todos jusqu'à l'actualisation de la page
    $rootScope.selectedEvaluation = {};
  });