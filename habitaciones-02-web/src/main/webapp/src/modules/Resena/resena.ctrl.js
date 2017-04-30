(function (ng) {
    var mod = ng.module("resenaModule");

    mod.controller("resenaListCtrl", ['$scope', 'resenas',
        function ($scope, resenas)
        {
            $scope.RecordsResena = resenas.data;
        }]);
})(window.angular);