(function(ng) {
    var mod = ng.module("habitacionModule");
    
    mod.controller("habitacionListCtrl", ['$scope','habitaciones',
        function($scope, habitaciones) {
            $scope.habitacionesRecords = habitaciones.data;
        }
    ]);
    
})(window.angular);