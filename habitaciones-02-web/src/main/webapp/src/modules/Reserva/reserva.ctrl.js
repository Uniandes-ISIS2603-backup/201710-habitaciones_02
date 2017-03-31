(function(ng) {
    var mod = ng.module("reservaModule");
    
    mod.controller("reservaListCtrl", ['$scope','reservas',
        function($scope,reservas) {
            $scope.reservasRecords = reservas.data;
        }
    ]);
})(window.angular);