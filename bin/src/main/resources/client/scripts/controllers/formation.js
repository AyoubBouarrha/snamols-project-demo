
angular.module('spiApp')
    .controller('formationCtrl', ['$scope', 'formationSvc', 'NgTableParams', function ($scope, formationSvc, NgTableParams) {

        $scope.sujet = "une formation";
        $scope.editoption = "l\'ajout";

        $scope.formations = [];
        $scope.editformation = {};

        $scope.cannotRemove = false;

        $scope.cannotAdd = false;

        //Recuperation des formations
        getFormations = function () {
            formationSvc.getFormations(function (data) {
                console.log(data);
                $scope.formations = data;
                $scope.tableParams = new NgTableParams({ sorting: { name: "asc" } }, { dataset: data });
            });
        }

        getFormations();


        $scope.cancelEditing = function () {
            $('#codeFormation').attr("disabled", false);
            $('#form-collapse').collapse('hide');            
            $scope.editFormation={};
            $scope.formformation.$setPristine();
            getFormations();
        }

        $scope.editSubmit = function () {
            console.log($scope.editoption);
            formationSvc.updateFormation($scope.editFormation, function (data) {
                $('#codeFormation').attr("disabled", false);
                $('#form-collapse').collapse('hide');
                $scope.formformation.$setPristine();
                getFormations();
            });
        }


        $scope.validateDelete = function () {
            formationSvc.deleteFormationById($scope.selectedFormation.codeFormation, function (data) {
                if (data == true) {
                    $scope.cannotRemove = false;
                    $('#delete-modal').modal('hide');
                    getFormations();
                }
                else {
                    $scope.cannotRemove = true;
                }
            })
        }

        $scope.cancelDelete = function () {
            $('#delete-modal').modal('hide');
        }


        $scope.showInfo = function (formation) {
            $scope.selectedFormation = formation;
        }

        $scope.showDeleteBox = function (formation) {
            $scope.selectedFormation = formation;
            $scope.cannotRemove = false;
        }

        $scope.showUpdateBox = function (formation) {
            $scope.editoption = "la modification";                        
            $scope.editformation={};
            $scope.editFormation = formation;
            $scope.editFormation.debutAccreditation = new Date($scope.editFormation.debutAccreditation);
            $scope.editFormation.finAccreditation = new Date($scope.editFormation.finAccreditation);
            $('#codeFormation').attr("disabled", true);
            $('#form-collapse').collapse('show');

        }

        $scope.showAddBox = function () {
            $scope.editFormation = {};
            $scope.formformation.$setPristine();
            $scope.editoption = "l\'ajout";
            $('#codeFormation').attr("disabled", false);
            $('#form-collapse').collapse('show');
        }



    }]);