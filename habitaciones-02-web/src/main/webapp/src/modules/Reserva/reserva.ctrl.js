(function(ng) {
    var mod = ng.module("reservaModule");
    
    mod.controller("reservaListCtrl", ['$scope','reservas',
        function($scope,reservas) {
            $scope.reservasRecords = reservas.data;
        }
    ]);
    mod.controller("viajerosListCtrl", ['$scope','reservas','viajeros',
        function($scope,viajeros) {
            
            $scope.viaRecords = viajeros.data;
        }
    ]);
})(window.angular);