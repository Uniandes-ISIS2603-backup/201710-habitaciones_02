(function (ng) {
    var mod = ng.module("viajeroModule");

    mod.controller("viajeroListCtrl", ['$scope', 'viajeros',
        function ($scope, viajeros)
        {
            $scope.RecordsViajero = viajeros.data;
        }]);
})(window.angular);