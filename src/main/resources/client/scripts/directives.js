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


    //evaluation-directives
    .directive("evaluationlist", function () {
        return {
            templateUrl: 'views/directives/directives-evaluation/evaluation-list.html'
        };
    })
    .directive("evaluationform", function () {
        return {
            templateUrl: 'views/directives/directives-evaluation/evaluation-form.html'
        };
    })

    .directive("evaluationdelete", function () {
        return {
            templateUrl: 'views/directives/directives-evaluation/evaluation-delete.html'
        };
    })



    //rub-eval-directives
    .directive("rubriqueevallist", function (){
        return{
            templateUrl : 'views/directives/directives-rubrique-eval/rubrique-eval-list.html'
        };
    })

    .directive("rubriqueevalform", function (){
        return{
            templateUrl : 'views/directives/directives-rubrique-eval/rubrique-eval-form.html'
        };
    })


    .directive("rubriqueevaldelete", function (){
        return{
            templateUrl : 'views/directives/directives-rubrique-eval/rubrique-eval-delete.html'
        };
    })


    .directive("viewrubriqueeval", function (){
        return{
            templateUrl : 'views/rubrique-eval.html'
        };
    })



    //qst-eval-directives
    .directive("questionevallist", function (){
        return{
            templateUrl : 'views/directives/directives-question-eval/question-eval-list.html'
        };
    })


    .directive("questionevaldelete", function (){
        return{
            templateUrl : 'views/directives/directives-question-eval/question-eval-delete.html'
        };
    })

    .directive("questionevalform", function (){
        return{
            templateUrl : 'views/directives/directives-question-eval/question-eval-form.html'
        };
    })


    .directive("viewquestioneval", function (){
        return{
            templateUrl : 'views/question-eval.html'
        };
    })
