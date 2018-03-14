angular.module('spiApp')

    //global-directives
    .directive("navbar", function () {
        return {
            templateUrl: 'views/directives/navbar.html'
        };
    })

    //enseignant-directives
    .directive("enseignantlist", function () {
        return {
            templateUrl: 'views/directives/directives-enseignant/enseignant-list.html'
        };
    })
    .directive("enseignantform", function () {
        return {
            templateUrl: 'views/directives/directives-enseignant/enseignant-form.html'
        };
    })
    .directive("enseignantdetail", function () {
        return {
            templateUrl: 'views/directives/directives-enseignant/enseignant-detail.html'
        };
    })
    .directive("enseignantdelete", function () {
        return {
            templateUrl: 'views/directives/directives-enseignant/enseignant-delete.html'
        };
    })

    //formation-directives
    .directive("formationlist", function () {
        return {
            templateUrl: 'views/directives/directives-formation/formation-list.html'
        };
    })
    .directive("formationform", function () {
        return {
            templateUrl: 'views/directives/directives-formation/formation-form.html'
        };
    })
    .directive("formationdetail", function () {
        return {
            templateUrl: 'views/directives/directives-formation/formation-detail.html'
        };
    })
    .directive("formationdelete", function () {
        return {
            templateUrl: 'views/directives/directives-formation/formation-delete.html'
        };
    })