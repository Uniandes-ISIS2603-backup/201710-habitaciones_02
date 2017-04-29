(function (ng) {
    var mod = ng.module("pagoModule");

    mod.controller("pagoListCtrl", ['$scope', 'pagos',
        function ($scope, pagos) {
            $scope.pagosRecords = pagos.data;
        }
    ]);
})(window.angular);