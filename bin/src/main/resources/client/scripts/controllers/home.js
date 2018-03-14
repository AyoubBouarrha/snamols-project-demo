angular.module('spiApp')
    .controller('homeCtrl', ['$scope', 'enseignantSvc','formationSvc','candidatSvc','promotionSvc', 'elementConstitutifSvc', function ($scope, enseignantSvc,formationSvc,candidatSvc,promotionSvc,elementConstitutifSvc) {

        //Recuperation de nombre des Enseignants
        getEnseignantsCount = function () {
            enseignantSvc.getEnseignantsCount(function (data) {
                $scope.nombreEnseigants=data;
            });
        }

        //Recuperation de nombre des formations
        getFormationsCount = function () {
            formationSvc.getFormationsCount(function (data) {
                $scope.nombreFormations=data;
            });
        }

        //Recuperation de nombre des Candidats
        getCandidatsCount = function () {
            candidatSvc.getCandidatsCount(function (data) {
                $scope.nombreCandidats=data;
            });
        }

        //Recuperation de nombre des Promotions
        getPromotionsCount = function () {
            promotionSvc.getPromotionsCount(function (data) {
                $scope.nombrePromotions=data;
            });
        }

        

        getEnseignantsCount();
        getFormationsCount();
        getCandidatsCount();
        getPromotionsCount();



    }]);