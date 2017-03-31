(function(ng) {
    var mod = ng.module("disponibilidadModule");
    
    mod.controller("disponibilidadListCtrl", ['$scope','disponibilidades',
        function($scope,disponibilidades) {
            $scope.disponibilidadesRecords = disponibilidades.data;
        }
    ]);
    
})(window.angular);