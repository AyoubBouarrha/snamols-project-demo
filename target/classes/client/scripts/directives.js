angular.module('spiApp')

    //global-directives
    .directive("navbar", function () {
        return {
            templateUrl: 'views/directives/navbar.html'
        };
    })

    .directive("loginform", function () {
        return {
            templateUrl: 'views/directives/directives-login/login-form.html'
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


    //questions-directives
    .directive("questionlist", function () {
        return {
            templateUrl: 'views/directives/directives-question/question-list.html'
        };
    })
    .directive("questionform", function () {
        return {
            templateUrl: 'views/directives/directives-question/question-form.html'
        };
    })
    .directive("questiondelete", function () {
        return {
            templateUrl: 'views/directives/directives-question/question-delete.html'
        };
    })

    //rubrique-directives
    .directive("rubriquelist", function () {
        return {
            templateUrl: 'views/directives/directives-rubrique/rubrique-list.html'
        };
    })
    .directive("rubriqueform", function () {
        return {
            templateUrl: 'views/directives/directives-rubrique/rubrique-form.html'
        };
    })

    .directive("rubriquedelete", function () {
        return {
            templateUrl: 'views/directives/directives-rubrique/rubrique-delete.html'
        };
    })


     //cq-directives
     .directive("cqform", function () {
        return {
            templateUrl: 'views/directives/directives-cq/cq-form.html'
        };
    })

    .directive("cqlist", function () {
        return {
            templateUrl: 'views/directives/directives-cq/cq-list.html'
        };
    })
    .directive("cqdelete", function () {
        return {
            templateUrl: 'views/directives/directives-cq/cq-delete.html'
        };
    })